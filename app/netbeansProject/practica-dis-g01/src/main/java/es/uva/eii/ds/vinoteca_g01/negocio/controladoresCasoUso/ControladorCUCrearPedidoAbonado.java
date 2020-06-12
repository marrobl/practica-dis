/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.controladoresCasoUso;

import es.uva.eii.ds.vinoteca_g01.negocio.modelos.Abonado;
import es.uva.eii.ds.vinoteca_g01.negocio.modelos.Pedido;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.ImpagosAbonadoException;

/**
 *
 * @author richard
 */
public class ControladorCUCrearPedidoAbonado {
    
    private Abonado abonado;

    public Abonado getAbonadoPorId(int numAbonado) {
        abonado = Abonado.getAbonado(numAbonado);
        
        return abonado;
    }

    public void compruebaImpagosAbonado() throws ImpagosAbonadoException {
        boolean impagos = Pedido.hayPedidosVencidosPorNumeroAbonado(abonado.getNumeroAbonado());
        
        System.out.println(impagos);
        if (impagos) {
            throw new ImpagosAbonadoException();
        }
        else {
            Pedido pedido = new Pedido();
        }
    }
    
}
