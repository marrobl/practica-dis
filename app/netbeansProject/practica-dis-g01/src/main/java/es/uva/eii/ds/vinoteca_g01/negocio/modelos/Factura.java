/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.modelos;

import es.uva.eii.ds.vinoteca_g01.persistencia.daos.DAOFactura;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.FechaNoVencidaException;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import javax.json.JsonValue;


/**
 *
 * @author maria
 */




public class Factura {
    
    private int numeroFactura;
    private LocalDate fechaEmision;
    private double importe;
    private EstadoFactura estado;
    private LocalDate fechaPago;
    private String idExtractoBancario;
    
    public Factura(int numeroFactura, LocalDate fechaEmision, double importe, EstadoFactura estado, LocalDate fechaPago, String idExtractoBancario){
        this.numeroFactura = numeroFactura;
        this.fechaEmision = fechaEmision;
        this.importe = importe;
        this.estado = estado;
        this.fechaPago = fechaPago;
        this.idExtractoBancario = idExtractoBancario;
    }
    
    public Factura(String datosJSON){
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(datosJSON));
        JsonObject facturaJSON = reader.readObject();
        
        this.numeroFactura = Integer.parseInt(facturaJSON.getJsonString("numeroFactura").getString());
        this.fechaEmision = LocalDate.parse(facturaJSON.getJsonString("fechaEmision").getString());
        this.importe = Double.parseDouble(facturaJSON.getJsonString("importe").getString());
        this.estado = EstadoFactura.values()[Integer.parseInt(facturaJSON.getJsonString("estado").getString())];
        if(estado.equals(EstadoFactura.Pagada)) {
            this.fechaPago =  LocalDate.parse(facturaJSON.getJsonString("fechaPago").getString());
        } else {
            this.fechaPago = null;
        }
        this.idExtractoBancario = facturaJSON.getJsonString("idExtractoBancario").getString();
    }
    
    public int getNumeroFactura(){
        return this.numeroFactura;
    }
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
       String facturasJSON = DAOFactura.consultaFacturasFecha(fecha);
       ArrayList<Factura> facturas = new ArrayList<Factura>();
       JsonReaderFactory factory = Json.createReaderFactory(null);
       try{
            JsonReader reader = factory.createReader(new StringReader(facturasJSON));
            JsonObject facturasJson = reader.readObject();
            JsonArray factJson = facturasJson.getJsonArray("facturas");

            Factura factura;

            for (JsonValue j: factJson) {
                    factura = new Factura(j.asJsonObject().toString());
                    facturas.add(factura);
            }
       } catch(Exception ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
       }
       
    
        return facturas;
    }
}
