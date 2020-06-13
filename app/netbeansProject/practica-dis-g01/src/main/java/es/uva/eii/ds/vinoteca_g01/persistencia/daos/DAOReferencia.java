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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;

/**
 *
 * @author richard
 */
public class DAOReferencia {
    
    private static final String SELECT_REFERENCIA_POR_CODIGO = "SELECT * FROM Referencia R WHERE R.codigo=?";

    public static String consultarReferenciaPorCodigo(int codigo) {
        String referenciaJsonString = null;
        boolean esPorCajas = false, disponible = false;
        int contenidoEnCL = 0, vinoId = 0;
        double precio = 0;
        boolean hayDatos = false;
        
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        
        try {
            PreparedStatement ps = connection.getStatement(SELECT_REFERENCIA_POR_CODIGO);
            ps.setInt(1, codigo);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                hayDatos = true;
                codigo = rs.getInt("Codigo");
                esPorCajas = rs.getString("EsPorCajas").equals("T");
                contenidoEnCL = rs.getInt("ContenidoEnCL");
                precio = rs.getDouble("Precio");
                disponible = rs.getString("Disponible").equals("T");
                vinoId = rs.getInt("VinoId");
            }
            
            connection.closeConnection();
            rs.close();
        } catch(SQLException ex) {
            Logger.getLogger(DAOReferencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (hayDatos) {
            referenciaJsonString = obtenerReferenciaJsonString(codigo, esPorCajas, contenidoEnCL, precio, disponible, vinoId);
        }
        
        return referenciaJsonString;
    }

    private static String obtenerReferenciaJsonString(int codigo, boolean esPorCajas, int contenidoEnCL, double precio, boolean disponible, int vinoId) {
        String referenciaJsonString = "";
        
        try {
            StringWriter stringWriter = new StringWriter();
            JsonWriter writer = Json.createWriter(stringWriter);
            
            JsonObject referenciaJson = Json.createObjectBuilder()
                    .add("codigo", codigo)
                    .add("esPorCajas", esPorCajas)
                    .add("contenidoEnCL", contenidoEnCL)
                    .add("precio", precio)
                    .add("disponible", disponible)
                    .add("vinoId", vinoId)
                    .build();
            
            writer.writeObject(referenciaJson);
            referenciaJsonString = stringWriter.toString();
        } catch (Exception ex) {
            Logger.getLogger(DAOReferencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return referenciaJsonString;
    }
    
}
