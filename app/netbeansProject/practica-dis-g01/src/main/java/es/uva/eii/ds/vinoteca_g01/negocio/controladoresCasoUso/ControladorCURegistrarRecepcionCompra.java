/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.controladoresCasoUso;

import es.uva.eii.ds.vinoteca_g01.negocio.modelos.Compra;

/**
 *
 * @author silmont
 */
public class ControladorCURegistrarRecepcionCompra {

    public void getCompraNoCompletada(String id) {
        Compra c = Compra.getCompraPorId(id);
    }
    
}
