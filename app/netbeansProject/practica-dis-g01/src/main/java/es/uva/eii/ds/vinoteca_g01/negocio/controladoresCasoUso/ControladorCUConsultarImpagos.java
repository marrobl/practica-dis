/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.controladoresCasoUso;

import es.uva.eii.ds.vinoteca_g01.negocio.modelos.Factura;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.FechaNoVencidaException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author maria
 */
public class ControladorCUConsultarImpagos {

    public ArrayList<Factura> obtenerListaFacturas(LocalDate fecha) throws FechaNoVencidaException {
       return Factura.getFacturasFecha(fecha);
    }
    
}
