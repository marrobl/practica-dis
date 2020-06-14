package es.uva.eii.ds.vinoteca_g01.interfaz.pares_vista_control.empleado;

import es.uva.eii.ds.vinoteca_g01.interfaz.GestorDeInterfazDeUsuario;

/**
 *
 * @author ricalba
 * @author silmont
 * @author marrobl
 */
public class CtrlVistaPersonalContabilidad {
    
    private final VistaPersonalContabilidad vista;

    public CtrlVistaPersonalContabilidad(VistaPersonalContabilidad vista) {
        this.vista = vista;
    }

    public void procesaEventoConsultarImpagos() {
        GestorDeInterfazDeUsuario.getInstance().cambiarAVistaConsultarImpagos();
    }

    public void procesaEventoCerrarSesion() {
        GestorDeInterfazDeUsuario.getInstance().cerrarSesion();
    }
    
}
