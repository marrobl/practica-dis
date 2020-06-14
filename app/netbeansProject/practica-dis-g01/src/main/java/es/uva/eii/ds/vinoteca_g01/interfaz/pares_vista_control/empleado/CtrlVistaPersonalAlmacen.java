/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.interfaz.pares_vista_control.empleado;

import es.uva.eii.ds.vinoteca_g01.interfaz.GestorDeInterfazDeUsuario;

/**
 *
 * @author richard
 */
public class CtrlVistaPersonalAlmacen {
    
    private final VistaPersonalAlmacen vista;
    
    public CtrlVistaPersonalAlmacen(VistaPersonalAlmacen vista) {
        this.vista = vista;
    }

    public void procesaEventoCerrarSesion() {
        GestorDeInterfazDeUsuario.getInstance().cerrarSesion();
    }

    void procesaEventoRegistrarRecepcionDeCompra() {
        GestorDeInterfazDeUsuario.getInstance().cambiarAVistaRegistarRecepcionCompra();
    }
    
}
