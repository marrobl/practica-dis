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
import javax.json.JsonWriter;

/**
 *
 * @author maria
 */
public class DAOBodega {
    private static final String SELECT_BODEGA_ID_COMPRA
            = "SELECT * FROM Bodega B WHERE B.id = ? ";

    static String consultarBodegaPorId(int idProveedor) {
        String bodegaJsonString;
       
        int id = 0;
        String nombre = "", cif = "", direccion = "";
             
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        
        try {
            PreparedStatement ps = connection.getStatement(SELECT_BODEGA_ID_COMPRA);
            ps.setInt(1, idProveedor);

            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                id = rs.getInt("Id");
                nombre = rs.getString("Nombre");
                cif = rs.getString("Cif");
                direccion = rs.getString("Direccion");
            }
            
            connection.closeConnection();
            
            rs.close();
        } catch(SQLException ex) {
            Logger.getLogger(DAOBodega.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        bodegaJsonString = obtenerBodegaJsonString(Integer.toString(id), nombre, cif, direccion);
        return bodegaJsonString;
    }

    private static String obtenerBodegaJsonString(String id, String nombre, String cif, String direccion) {
        String bodegaJsonString = "";
        
        try {
            StringWriter stringWriter = new StringWriter();
            JsonWriter writer = Json.createWriter(stringWriter);
            
            JsonObject lineaCompraJson = Json.createObjectBuilder()
                    .add("id", id)
                    .add("nombre", nombre)
                    .add("cif", cif)
                    .add("direccion", direccion)
                    .build();
            
            writer.writeObject(lineaCompraJson);
            bodegaJsonString = stringWriter.toString();
        } catch(Exception ex) {
            Logger.getLogger(DAOBodega.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bodegaJsonString;
    }
  
}
