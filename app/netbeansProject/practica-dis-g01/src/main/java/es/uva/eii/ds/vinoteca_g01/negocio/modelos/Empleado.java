/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.modelos;

import es.uva.eii.ds.vinoteca_g01.persistencia.daos.DAOEmpleado;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.DatosIncorrectosException;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import javax.json.JsonValue;

/**
 *
 * @author richard
 */
public class Empleado extends Persona {
    
    private LocalDate fechaInicio;
    private ArrayList<RolesEnEmpresa> rolesEnEmpresa;
    private ArrayList<VinculacionConLaEmpresa> vinculaciones;
    private ArrayList<Disponibilidad> disponibilidades;

    public Empleado(String nif, String nombre, String apellidos, String telefono, String email, String cuentaBancaria) {
        super();
    }
    
    public Empleado(String json) {
        super();
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
            
            setNif(nif);
            setNombre(nombre);
            setApellidos(apellidos);
            setTelefono(telefono);
            setCuentaBancaria(cuentaBancaria);
            
            JsonArray rolesJson = empleadoJson.getJsonArray("rolesEnEmpresa");
            
            RolesEnEmpresa rolEnEmpresa;
            
            for (JsonValue j: rolesJson) {
                rolEnEmpresa = new RolesEnEmpresa(j.asJsonObject().toString());
                rolesEnEmpresa.add(rolEnEmpresa);
            }
            
            JsonArray vinculacionesJson = empleadoJson.getJsonArray("vinculaciones");
            
            VinculacionConLaEmpresa vinculacion;
            
            for (JsonValue j: vinculacionesJson) {
                vinculacion = new VinculacionConLaEmpresa(j.asJsonObject().toString());
                vinculaciones.add(vinculacion);
            }
            
            JsonArray disponibilidadesJson = empleadoJson.getJsonArray("disponibilidades");
            
            Disponibilidad disponibilidad;
            
            for (JsonValue j: disponibilidadesJson) {
                disponibilidad = new Disponibilidad(j.asJsonObject().toString());
                disponibilidades.add(disponibilidad);
            }
            
            
            System.out.println(nif);
        } catch(Exception ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Empleado getEmpleadoPorNifYPassword(String dni, String password) throws DatosIncorrectosException {
        String datosJSON = DAOEmpleado.consultaEmpleadoPorNifYPassword(dni, password);      
        
        if (datosJSON == null) {
            throw new DatosIncorrectosException();
        }
        
        return new Empleado(datosJSON);
    }

    public boolean estaActivo() {
        return false;
    }

    public TipoDeRol getRol() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
