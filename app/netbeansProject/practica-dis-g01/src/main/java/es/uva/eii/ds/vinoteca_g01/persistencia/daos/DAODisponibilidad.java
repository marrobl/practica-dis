/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.persistencia.daos;

import es.uva.eii.ds.vinoteca_g01.persistencia.dbaccess.DBConnection;
import java.io.StringWriter;
import java.math.BigDecimal;
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
 * @author richard
 */
public class DAODisponibilidad {
    public static final String SELECT_TODAS_DISPONIBILIDADES_EMPLEADO =
            "SELECT * FROM Disponibilidad D, TipoDeDisponibilidad TD WHERE D.empleado=? AND D.disponibilidad=TD.idTipo ORDER BY D.comienzo ASC";

    static String consultarTodasDisponibilidadesPorId(String dni) {
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        StringBuilder disponibilidades = new StringBuilder("[");
        
        try {
            PreparedStatement s = connection.getStatement(SELECT_TODAS_DISPONIBILIDADES_EMPLEADO);
            s.setString(1, dni);
            ResultSet result = s.executeQuery();
            
            LocalDate comienzo;
            LocalDate finalPrevisto;
            String disponibilidad = "";
            
            while (result.next()) {
                comienzo = result.getDate("Comienzo").toLocalDate();
                finalPrevisto = result.getDate("FinalPrevisto").toLocalDate();
                disponibilidad = result.getString("Disponibilidad");
                disponibilidades.append(mapEntryAsJSON(comienzo, finalPrevisto, disponibilidad));
                disponibilidades.append(",");
            }
            
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAODisponibilidad.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        connection.closeConnection();
        
        if (disponibilidades.charAt(disponibilidades.length()-1) == ',') {
            disponibilidades.deleteCharAt(disponibilidades.length()-1);
        }
        
        disponibilidades.append("]");
        
        return disponibilidades.toString();
    }

    private static String mapEntryAsJSON(LocalDate comienzo, LocalDate finalPrevisto, String disponibilidad) {
        String entryJSON = "";
        JsonObject json = Json.createObjectBuilder()
                .add("comienzo", comienzo.toString())
                .add("finalPrevisto", Json.createObjectBuilder().add("finalPrevisto", finalPrevisto.toString()).build())
                .add("disponibilidad", Json.createObjectBuilder().add("disponibilidad", disponibilidad))
                .build();
        
        try {
            StringWriter stringWriter = new StringWriter();
            JsonWriter writer = Json.createWriter(stringWriter);
            writer.writeObject(json);
            entryJSON = stringWriter.toString(); 
        } catch (Exception ex) {
            Logger.getLogger(DAODisponibilidad.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return entryJSON;
    }  
}
