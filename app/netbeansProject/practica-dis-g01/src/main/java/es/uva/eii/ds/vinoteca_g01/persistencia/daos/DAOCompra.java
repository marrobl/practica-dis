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
        String recibidaCompleta="", pagada="";
        double importe=0;
        
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        JsonObjectBuilder jb = Json.createObjectBuilder();

        
        try {
            PreparedStatement ps = connection.getStatement(SELECT_COMPRA_ID_COMPRA);
            ps.setString(1,id);

            ResultSet rs = ps.executeQuery();
 
            if (rs.next()) {
                idCompra = rs.getInt("IdCompra");
                fechaInicioCompra = rs.getDate("FechaInicioCompra").toLocalDate();
                recibidaCompleta = rs.getString("RecibidaCompleta");
                fechaCompraCompletada = rs.getDate("FechaCompraCompletada").toLocalDate();
                importe = rs.getDouble("Importe");
                pagada = rs.getString("FechaRecepcion");
                fechaPago = rs.getDate("FechaPago").toLocalDate();
                idProveedor = rs.getInt("IdProveedor");
                
                
              
            }
            
            compraJSON = obtenerCompraJsonString(idCompra, fechaInicioCompra, recibidaCompleta, fechaCompraCompletada,
                    importe, pagada, fechaPago, idProveedor);

            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOFactura.class.getName()).log(Level.SEVERE, null, ex);
        }

        connection.closeConnection();
        
        
        return compraJSON;
    }

 
    private static String obtenerCompraJsonString(int idCompra, LocalDate fechaInicioCompra, String recibidaCompleta, LocalDate fechaCompraCompletada, double importe, String pagada, LocalDate fechaPago, int idProveedor) {
        String empleadoJsonString = "";
        JsonReaderFactory factory = Json.createReaderFactory(null);
        
        try {
        
            StringWriter stringWriter = new StringWriter();
            JsonWriter writer = Json.createWriter(stringWriter);
            
            
            JsonObject compraJson = Json.createObjectBuilder()
                    .add("idCompra", idCompra)
                    .add("fechaInicioCompra", fechaInicioCompra.toString())
                    .add("recibidaCompleta", recibidaCompleta)
                    .add("fechaCompraCompletada", fechaCompraCompletada.toString())
                    .add("importe", Double.toString(importe))
                    .add("fechaPago", fechaPago.toString())
                    .add("idProveedor", Integer.toString(idProveedor))
                    .build();
            
            writer.writeObject(compraJson);
            empleadoJsonString = stringWriter.toString();
        } catch(Exception ex) {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return empleadoJsonString;
    }
    
}
