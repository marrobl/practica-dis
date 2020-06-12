/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.modelos;

/**
 *
 * @author silmont
 */
public class LineaPedido {
    
    private int id;
    private int unidades;
    private boolean completada;
    private int codigoReferencia;
    private int numeroPedido;
    private int idLineaCompra;

    public void setCompletada() {
        completada=true;
    }
    
}
