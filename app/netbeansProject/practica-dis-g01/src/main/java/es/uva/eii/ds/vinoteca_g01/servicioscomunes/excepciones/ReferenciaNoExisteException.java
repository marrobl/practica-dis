package es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones;

/**
 *
 * @author ricalba
 * @author silmont
 * @author marrobl
 */
public class ReferenciaNoExisteException extends Exception {

    /**
     * Creates a new instance of <code>ReferenciaNoExisteException</code>
     * without detail message.
     */
    public ReferenciaNoExisteException() {
    }

    /**
     * Constructs an instance of <code>ReferenciaNoExisteException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public ReferenciaNoExisteException(String msg) {
        super(msg);
    }
}
