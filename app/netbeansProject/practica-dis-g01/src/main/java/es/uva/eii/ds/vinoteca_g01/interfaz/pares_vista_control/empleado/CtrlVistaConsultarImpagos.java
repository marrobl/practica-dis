/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.interfaz.pares_vista_control.empleado;

import es.uva.eii.ds.vinoteca_g01.negocio.controladoresCasoUso.ControladorCUConsultarImpagos;
import es.uva.eii.ds.vinoteca_g01.negocio.modelos.Factura;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.FechaNoVencidaException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase que implementa el controlador de la vista del caso de uso consultar impagos
 * 
 * @author ricalba
 * @author silmont
 * @author marrobl
 */
public class CtrlVistaConsultarImpagos {
    
    private final VistaConsultarImpagos vista;
    private final ControladorCUConsultarImpagos controladorCasoUso;
    private final String ERROR_FECHA_VACIA = "La fecha no puede estar vacía";
    private final String ERROR_FECHA_FORMATO = "El formato de la fecha no es correcto";
    private final String ERROR_FECHA_NO_VENCIDA = "No han pasado 30 días, las facturas no están vencidas";
     
    /**
     * Crea un controlador de vista
     * 
     * @param vista asociada 
     */
    public CtrlVistaConsultarImpagos(VistaConsultarImpagos vista) {
        this.vista = vista;
        controladorCasoUso = new ControladorCUConsultarImpagos();
    }

    /**
     * Procesa la fecha introducida por el usuario en la vista
     * y obtiene la lista de facturas vencidas de los dias
     * anteriores a esa fecha y comprueba que la fecha introducida no sea
     * una cadena vacia
     * 
     */
    public void procesaEventoIntroducirFecha() {
        String fecha = vista.getFecha();
        if(fecha.isEmpty()){
            vista.mostrarMensajeError(ERROR_FECHA_VACIA);
        }else {
            vista.esconderMensajeError();
            try{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date = LocalDate.parse(fecha, formatter);
                ArrayList<Factura> facturas = controladorCasoUso.obtenerListaFacturas(date);
                vista.mostrarFacturasImpagos(facturas);
            } catch (DateTimeParseException ex){
                vista.mostrarMensajeError(ERROR_FECHA_FORMATO);
            } catch (FechaNoVencidaException ex){
                vista.mostrarAvisoNoVencido(ERROR_FECHA_NO_VENCIDA);
            }
        }
    }
}
