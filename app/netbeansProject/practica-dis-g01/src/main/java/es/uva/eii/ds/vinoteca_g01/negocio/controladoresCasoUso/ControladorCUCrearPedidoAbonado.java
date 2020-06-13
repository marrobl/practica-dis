/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.controladoresCasoUso;

import es.uva.eii.ds.vinoteca_g01.negocio.modelos.Abonado;
import es.uva.eii.ds.vinoteca_g01.negocio.modelos.Pedido;
import es.uva.eii.ds.vinoteca_g01.negocio.modelos.Referencia;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.ImpagosAbonadoException;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.ReferenciaNoDisponibleException;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.ReferenciaNoExisteException;
import java.time.LocalDate;

/**
 *
 * @author richard
 */
public class ControladorCUCrearPedidoAbonado {
    
    private Abonado abonado;
    private Pedido pedido;

    public Abonado getAbonadoPorId(int numAbonado) {
        abonado = Abonado.getAbonado(numAbonado);
        
        return abonado;
    }

    public void compruebaImpagosAbonado() throws ImpagosAbonadoException {
        boolean impagos = Pedido.hayPedidosVencidosPorNumeroAbonado(abonado.getNumeroAbonado());
        
        if (impagos) {
            throw new ImpagosAbonadoException();
        }
        else {
            pedido = new Pedido();
        }
    }

    public void comprobarReferencia(int numRef, int cantidad) throws ReferenciaNoExisteException, ReferenciaNoDisponibleException {
        Referencia referencia = Referencia.getReferenciaPorCodigo(numRef);
        
        if (!referencia.isDisponible()) {
            throw new ReferenciaNoDisponibleException();
        }
        
        pedido.crearLineaPedido(referencia, cantidad);
    }

    public void registrarPedido() {
        pedido.setFechaRealizacion(LocalDate.now());
        pedido.calcularImporte();
        pedido.setNumeroAbonado(abonado.getNumeroAbonado());
        String json = pedido.toJson();
        Pedido.registrarPedido(json);
    }
}
