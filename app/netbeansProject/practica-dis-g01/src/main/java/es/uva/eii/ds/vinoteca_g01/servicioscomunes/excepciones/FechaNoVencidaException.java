package es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones;

/**
 *
 * @author ricalba
 * @author silmont
 * @author marrobl
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
