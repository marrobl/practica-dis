package es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones;

/**
 *
 * @author ricalba
 * @author silmont
 * @author marrobl
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
