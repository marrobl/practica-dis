/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.modelos;

import es.uva.eii.ds.vinoteca_g01.persistencia.daos.DAOEmpleado;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;

/**
 *
 * @author richard
 */
public class Empleado extends Persona {
    
    private LocalDate fechaInicio;
    private ArrayList<RolesEnEmpresa> rolesEnEmpresa;
    private ArrayList<VinculacionConLaEmpresa> vinculaciones;
    private ArrayList<Disponibilidad> disponibilidades;

    public static void getEmpleadoPorNifYPassword(String dni, String password) {
        String datosJSON = DAOEmpleado.consultaEmpleadoPorNifYPassword(dni, password);
    }

    public Empleado(String nif, String nombre, String apellidos, String telefono, String email, String cuentaBancaria) {
        super(nif, nombre, apellidos, telefono, email, cuentaBancaria);
    }
    
    public Empleado(String json) {
        rolesEnEmpresa = new ArrayList<>();
        vinculaciones = new ArrayList<>();
        disponibilidades = new ArrayList<>();
        JsonReaderFactory factory = Json.createReaderFactory(null);
        
        try {
            JsonReader reader = factory.createReader(new StringReader(json));
            JsonObject empleadoJson = reader.readObject();
            
            String nif = empleadoJson.getString("nif");
            String nombre = empleadoJson.getString("nombre");
            String apellidos = empleadoJson.getString("apellidos");
            String telefono = empleadoJson.getString("telefono");
            String cuentaBancaria = empleadoJson.getString("cuentaBancaria");
            
            super(nif, nombre, apellidos, telefono, nif, cuentaBancaria);
        } catch(Exception ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fechaInicio = 
    }
    
}
