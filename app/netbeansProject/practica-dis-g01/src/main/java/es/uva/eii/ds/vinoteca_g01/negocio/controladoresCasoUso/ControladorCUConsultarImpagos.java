/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.controladoresCasoUso;

import es.uva.eii.ds.vinoteca_g01.negocio.modelos.Factura;
import es.uva.eii.ds.vinoteca_g01.negocio.modelos.Pedido;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.FechaNoVencidaException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author maria
 */
public class ControladorCUConsultarImpagos {

    public HashMap<Integer,ArrayList<Pedido>> obtenerListaFacturas(LocalDate fecha) throws FechaNoVencidaException {
       int numeroFactura;
       ArrayList<Factura> facturas = Factura.getFacturasFecha(fecha);
       HashMap<Integer, ArrayList<Pedido>> facturaPedidos =  new HashMap<>();
       
       for(Factura f: facturas){
           numeroFactura = f.getNumeroFactura();
           ArrayList<Pedido> pedidos = Pedido.getPedidosNumFactura(numeroFactura);
           if(pedidos.size() > 0) facturaPedidos.put(numeroFactura, pedidos);
       }
       return facturaPedidos;
    }
    
}
