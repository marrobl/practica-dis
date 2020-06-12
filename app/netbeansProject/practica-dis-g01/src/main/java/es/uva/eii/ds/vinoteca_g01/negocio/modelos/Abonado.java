/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.modelos;

import es.uva.eii.ds.vinoteca_g01.persistencia.daos.DAOAbonado;

/**
 *
 * @author richard
 */
public class Abonado extends Persona {

    public static Abonado getAbonado(int numAbonado) {
        String datosJSON = DAOAbonado.consultarAbonadoPorId(numAbonado);
        
        return null;
    }
    
}
