package es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones;

/**
 *
 * @author ricalba
 * @author silmont
 * @author marrobl
 */
public class DatosIncorrectosException extends Exception {

    /**
     * Creates a new instance of <code>DatosIncorrectosException</code> without
     * detail message.
     */
    public DatosIncorrectosException() {
    }

    /**
     * Constructs an instance of <code>DatosIncorrectosException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DatosIncorrectosException(String msg) {
        super(msg);
    }
}
