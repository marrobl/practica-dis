/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.modelos;

import es.uva.eii.ds.vinoteca_g01.persistencia.daos.DAOEmpleado;

/**
 *
 * @author richard
 */
public class Empleado {

    public static void getEmpleadoPorNifYPassword(String dni, String password) {
        String datosJSON = DAOEmpleado.consultaEmpleadoPorNifYPassword(dni, password);
    }
    
}
