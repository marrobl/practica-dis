/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.modelos;

/**
 *
 * @author richard
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
