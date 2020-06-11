/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.modelos;

import es.uva.eii.ds.vinoteca_g01.persistencia.daos.DAOCompra;
import java.util.Iterator;

/**
 *
 * @author silmont
 */
public class Compra {

    public static Compra getCompraPorId(String id) {
        String datosJSON = DAOCompra.consultaCompraPorId(id);
        Compra c = new Compra(datosJSON);
        return c;
    }

    private Compra(String datosJSON) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getBodega() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Iterator getLineasCompra() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
