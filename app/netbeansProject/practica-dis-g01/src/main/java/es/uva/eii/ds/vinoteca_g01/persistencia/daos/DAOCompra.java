/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.persistencia.daos;

import es.uva.eii.ds.vinoteca_g01.persistencia.dbaccess.DBConnection;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReaderFactory;
import javax.json.JsonWriter;

/**
 *
 * @author silmont
 */
public class DAOCompra {
    private static final String SELECT_COMPRA_ID_COMPRA
            = "SELECT * FROM Compra C WHERE C.IdCompra = ? ";

    public static String consultaCompraPorId(String id) {
        String compraJSON = null;
        
        int idCompra=0; int idProveedor=0;
        LocalDate fechaInicioCompra=null, fechaCompraCompletada=null, fechaPago = null;
        String recibidaCompleta="", pagada="";
        double importe=0;
        boolean hayDatos = false;
        
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        JsonObjectBuilder jb = Json.createObjectBuilder();

        
        try {
            PreparedStatement ps = connection.getStatement(SELECT_COMPRA_ID_COMPRA);
            ps.setString(1,id);

            ResultSet rs = ps.executeQuery();
 
            if (rs.next()) {
                hayDatos = true;
                idCompra = rs.getInt("IdCompra");
                fechaInicioCompra = rs.getDate("FechaInicioCompra").toLocalDate();
                recibidaCompleta = rs.getString("RecibidaCompleta");
                fechaCompraCompletada = rs.getDate("FechaCompraCompletada").toLocalDate();
                importe = rs.getDouble("Importe");
                pagada = rs.getString("FechaRecepcion");
                fechaPago = rs.getDate("FechaPago").toLocalDate();
                idProveedor = rs.getInt("IdProveedor");
            }
            
            connection.closeConnection();      
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (hayDatos) {
            String lineasCompra = DAOLineaCompra.consultarLineasCompraPorIdCompra(id);
            String bodega = DAOBodega.consultarBodegaPorId(idProveedor);
            
            compraJSON = obtenerCompraJsonString(Integer.toString(idCompra), fechaInicioCompra.toString(), recibidaCompleta, fechaCompraCompletada.toString(),
                    Double.toString(importe), pagada, fechaPago.toString(), Integer.toString(idProveedor), lineasCompra, bodega);
           }

        return compraJSON;
    }

 
    private static String obtenerCompraJsonString(String idCompra, String fechaInicioCompra, String recibidaCompleta, String fechaCompraCompletada, String importe, String pagada, String fechaPago, String idProveedor, String lineasCompra, String bodega) {
        String compraLineasCompraJsonString = "";
        JsonReaderFactory factory = Json.createReaderFactory(null);
        
        try {
        
            StringWriter stringWriter = new StringWriter();
            JsonWriter writer = Json.createWriter(stringWriter);
            
            
            JsonObject compraJson = Json.createObjectBuilder()
                    .add("idCompra", idCompra)
                    .add("fechaInicioCompra", fechaInicioCompra)
                    .add("recibidaCompleta", recibidaCompleta)
                    .add("fechaCompraCompletada", fechaCompraCompletada)
                    .add("importe", importe)
                    .add("fechaPago", fechaPago)
                    .add("idProveedor", idProveedor)
                    .add("lineasCompra", lineasCompra)
                    .add("bodega", bodega)
                    .build();
            
            writer.writeObject(compraJson);
            compraLineasCompraJsonString = stringWriter.toString();
        } catch(Exception ex) {
            Logger.getLogger(DAOCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return compraLineasCompraJsonString;
    }
    
}
