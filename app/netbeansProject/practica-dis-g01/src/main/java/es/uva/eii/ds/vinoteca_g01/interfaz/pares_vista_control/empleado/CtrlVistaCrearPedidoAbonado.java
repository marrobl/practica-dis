/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.interfaz.pares_vista_control.empleado;

import es.uva.eii.ds.vinoteca_g01.negocio.controladoresCasoUso.ControladorCUCrearPedidoAbonado;
import es.uva.eii.ds.vinoteca_g01.negocio.modelos.Abonado;

/**
 *
 * @author richard
 */
public class CtrlVistaCrearPedidoAbonado {
    
    private final VistaCrearPedidoAbonado vista;
    private final ControladorCUCrearPedidoAbonado controladorCasoUso;
    
    public CtrlVistaCrearPedidoAbonado(VistaCrearPedidoAbonado vista) {
        this.vista = vista;
        controladorCasoUso = new ControladorCUCrearPedidoAbonado();
    }

    public void procesaEventoIntroduceNumeroAbonado() {
        try {
            int numAbonado = Integer.parseInt(vista.getNumeroAbonado());
            
            Abonado abonado = controladorCasoUso.getAbonadoPorId(numAbonado);
        } catch (NumberFormatException ex) {
            // TODO Tratar error
        }
        
    }
    
}
