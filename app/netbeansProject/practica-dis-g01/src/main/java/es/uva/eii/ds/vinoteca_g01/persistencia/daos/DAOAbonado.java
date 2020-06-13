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
public class DAOAbonado {

    private static final String SELECT_ABONADO_POR_NUMERO = 
            "SELECT * FROM Persona P, Abonado A WHERE P.nif=A.nif AND A.numeroAbonado=?";
    
    public static String consultarAbonadoPorId(int numAbonado) {
        String abonadoJsonString = null;
        String nif = "", nombre = "", apellidos = "", direccion = "";
        String telefono = "", email = "", cuentaBancaria = "";
        String openIDref = "";
        boolean hayDatos = false;
        
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        
        try {
            PreparedStatement ps = connection.getStatement(SELECT_ABONADO_POR_NUMERO);
            ps.setInt(1, numAbonado);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                hayDatos = true;
                nif = rs.getString("Nif");
                nombre = rs.getString("Nombre");
                apellidos = rs.getString("Apellidos");
                direccion = rs.getString("Direccion");
                telefono = rs.getString("Telefono");
                email = rs.getString("Email");
                cuentaBancaria = rs.getString("CuentaBancaria");
                numAbonado = rs.getInt("NumeroAbonado");
                openIDref = rs.getString("OpenIDref");
            }
            
            connection.closeConnection();
            rs.close();
        } catch(SQLException ex) {
            Logger.getLogger(DAOAbonado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (hayDatos) {
            abonadoJsonString = obtenerAbonadoJsonString(nif, nombre, apellidos, direccion,
                    telefono, email, cuentaBancaria, numAbonado, openIDref);
        }
        
        return abonadoJsonString;
    }

    private static String obtenerAbonadoJsonString(String nif, String nombre, String apellidos, 
            String direccion, String telefono, String email, String cuentaBancaria, int numAbonado, String openIDref) {
        String abonadoJsonString = "";
        
        try {
            StringWriter stringWriter = new StringWriter();
            JsonWriter writer = Json.createWriter(stringWriter);
            
            JsonObject abonadoJson = Json.createObjectBuilder()
                    .add("nif", nif)
                    .add("nombre", nombre)
                    .add("apellidos", apellidos)
                    .add("direccion", direccion)
                    .add("telefono", telefono)
                    .add("email", email)
                    .add("cuentaBancaria", cuentaBancaria)
                    .add("numeroAbonado", numAbonado)
                    .add("openIDref", openIDref)
                    .build();
            
            writer.writeObject(abonadoJson);
            abonadoJsonString = stringWriter.toString();
        } catch (Exception ex) {
            Logger.getLogger(DAOAbonado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return abonadoJsonString;
    }
    
}
