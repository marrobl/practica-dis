/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.interfaz.pares_vista_control.empleado;

import es.uva.eii.ds.vinoteca_g01.negocio.controladoresCasoUso.ControladorCUIdentificarse;
import es.uva.eii.ds.vinoteca_g01.negocio.modelos.TipoDeRol;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.DatosIncorrectosException;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.EmpleadoInactivoException;

/**
 *
 * @author richard
 */
public class CtrlVistaIdentificarse {
    
    private final VistaIdentificarse vista;
    private final ControladorCUIdentificarse controladorCasoUso;
    private final String ERROR_CADENA_VACIA = "Los campos de texto no pueden estar vacíos";
    
    public CtrlVistaIdentificarse(VistaIdentificarse vista) {
        this.vista = vista;
        controladorCasoUso = new ControladorCUIdentificarse();
    }

    public void procesaEventoIdentificarse() {
        String dni = vista.getDni();
        String password = vista.getPassword();
        
        boolean dniVacio = dni.isEmpty();
        boolean passwordVacio = password.isEmpty();
        
        if (dniVacio || passwordVacio){
            vista.mostrarMensajeError(ERROR_CADENA_VACIA);
        }
        else {
            vista.esconderMensajeError();
            
            TipoDeRol rol = controladorCasoUso.identificarEmpleado(dni, password);
        }
    }
    
}
