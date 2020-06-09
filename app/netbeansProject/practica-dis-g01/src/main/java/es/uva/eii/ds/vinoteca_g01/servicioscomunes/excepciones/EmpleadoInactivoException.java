/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones;

/**
 *
 * @author richard
 */
public class EmpleadoInactivoException extends Exception {

    /**
     * Creates a new instance of <code>EmpleadoInactivoException</code> without
     * detail message.
     */
    public EmpleadoInactivoException() {
    }

    /**
     * Constructs an instance of <code>EmpleadoInactivoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EmpleadoInactivoException(String msg) {
        super(msg);
    }
}
