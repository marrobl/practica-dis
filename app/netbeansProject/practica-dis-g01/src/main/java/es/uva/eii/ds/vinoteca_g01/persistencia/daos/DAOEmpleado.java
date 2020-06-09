/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.persistencia.daos;

import es.uva.eii.ds.vinoteca_g01.persistencia.dbaccess.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

/**
 *
 * @author richard
 */
public class DAOEmpleado {
    
    private static final String SELECT_EMPLEADO_POR_DNI_Y_PASSWORD = "SELECT * FROM Persona P, Empleado E WHERE E.nif=? AND E.password=? AND E.nif=P.nif";

    public static String consultaEmpleadoPorNifYPassword(String dni, String password) {
        String empleadoJsonString = "";
        String nif, nombre, apellidos, direccion, telefono, email, cuentaBancaria, passw;
        LocalDate fechaInicio;
       
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        JsonObjectBuilder jb = Json.createObjectBuilder();
        
        try {
            PreparedStatement ps = connection.getStatement(SELECT_EMPLEADO_POR_DNI_Y_PASSWORD);
            ps.setString(1, dni);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                nif = rs.getString(1);
                nombre = rs.getString(2);
                apellidos = rs.getString(3);
                direccion = rs.getString(4);
                telefono = rs.getString(5);
                cuentaBancaria = rs.getString(6);
                passw = rs.getString(7);
                fechaInicio = rs.getTimestamp(8).toLocalDateTime().toLocalDate();
            }
        } catch(SQLException ex) {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}
