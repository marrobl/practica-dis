package es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones;

/**
 *
 * @author ricalba
 * @author silmont
 * @author marrobl
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
