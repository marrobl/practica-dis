/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.interfaz.pares_vista_control.empleado;

import es.uva.eii.ds.vinoteca_g01.negocio.controladoresCasoUso.ControladorCUCrearPedidoAbonado;
import es.uva.eii.ds.vinoteca_g01.negocio.modelos.Abonado;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.ImpagosAbonadoException;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.ReferenciaNoDisponibleException;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.ReferenciaNoExisteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author richard
 */
public class CtrlVistaCrearPedidoAbonado {

    private final VistaCrearPedidoAbonado vista;
    private final ControladorCUCrearPedidoAbonado controladorCasoUso;
    private final String ERROR_REFERENCIA_NO_EXISTE = "La referencia dada no existe";
    private final String ERROR_REFERENCIA_NO_DISPONIBLE = "La referencia dada no está disponible";

    public CtrlVistaCrearPedidoAbonado(VistaCrearPedidoAbonado vista) {
        this.vista = vista;
        controladorCasoUso = new ControladorCUCrearPedidoAbonado();
    }

    public void procesaEventoIntroduceNumeroAbonado() {
        try {
            int numAbonado = Integer.parseInt(vista.getNumeroAbonado());

            Abonado abonado = controladorCasoUso.getAbonadoPorId(numAbonado);
            vista.mostrarAbonado(abonado);
        } catch (NumberFormatException ex) {
            // TODO Tratar error
        }

    }

    public void procesaEventoConfirmarAbonado() {
        try {
            controladorCasoUso.compruebaImpagosAbonado();
            vista.habilitarCamposPedido();
        } catch (ImpagosAbonadoException ex) {
            System.out.println("Tratar excepcion");
        }
    }

    public void procesaEventoIntroducirDatosPedido() {

        try {
            int numRef = Integer.parseInt(vista.getNumReferencia());
            int cantidad = Integer.parseInt(vista.getCantidad());
            
            try {
                controladorCasoUso.comprobarReferencia(numRef, cantidad);
                vista.habilitarFinalizarPedido();
            } catch (ReferenciaNoExisteException ex) {
                vista.mostrarError(ERROR_REFERENCIA_NO_EXISTE);
            } catch (ReferenciaNoDisponibleException ex) {
                vista.mostrarError(ERROR_REFERENCIA_NO_DISPONIBLE);
            }
        } catch (NumberFormatException ex) {
            System.out.println("Tratar excepcion");
        }
    }

    void procesaEventoFinalizarPedido() {
        controladorCasoUso.registrarPedido();
    }

}
