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
public class CompraNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>CompraNotFoundException</code> without
     * detail message.
     */
    public CompraNotFoundException() {
    }

    /**
     * Constructs an instance of <code>CompraNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CompraNotFoundException(String msg) {
        super(msg);
    }
}
