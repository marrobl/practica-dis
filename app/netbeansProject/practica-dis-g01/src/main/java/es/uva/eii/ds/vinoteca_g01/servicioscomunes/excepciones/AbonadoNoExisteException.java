package es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones;

/**
 *
 * @author ricalba
 * @author silmont
 * @author marrobl
 */
public class AbonadoNoExisteException extends Exception {

    /**
     * Creates a new instance of <code>AbonadoNoExisteException</code> without
     * detail message.
     */
    public AbonadoNoExisteException() {
    }

    /**
     * Constructs an instance of <code>AbonadoNoExisteException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public AbonadoNoExisteException(String msg) {
        super(msg);
    }
}
