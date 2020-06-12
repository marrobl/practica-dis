/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.controladoresCasoUso;

import es.uva.eii.ds.vinoteca_g01.negocio.modelos.Abonado;

/**
 *
 * @author richard
 */
public class ControladorCUCrearPedidoAbonado {

    public Abonado getAbonadoPorId(int numAbonado) {
        Abonado abonado = Abonado.getAbonado(numAbonado);
    }
    
}
