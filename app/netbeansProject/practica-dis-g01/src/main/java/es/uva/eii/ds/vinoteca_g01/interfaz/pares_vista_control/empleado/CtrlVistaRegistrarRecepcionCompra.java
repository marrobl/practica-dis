/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.interfaz.pares_vista_control.empleado;
import es.uva.eii.ds.vinoteca_g01.negocio.controladoresCasoUso.ControladorCURegistrarRecepcionCompra;

/**
 *
 * @author silmont
 */
public class CtrlVistaRegistrarRecepcionCompra {
    
    private final VistaRegistrarRecepcionCompra vista;
    private final ControladorCURegistrarRecepcionCompra controladorCU;
    private final String ERROR_ID_VACIO = "El id de la compra no puede estar vacío";
    
    public CtrlVistaRegistrarRecepcionCompra(VistaRegistrarRecepcionCompra vista){
        this.vista = vista;
        controladorCU = new ControladorCURegistrarRecepcionCompra();
    }

    void procesaEventoIntroduceId() {
        String id = vista.getId();
        if(id.isEmpty()){
            vista.mostrarMensajeError(ERROR_ID_VACIO);
        }else{
            vista.esconderMensajeError();
        }
        try{
            controladorCU.getCompraNoCompletada(id);
            
        }catch(IllegalArgumentException e){
            
        }
    }
    
}
