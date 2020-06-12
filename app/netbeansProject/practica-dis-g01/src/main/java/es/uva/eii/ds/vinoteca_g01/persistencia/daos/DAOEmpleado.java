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
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import javax.json.JsonWriter;

/**
 *
 * @author richard
 */
public class DAOEmpleado {
    
    private static final String SELECT_EMPLEADO_POR_DNI_Y_PASSWORD = 
            "SELECT * FROM Persona P, Empleado E WHERE E.nif=? AND E.password=? AND P.nif=E.nif";

    public static String consultaEmpleadoPorNifYPassword(String dni, String password) {
        String empleadoJsonString = null;
        String nif = "", nombre = "", apellidos = "", direccion = "",
                telefono = "", email = "", cuentaBancaria = "";
        LocalDate fechaInicio = null;
        boolean hayDatos = false;
       
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        
        try {
            PreparedStatement ps = connection.getStatement(SELECT_EMPLEADO_POR_DNI_Y_PASSWORD);
            ps.setString(1, dni);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                hayDatos = true;
                nif = rs.getString("Nif");
                nombre = rs.getString("Nombre");
                apellidos = rs.getString("Apellidos");
                direccion = rs.getString("Direccion");
                telefono = rs.getString("Telefono");
                cuentaBancaria = rs.getString("CuentaBancaria");
                fechaInicio = rs.getDate("FechaInicioEnEmpresa").toLocalDate();
            }
            
            connection.closeConnection();
            
            rs.close();
        } catch(SQLException ex) {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (hayDatos) {
            String roles = DAORolesEnEmpresa.consultarTodosLosRolesPorId(dni);
            String vinculaciones = DAOVinculacionConLaEmpresa.consultarTodasVinculacionesPorId(dni);
            String disponibilidades = DAODisponibilidad.consultarTodasDisponibilidadesPorId(dni);

            empleadoJsonString = obtenerEmpleadoJsonString(nif, nombre, apellidos, direccion,
                        telefono, email, cuentaBancaria, fechaInicio.toString(), roles, vinculaciones, disponibilidades);
        }
        
        return empleadoJsonString;
    }

    private static String obtenerEmpleadoJsonString(String nif, String nombre, String apellidos,
            String direccion, String telefono, String email, String cuentaBancaria, String fechaInicio,
            String roles, String vinculaciones, String disponibilidades) {
        String empleadoJsonString = "";
        JsonReaderFactory factory = Json.createReaderFactory(null);
        
        try {
            JsonReader readerRoles = factory.createReader(new StringReader(roles));
            JsonReader readerVinculaciones = factory.createReader(new StringReader(vinculaciones));
            JsonReader readerDisponibilidades = factory.createReader(new StringReader(disponibilidades));
            
            StringWriter stringWriter = new StringWriter();
            JsonWriter writer = Json.createWriter(stringWriter);
            
            JsonArray rolesJsonArray = readerRoles.readArray();
            JsonArray vinculacionesJsonArray = readerVinculaciones.readArray();
            JsonArray disponibilidadesJsonArray = readerDisponibilidades.readArray();
            
            JsonObject empleadoJson = Json.createObjectBuilder()
                    .add("nif", nif)
                    .add("nombre", nombre)
                    .add("apellidos", apellidos)
                    .add("direccion", direccion)
                    .add("telefono", telefono)
                    .add("email", email)
                    .add("cuentaBancaria", cuentaBancaria)
                    .add("fechaInicioEnEmpresa", fechaInicio)
                    .add("rolesEnEmpresa", rolesJsonArray)
                    .add("vinculaciones", vinculacionesJsonArray)
                    .add("disponibilidades", disponibilidadesJsonArray)
                    .build();
            
            writer.writeObject(empleadoJson);
            empleadoJsonString = stringWriter.toString();
        } catch(Exception ex) {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return empleadoJsonString;
    }    
}
