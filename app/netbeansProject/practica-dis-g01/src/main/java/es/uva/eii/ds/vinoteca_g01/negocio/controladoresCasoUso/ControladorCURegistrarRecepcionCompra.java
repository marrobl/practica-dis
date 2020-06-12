/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.controladoresCasoUso;

import es.uva.eii.ds.vinoteca_g01.negocio.modelos.Compra;
import es.uva.eii.ds.vinoteca_g01.negocio.modelos.LineaCompra;
import es.uva.eii.ds.vinoteca_g01.negocio.modelos.LineaPedido;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.CompraNotFoundException;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.CompraYaCompletadaException;
import java.util.ArrayList;

/**
 *
 * @author silmont
 */
public class ControladorCURegistrarRecepcionCompra {

    public void getCompraNoCompletada(String id) throws CompraNotFoundException, CompraYaCompletadaException {
        Compra c = Compra.getCompraPorId(id);
        if(c.getRecibidaCompleta()) throw new CompraYaCompletadaException();
    }

    public void setLinea(LineaCompra linea) {
        linea.setRecibida();
        linea.setFecha();
        ArrayList<LineaPedido> lp = linea.getLineasPedido();
        for(LineaPedido lineap : lp){
            lineap.setCompletada();
            
        }
    }

    public ArrayList<LineaCompra> finRegistroLineas(String id) {
        Compra c = Compra.getCompraPorId(id);
        ArrayList<LineaCompra> listaNoFinalizadas = c.getLineasNoRecibidas();
        if (listaNoFinalizadas.isEmpty()){
            c.setRecibidaCompleta();
            c.setFechaCompraCompletada();
            
        }
        String json = c.getJSON();
        c.actualizar(json);
    }

    public void revisarPedidos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
