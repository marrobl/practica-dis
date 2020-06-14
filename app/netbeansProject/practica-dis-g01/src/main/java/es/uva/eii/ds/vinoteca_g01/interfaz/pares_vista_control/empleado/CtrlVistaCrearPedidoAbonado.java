/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.interfaz.pares_vista_control.empleado;

import es.uva.eii.ds.vinoteca_g01.negocio.controladoresCasoUso.ControladorCUCrearPedidoAbonado;
import es.uva.eii.ds.vinoteca_g01.negocio.modelos.Abonado;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.AbonadoNoExisteException;
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
    private final String ERROR_CADENA_VACIA_O_NO_VALIDA = "Por favor, introduzca datos válidos en los campos de texto";
    private final String ERROR_NO_EXISTE_ABONADO_CON_ESE_NUMERO = "No existe ningún abonado con ese número";
    private final String ERROR_ABONADO_CON_PLAZOS_VENCIDOS = "El abonado tiene algún plazo de pago de pedidos anteriores vencido";

    public CtrlVistaCrearPedidoAbonado(VistaCrearPedidoAbonado vista) {
        this.vista = vista;
        controladorCasoUso = new ControladorCUCrearPedidoAbonado();
    }

    public void procesaEventoIntroduceNumeroAbonado() {
        try {
            vista.esconderError();
            int numAbonado = Integer.parseInt(vista.getNumeroAbonado());

            Abonado abonado = controladorCasoUso.getAbonadoPorId(numAbonado);

            vista.mostrarAbonado(abonado);
            vista.habilitarCancelar();
            vista.deshabilitarCamposDeBusqueda();
        } catch (NumberFormatException ex) {
            vista.mostrarError(ERROR_CADENA_VACIA_O_NO_VALIDA);
        } catch (AbonadoNoExisteException ex) {
            vista.mostrarError(ERROR_NO_EXISTE_ABONADO_CON_ESE_NUMERO);
        }

    }

    public void procesaEventoConfirmarAbonado() {
        try {
            vista.deshabilitarCancelar();
            vista.deshabilitarCamposAbonado();
            
            controladorCasoUso.compruebaImpagosAbonado();
            vista.habilitarCamposPedido();
        } catch (ImpagosAbonadoException ex) {
            vista.mostrarError(ERROR_ABONADO_CON_PLAZOS_VENCIDOS);
            vista.mostrarVentanaNotificacion();
        }
    }
    
    public void procesaEventoAbonadoIncorrecto() {
        vista.limpiarNumeroAbonadoTextField();
        vista.limpiarCamposAbonado();
        vista.deshabilitarCancelar();
        vista.deshabilitarCamposAbonado();
        vista.habilitarCamposDeBusqueda();
    }

    public void procesaEventoIntroducirDatosPedido() {

        try {
            int numRef = Integer.parseInt(vista.getNumReferencia());
            int cantidad = Integer.parseInt(vista.getCantidad());
            
            if (cantidad < 1) throw new NumberFormatException();
            
            try {
                controladorCasoUso.comprobarReferencia(numRef, cantidad);
                vista.habilitarFinalizarPedido();
                vista.esconderError();
                vista.deshabilitarCamposPedido();
                vista.habilitarCancelar();
            } catch (ReferenciaNoExisteException ex) {
                vista.mostrarError(ERROR_REFERENCIA_NO_EXISTE);
            } catch (ReferenciaNoDisponibleException ex) {
                vista.mostrarError(ERROR_REFERENCIA_NO_DISPONIBLE);
            }
        } catch (NumberFormatException ex) {
            vista.mostrarError(ERROR_CADENA_VACIA_O_NO_VALIDA);
        }
    }

    public void procesaEventoFinalizarPedido() {
        controladorCasoUso.registrarPedido();
        vista.deshabilitarFinalizarPedido();
        vista.deshabilitarCancelar();
        vista.habilitarCamposDeBusqueda();
        vista.limpiarNumeroAbonadoTextField();
        vista.limpiarCamposAbonado();
        vista.limpiarCamposPedido();
    }
    
    public void procesaEventoNoFinalizar() {
        vista.deshabilitarFinalizarPedido();
        vista.habilitarCamposPedido();
        vista.limpiarCamposPedido();
    }

    public void procesaEventoCancelar() {
        vista.limpiarNumeroAbonadoTextField();
        vista.limpiarCamposAbonado();
        vista.limpiarCamposPedido();
        vista.deshabilitarCamposAbonado();
        vista.deshabilitarFinalizarPedido();
        vista.habilitarCamposDeBusqueda();
    }
}
