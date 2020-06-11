/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.controladoresCasoUso;

import es.uva.eii.ds.vinoteca_g01.negocio.modelos.Empleado;
import es.uva.eii.ds.vinoteca_g01.negocio.modelos.TipoDeRol;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.DatosIncorrectosException;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.EmpleadoInactivoException;

/**
 *
 * @author richard
 */
public class ControladorCUIdentificarse {

    public TipoDeRol identificarEmpleado(String dni, String password) throws DatosIncorrectosException, EmpleadoInactivoException {
        Empleado empleado = Empleado.getEmpleadoPorNifYPassword(dni, password);
        
        if (!empleado.estaActivo()) {
            throw new EmpleadoInactivoException();
        }
        
        return empleado.getUltimoRol();
    }
    
}
