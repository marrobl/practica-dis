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
 * @author richard
 */
public class DAOVinculacionConLaEmpresa {
    public static final String SELECT_TODAS_VINCULACIONES_EMPLEADO =
            "SELECT * FROM VinculacionConLaEmpresa VE, TipoDeVinculacion TV WHERE VE.empleado=? AND VE.vinculo=TV.idTipo ORDER BY VE.inicio ASC";

    public static String consultarTodasVinculacionesPorId(String dni) {
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        StringBuilder vinculaciones = new StringBuilder("[");
        
        try {
            PreparedStatement s = connection.getStatement(SELECT_TODAS_VINCULACIONES_EMPLEADO);
            s.setString(1, dni);
            ResultSet result = s.executeQuery();
            
            LocalDate inicio;
            String vinculo = "";
            
            while (result.next()) {
                inicio = result.getDate("Inicio").toLocalDate();
                vinculo = result.getString("Vinculo");
                vinculaciones.append(mapEntryASJSON(inicio, vinculo));
                vinculaciones.append(",");
            }
            
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOVinculacionConLaEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        connection.closeConnection();
        
        if (vinculaciones.charAt(vinculaciones.length()-1) == ',') {
            vinculaciones.deleteCharAt(vinculaciones.length()-1);
        }
        
        vinculaciones.append("]");
        
        return vinculaciones.toString();
    }

    private static String mapEntryASJSON(LocalDate inicio, String vinculo) {
        String entryJSON = "";
        JsonObject json = Json.createObjectBuilder()
                .add("inicio", inicio.toString())
                .add("vinculo", Json.createObjectBuilder().add("vinculo", vinculo).build())
                .build();
        
        try {
            StringWriter stringWriter = new StringWriter();
            JsonWriter writer = Json.createWriter(stringWriter);
            writer.writeObject(json);
            entryJSON = stringWriter.toString();
        } catch (Exception ex) {
            Logger.getLogger(DAORolesEnEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return entryJSON;
    }
    
   
}
