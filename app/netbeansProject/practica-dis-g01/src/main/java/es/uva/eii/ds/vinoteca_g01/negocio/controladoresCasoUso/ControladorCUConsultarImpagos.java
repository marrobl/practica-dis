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
 * Clase que implementa el controlador del caso de uso de consultar impagos
 * 
 * @author ricalba
 * @author silmont
 * @author marrobl
 * 
 */
public class ControladorCUConsultarImpagos {

    /**
     * Obtiene lista de facturas vencidas anteriores a la fecha introducida
     * 
     * @param fecha limite superior para obtener facturas
     * @return facturas 
     * @throws FechaNoVencidaException cuando no han pasado 30 dias desde {@code fecha}
     */
    public ArrayList<Factura> obtenerListaFacturas(LocalDate fecha) throws FechaNoVencidaException {
       ArrayList<Factura> facturas = Factura.getFacturasFecha(fecha);
 
       return facturas;
    }
    
}
