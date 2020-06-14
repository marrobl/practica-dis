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
 * Clase que representa un Empleado de una empresa.
 *
 * @author ricalba
 * @author silmont
 * @author marrobl
 */
public class Empleado extends Persona {
    
    private LocalDate fechaInicioEnEmpresa;
    private ArrayList<RolesEnEmpresa> rolesEnEmpresa;
    private ArrayList<VinculacionConLaEmpresa> vinculaciones;
    private ArrayList<Disponibilidad> disponibilidades;
    
     /**
     * Constructor de Empleado a partir de JSON.
     * @param json cadena de texto con formato JSON cuya contenido representa un Empleado
     */
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
            String direccion = empleadoJson.getString("direccion");
            String telefono = empleadoJson.getString("telefono");
            String email = empleadoJson.getString("email");
            String cuentaBancaria = empleadoJson.getString("cuentaBancaria");
            LocalDate fechaInicio = LocalDate.parse(empleadoJson.getString("fechaInicioEnEmpresa"));
            
            setNif(nif);
            setNombre(nombre);
            setApellidos(apellidos);
            setDireccion(direccion);
            setTelefono(telefono);
            setEmail(email);
            setCuentaBancaria(cuentaBancaria);
            setFechaInicioEnEmpresa(fechaInicio);
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
            
        } catch(Exception ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Devuelve un Empleado, en caso de existir, a partir de su nif y contraseña.
     * @param dni el nif del Empleado
     * @param password la contraseña del Empleado
     * @return el Empleado cuyas credenciales coinciden con el dni y password
     * @throws DatosIncorrectosException si no se encuentra ningún empleado a partir de las credenciales
     */
    public static Empleado getEmpleadoPorNifYPassword(String dni, String password) throws DatosIncorrectosException {
        String datosJSON = DAOEmpleado.consultaEmpleadoPorNifYPassword(dni, password);      
        
        if (datosJSON == null) {
            throw new DatosIncorrectosException();
        }
        
        return new Empleado(datosJSON);
    }
    
    /**
     * Establece la fecha de inicio del Empleado en la empresa.
     * @param fechaInicioEnEmpresa la fecha de inicio del empleado en la empresa
     */
    public void setFechaInicioEnEmpresa(LocalDate fechaInicioEnEmpresa) {
        this.fechaInicioEnEmpresa = fechaInicioEnEmpresa;
    }

    /**
     * Comprueba si un empleado está activo en la empresa. Para ello su último vínculo debe ser
     * {@code TipoDeVinculacion.Contratado} y su última disponibilidad debe ser {@code TipoDeDisponibilidad.Trabajando}
     * @return {@code true} si el empleado está activo y {@code false} en caso contrario
     */
    public boolean estaActivo() {
        TipoDeVinculacion ultimoVinculo = getUltimoVinculo();
        TipoDeDisponibilidad ultimaDisponibilidad = getUltimaDisponibilidad();
        
        return ultimoVinculo == TipoDeVinculacion.Contratado && ultimaDisponibilidad == TipoDeDisponibilidad.Trabajando;
    }
    
    /**
     * Consulta el último ro del Empleado en la empresa.
     * @return el último rol del empleado en cuestión
     */
    public TipoDeRol getUltimoRol() {
        return rolesEnEmpresa.get(rolesEnEmpresa.size() - 1) .getRol();
    }
    
    /**
     * Consulta el último vínculo del Empleado con la empresa.
     * @return el último vínculo del empleado en cuestión
     */
    public TipoDeVinculacion getUltimoVinculo() {
        return vinculaciones.get(vinculaciones.size() - 1).getVinculo();    
    }
    
    /**
     * Consulta la última disponibilidad del Empleado en la empresa.
     * @return la última disponibilidad del empleado en cuestión
     */
    public TipoDeDisponibilidad getUltimaDisponibilidad() {
        return disponibilidades.get(disponibilidades.size() - 1).getDisponibilidad();
    }
    
   
}
