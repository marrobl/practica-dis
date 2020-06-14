package es.uva.eii.ds.vinoteca_g01.negocio.modelos;

/**
 * Clase que representa una Persona.
 * 
 * @author ricalba
 * @author silmont
 * @author marrobl
 */
public class Persona {
    
    private String nif;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String email;
    private String cuentaBancaria;
    
    /**
     * Consulta el NIF de la Persona.
     * @return el NIF de la persona en cuestión
     */
    public String getNif() {
        return nif;
    }

    /**
     * Establece el nif de la Persona.
     * @param nif el NIF a establecer
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * Consulta el nombre de la Persona.
     * @return el nombre de la persona en cuestión
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el nombre de la Persona.
     * @param nombre el nombre a establecer
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Consulta los apellidos de la Persona.
     * @return los apellidos de la persona en cuestión
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos de la Persona
     * @param apellidos los apellidos a establecer
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    /**
     * Consulta la dirección de la Persona.
     * @return la dirección de la persona en cuestión
     */
    public String getDireccion() {
        return direccion;
    }
    
    /**
     * Establece la dirección del la Persona. 
     * @param direccion la dirección a establecer
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Consulta el teléfono de la Persona.
     * @return el telefono de la persona en cuestión
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el teléfono de la Persona.
     * @param telefono el teléfono a establecer
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Consulta el email de la Persona.
     * @return el email de la persona en cuestión
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el email de la Persona.
     * @param email el email a establecer
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Consulta la cuenta bancaria de la Persona.
     * @return 
     */
    public String getCuentaBancaria() {
        return cuentaBancaria;
    }

    /**
     * Establece la cuenta bancaria de la Persona.
     * @param cuentaBancaria la cuenta bancaria a establecer
     */
    public void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }
}
