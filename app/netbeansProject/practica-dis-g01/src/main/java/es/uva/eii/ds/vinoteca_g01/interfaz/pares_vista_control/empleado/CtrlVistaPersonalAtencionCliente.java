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
public class CtrlVistaPersonalAtencionCliente {
    
    private final VistaPersonalAtencionCliente vista;
    
    public CtrlVistaPersonalAtencionCliente(VistaPersonalAtencionCliente vista) {
        this.vista = vista;
    }

    public void procesaEventoCrearPedidoAbonado() {
        GestorDeInterfazDeUsuario.getInstance().cambiarAVistaCrearPedidoAbonado();
    }

    void procesaEventoCerrarSesion() {
        GestorDeInterfazDeUsuario.getInstance().cerrarSesion();
    }
}
