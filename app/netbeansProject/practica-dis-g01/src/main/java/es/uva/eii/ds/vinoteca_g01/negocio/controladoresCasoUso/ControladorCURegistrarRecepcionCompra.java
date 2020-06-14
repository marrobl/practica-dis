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
import es.uva.eii.ds.vinoteca_g01.persistencia.daos.DAOLineaCompra;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.CompraNotFoundException;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.CompraYaCompletadaException;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author silmont
 */
public class ControladorCURegistrarRecepcionCompra {
    
    Compra compraRegistrar;

    public Compra getCompraNoCompletada(String id) throws CompraNotFoundException, CompraYaCompletadaException {
        compraRegistrar = Compra.getCompraPorId(id);
        if(compraRegistrar.getRecibidaCompleta()) throw new CompraYaCompletadaException();
        return compraRegistrar;
    }

    public void setLinea(int idLinea) throws CompraNotFoundException {
        LineaCompra linea = compraRegistrar.getLineaCompraId(idLinea);
        linea.setRecibida();
        linea.setFecha();
        ArrayList<LineaPedido> lp = linea.getLineasPedido();
        for(LineaPedido lineap : lp){
            lineap.setCompletada();
            
        }
    }

    public ArrayList<LineaCompra> finRegistroLineas() {
        ArrayList<LineaCompra> listaNoFinalizadas = compraRegistrar.getLineasNoRecibidas();
        if (listaNoFinalizadas.isEmpty()){
            compraRegistrar.setRecibidaCompleta();
            compraRegistrar.setFechaCompraCompletada();
            String json = compraRegistrar.toJSON();
            compraRegistrar.actualizar(json);

        }else{
            Iterator it = compraRegistrar.getLineasCompra();
            while(it.hasNext()){
                LineaCompra l = (LineaCompra)it.next();
                String json = l.toJSON();
                LineaCompra.actualizar(json);
            }
        }
 
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
           String json = p.toJson();
           p.actualizar(json);
       }
    }
    
}
