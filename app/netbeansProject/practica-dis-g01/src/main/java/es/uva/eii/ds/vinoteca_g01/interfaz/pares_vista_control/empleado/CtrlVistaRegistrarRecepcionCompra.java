/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.interfaz.pares_vista_control.empleado;
import es.uva.eii.ds.vinoteca_g01.negocio.controladoresCasoUso.ControladorCURegistrarRecepcionCompra;
import es.uva.eii.ds.vinoteca_g01.negocio.modelos.LineaCompra;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.CompraNotFoundException;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.CompraYaCompletadaException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author silmont
 */
public class CtrlVistaRegistrarRecepcionCompra {
    
    private final VistaRegistrarRecepcionCompra vista;
    private final ControladorCURegistrarRecepcionCompra controladorCU;
    private final String ERROR_ID_VACIO = "El id de la compra no puede estar vacío";
    private final String ERROR_COMPRA_COMPLETA = "Esta compra ya está completada";
    private final String ERROR_COMPRA_NOT_FOUND = "No existe una compra con ese id en el sistema";
    
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
            
        }catch(CompraNotFoundException e){
            vista.mostrarMensajeError(ERROR_COMPRA_NOT_FOUND);
        }catch (CompraYaCompletadaException ex) {
            vista.mostrarMensajeError(ERROR_COMPRA_COMPLETA);
        }
    }

    void procesaEventoSeleccionaLinea(Object item) {
        LineaCompra linea = (LineaCompra)item;
        controladorCU.setLinea(linea);
    }

    void procesaEventoFinalizarRegistroLineas() {
        String id = vista.getId();
        ArrayList<LineaCompra> l = controladorCU.finRegistroLineas(id);
        if(!l.isEmpty()) vista.mostrarLineasNoCompletadas(l);
        controladorCU.revisarPedidos();

    }


    
}
