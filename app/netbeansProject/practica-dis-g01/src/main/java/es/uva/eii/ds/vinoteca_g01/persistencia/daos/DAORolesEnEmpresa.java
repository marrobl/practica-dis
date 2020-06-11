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
public class DAORolesEnEmpresa {
    public static final String SELECT_TODOS_ROLES_EMPLEADO = 
            "SELECT * FROM RolesEnEmpresa RE, TipoDeRol TR WHERE RE.empleado=? AND RE.rol=TR.idTipo ORDER BY RE.comienzoEnRol ASC";

    public static String consultarTodosLosRolesPorId(String dni) {
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        StringBuilder roles = new StringBuilder("[");
        
        try {
            PreparedStatement s = connection.getStatement(SELECT_TODOS_ROLES_EMPLEADO);
            s.setString(1, dni);
            ResultSet result = s.executeQuery();
            
            LocalDate comienzoEnRol;
            String rol;
            
            while (result.next()) {
                comienzoEnRol = result.getDate("ComienzoEnRol").toLocalDate();
                rol = result.getString("NombreTipo");
                roles.append(obtenerRolEnEmpresaJsonString(comienzoEnRol.toString(), rol));
                roles.append(",");
            }
            
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAORolesEnEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        connection.closeConnection();
        
        if (roles.charAt(roles.length()-1) == ',') {
            roles.deleteCharAt(roles.length()-1);
        }
        
        roles.append("]");
        
        return roles.toString();
    }

    private static String obtenerRolEnEmpresaJsonString(String comienzoEnEmpresa, String rol) {
        String rolEnEmpresaJsonString = "";
        
        try {
            StringWriter stringWriter = new StringWriter();
            JsonWriter writer = Json.createWriter(stringWriter);
            
            JsonObject rolEnEmpresaJson = Json.createObjectBuilder()
                    .add("comienzoEnRol", comienzoEnEmpresa)
                    .add("rol", rol)
                    .build();
            
            writer.writeObject(rolEnEmpresaJson);
            rolEnEmpresaJsonString = stringWriter.toString();
        } catch(Exception ex) {
            Logger.getLogger(DAORolesEnEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rolEnEmpresaJsonString;
    }
}
