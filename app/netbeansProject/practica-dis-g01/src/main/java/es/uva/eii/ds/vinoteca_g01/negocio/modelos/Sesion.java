package es.uva.eii.ds.vinoteca_g01.negocio.modelos;

/**
 *
 * @author ricalba
 * @author silmont
 * @author marrobl
 */
public class Sesion {
    private static Sesion sesion;

    public static Sesion getInstance() {
        if (sesion == null) {
            sesion = new Sesion();
        }

        return sesion;
    }

    private Empleado empleado;
    
    private Sesion() {
    }
    
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    public Empleado getEmpleado() {
        return empleado;
    }
}
