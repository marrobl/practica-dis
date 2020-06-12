/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.controladoresCasoUso;

import es.uva.eii.ds.vinoteca_g01.negocio.modelos.Compra;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.CompraNotFoundException;

/**
 *
 * @author silmont
 */
public class ControladorCURegistrarRecepcionCompra {

    public void getCompraNoCompletada(String id) throws CompraNotFoundException {
        Compra c = Compra.getCompraPorId(id);
    }
    
}
