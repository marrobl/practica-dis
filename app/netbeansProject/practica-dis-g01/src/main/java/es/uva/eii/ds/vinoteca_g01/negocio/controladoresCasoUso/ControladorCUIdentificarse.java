/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.controladoresCasoUso;

import es.uva.eii.ds.vinoteca_g01.negocio.modelos.Empleado;
import es.uva.eii.ds.vinoteca_g01.negocio.modelos.TipoDeRol;

/**
 *
 * @author richard
 */
public class ControladorCUIdentificarse {

    public TipoDeRol identificarEmpleado(String dni, String password) {
        Empleado.getEmpleadoPorNifYPassword(dni, password);
        
        return null;
    }
    
}
