/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones;

/**
 *
 * @author maria
 */
public class FechaNoVencidaException extends Exception {

    /**
     * Creates a new instance of <code>FechaNoVencidaException</code> without
     * detail message.
     */
    public FechaNoVencidaException() {
    }

    /**
     * Constructs an instance of <code>FechaNoVencidaException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public FechaNoVencidaException(String msg) {
        super(msg);
    }
}
