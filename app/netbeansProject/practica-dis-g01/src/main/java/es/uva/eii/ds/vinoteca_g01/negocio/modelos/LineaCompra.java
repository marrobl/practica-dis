/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.modelos;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author silmont
 */
public class LineaCompra {
    
    private int id;
    private int unidades;
    private boolean recibida;
    private LocalDate fechaRecepcion;
    private int idCompra;
    private int codigoReferencia;

    public int getCodigoReferencia() {
        return codigoReferencia;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setRecibida() {
        recibida=true;
    }

    public void setFecha() {
        fechaRecepcion = LocalDate.now();
    }

    public ArrayList<LineaPedido> getLineasPedido() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean getRecibida() {
        return recibida;
    }
    
}
