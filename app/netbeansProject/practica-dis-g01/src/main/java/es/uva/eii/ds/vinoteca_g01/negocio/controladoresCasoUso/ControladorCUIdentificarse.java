package es.uva.eii.ds.vinoteca_g01.negocio.controladoresCasoUso;

import es.uva.eii.ds.vinoteca_g01.negocio.modelos.Empleado;
import es.uva.eii.ds.vinoteca_g01.negocio.modelos.Sesion;
import es.uva.eii.ds.vinoteca_g01.negocio.modelos.TipoDeRol;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.DatosIncorrectosException;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.EmpleadoInactivoException;

/**
 *
 * @author ricalba
 * @author silmont
 * @author marrobl
 */
public class ControladorCUIdentificarse {

    public TipoDeRol identificarEmpleado(String dni, String password) throws DatosIncorrectosException, EmpleadoInactivoException {
        Empleado empleado = Empleado.getEmpleadoPorNifYPassword(dni, password);
        
        if (!empleado.estaActivo()) {
            throw new EmpleadoInactivoException();
        }
        
        Sesion.getInstance().setEmpleado(empleado);
        
        return empleado.getUltimoRol();
    }
    
}
