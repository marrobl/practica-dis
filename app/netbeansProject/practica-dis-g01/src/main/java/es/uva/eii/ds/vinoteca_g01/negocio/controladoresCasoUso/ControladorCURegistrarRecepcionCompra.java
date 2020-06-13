/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.controladoresCasoUso;

import es.uva.eii.ds.vinoteca_g01.negocio.modelos.Compra;
import es.uva.eii.ds.vinoteca_g01.negocio.modelos.LineaCompra;
import es.uva.eii.ds.vinoteca_g01.negocio.modelos.LineaPedido;
import es.uva.eii.ds.vinoteca_g01.negocio.modelos.Pedido;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.CompraNotFoundException;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.CompraYaCompletadaException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author silmont
 */
public class ControladorCURegistrarRecepcionCompra {

    public Compra getCompraNoCompletada(String id) throws CompraNotFoundException, CompraYaCompletadaException {
        Compra c = Compra.getCompraPorId(id);
        if(c.getRecibidaCompleta()) throw new CompraYaCompletadaException();
        return c;
    }

    public void setLinea(int idLinea, String idCompra) throws CompraNotFoundException {
        Compra c = Compra.getCompraPorId(idCompra);
        LineaCompra linea = c.getLineaCompraId(idLinea);
        linea.setRecibida();
        linea.setFecha();
        ArrayList<LineaPedido> lp = linea.getLineasPedido();
        for(LineaPedido lineap : lp){
            lineap.setCompletada();
            
        }
    }

    public ArrayList<LineaCompra> finRegistroLineas(String id) {
        Compra c = null;
        try {
            c = Compra.getCompraPorId(id);
        } catch (CompraNotFoundException ex) {
        }
        ArrayList<LineaCompra> listaNoFinalizadas = c.getLineasNoRecibidas();
        if (listaNoFinalizadas.isEmpty()){
            c.setRecibidaCompleta();
            c.setFechaCompraCompletada();
            
        }
        String json = c.getJSON();
        c.actualizar(json);
        return listaNoFinalizadas;
    }

    public void revisarPedidos() {
       ArrayList<Pedido> listaTramitados = Pedido.getPedidosTramitados();
       for(Pedido p : listaTramitados){
           ArrayList<LineaPedido> lineas = p.getLineasPedido();
           boolean pedidoCompleto = true;
           for(LineaPedido l : lineas){
               if(!l.getCompletada()) pedidoCompleto=false;
           }
           if(pedidoCompleto) p.setEstadoPedidoCompletado();
           //Aqui actualizamos a la BBDD
       }
    }
    
}
