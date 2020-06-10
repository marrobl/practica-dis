/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.interfaz.pares_vista_control.empleado;

import es.uva.eii.ds.vinoteca_g01.negocio.controladoresCasoUso.ControladorCUConsultarImpagos;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author maria
 */
public class CtrlVistaConsultarImpagos {
    
    private final VistaConsultarImpagos vista;
    private final ControladorCUConsultarImpagos controladorCasoUso;
    private final String ERROR_FECHA_VACIA = "La fecha no puede estar vacía";
    private final String ERROR_FECHA_FORMATO = "El formato de la fecha no es correcto";
    
    public CtrlVistaConsultarImpagos(VistaConsultarImpagos vista) {
        this.vista = vista;
        controladorCasoUso = new ControladorCUConsultarImpagos();
    }

    public void procesaEventoIntroducirFecha() {
        String fecha = vista.getFecha();
        if(fecha.isEmpty()){
            vista.mostrarMensajeError(ERROR_FECHA_VACIA);
        }else {
            vista.esconderMensajeError();
            try{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date = LocalDate.parse(fecha, formatter);
            } catch (DateTimeParseException ex){
                vista.mostrarMensajeError(ERROR_FECHA_FORMATO);
            }
        }
    }
}
