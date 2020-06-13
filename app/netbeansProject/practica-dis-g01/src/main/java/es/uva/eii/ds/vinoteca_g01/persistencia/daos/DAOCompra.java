/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.persistencia.daos;

import es.uva.eii.ds.vinoteca_g01.persistencia.dbaccess.DBConnection;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
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
        String recibidaCompleta="", pagada="", fechaCompletada = null, fechaDePago= null;
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
                if(recibidaCompleta.equals("T")){
                    fechaCompraCompletada = rs.getDate("FechaCompraCompletada").toLocalDate();
                    fechaCompletada = fechaCompraCompletada.toString();
                } else {
                    fechaCompraCompletada = null;
                }
                importe = rs.getDouble("Importe");
                pagada = rs.getString("Pagada");
                if(pagada.equals("T")){
                    fechaPago = rs.getDate("FechaPago").toLocalDate();
                    fechaDePago = fechaPago.toString();
                } else {
                    fechaPago = null;
                }
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
            
            compraJSON = obtenerCompraJsonString(Integer.toString(idCompra), fechaInicioCompra.toString(), recibidaCompleta, fechaCompletada,
                    Double.toString(importe), pagada, fechaDePago, Integer.toString(idProveedor), lineasCompra, bodega);
           }

        return compraJSON;
    }

 
    private static String obtenerCompraJsonString(String idCompra, String fechaInicioCompra, String recibidaCompleta, 
            String fechaCompraCompletada, String importe, String pagada, String fechaPago, String idProveedor, 
            String lineasCompra, String bodega) {
        String compraLineasCompraJsonString = "";
        JsonReaderFactory factory = Json.createReaderFactory(null);
        
        try {
            JsonReader readerLineasCompra = factory.createReader(new StringReader(lineasCompra));
                     
            StringWriter stringWriter = new StringWriter();
            JsonWriter writer = Json.createWriter(stringWriter);
            
            JsonArray lineasCompraArray = readerLineasCompra.readArray();
          
            JsonObject compraJson = Json.createObjectBuilder()
                    .add("idCompra", idCompra)
                    .add("fechaInicioCompra", fechaInicioCompra)
                    .add("recibidaCompleta", recibidaCompleta)
                    .add("importe", importe)
                    .add("pagada", pagada)
                    .add("idProveedor", idProveedor)
                    .add("lineasCompra", lineasCompraArray)
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
