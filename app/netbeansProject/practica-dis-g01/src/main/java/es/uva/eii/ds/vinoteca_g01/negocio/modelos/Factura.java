/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.modelos;

import es.uva.eii.ds.vinoteca_g01.persistencia.daos.DAOFactura;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.FechaNoVencidaException;
import java.time.LocalDate;
import java.util.ArrayList;
import static java.time.temporal.ChronoUnit.DAYS;

/**
 *
 * @author maria
 */




public class Factura {
    private static boolean comprobarFecha(LocalDate fecha) {
        boolean ok = false;
        long daysBetween = DAYS.between(fecha, LocalDate.now());
        if(daysBetween>=30) ok = true;
        return ok;
    }
    public static ArrayList<Factura> getFacturasFecha(LocalDate fecha) throws FechaNoVencidaException{
       boolean ok = comprobarFecha(fecha);
       if( ok == false){
           throw new FechaNoVencidaException();
       }
       
       String resultado = DAOFactura.consultaFacturasFecha(fecha);
       return new ArrayList<Factura>();
    }

    
}
