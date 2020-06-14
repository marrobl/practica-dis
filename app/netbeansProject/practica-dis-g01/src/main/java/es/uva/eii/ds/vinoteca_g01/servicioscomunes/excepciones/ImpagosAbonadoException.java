package es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones;

/**
 *
 * @author ricalba
 * @author silmont
 * @author marrobl
 */
public class ImpagosAbonadoException extends Exception {

    /**
     * Creates a new instance of <code>ImpagosAbonadoException</code> without
     * detail message.
     */
    public ImpagosAbonadoException() {
    }

    /**
     * Constructs an instance of <code>ImpagosAbonadoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ImpagosAbonadoException(String msg) {
        super(msg);
    }
}
