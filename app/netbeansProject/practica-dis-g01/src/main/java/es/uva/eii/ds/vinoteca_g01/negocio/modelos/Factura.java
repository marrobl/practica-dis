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
 * Clase que implementa a una factura, identificada por un numero
 * 
 * @author ricalba
 * @author silmont
 * @author marrobl
 * 
 */
public class Factura {
    
    private int numeroFactura;
    private LocalDate fechaEmision;
    private double importe;
    private EstadoFactura estado;
    private LocalDate fechaPago;
    private String idExtractoBancario;
    
    /**
     * Crea una instancia de factura
     * @param numeroFactura numero de identificacion 
     * @param fechaEmision fecha en la que ha sido emitida
     * @param importe de la factura
     * @param estado en el que se encuentra la factura
     * @param fechaPago fecha en la que ha sido pagada
     * @param idExtractoBancario identificador del extracto bancario
     * 
     */
    public Factura(int numeroFactura, LocalDate fechaEmision, double importe, EstadoFactura estado, LocalDate fechaPago, String idExtractoBancario){
        this.numeroFactura = numeroFactura;
        this.fechaEmision = fechaEmision;
        this.importe = importe;
        this.estado = estado;
        this.fechaPago = fechaPago;
        this.idExtractoBancario = idExtractoBancario;
    }
    
    /**
     * Crea una instancia de factura a partir de un String Json
     * @param datosJSON datos de la factura
     */
    public Factura(String datosJSON){
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(datosJSON));
        JsonObject facturaJSON = reader.readObject();
        
        this.numeroFactura = Integer.parseInt(facturaJSON.getJsonString("numeroFactura").getString());
        this.fechaEmision = LocalDate.parse(facturaJSON.getJsonString("fechaEmision").getString());
        this.importe = Double.parseDouble(facturaJSON.getJsonString("importe").getString());
        this.estado = EstadoFactura.values()[Integer.parseInt(facturaJSON.getJsonString("estado").getString())-1];  
        if(estado.equals(EstadoFactura.pagada)) {
            this.fechaPago =  LocalDate.parse(facturaJSON.getJsonString("fechaPago").getString());
        } else {
            this.fechaPago = null;
        }
        this.idExtractoBancario = facturaJSON.getJsonString("idExtractoBancario").getString();
    }
    
    /**
     * Consulta el numero de identifacion de la factura
     * @return numeroFactura
     */
    public int getNumeroFactura(){
        return this.numeroFactura;
    }
    
    /**
     * Consulta la fecha de emision
     * @return fechaEmision
     */
    public LocalDate getFechaEmision(){
        return this.fechaEmision;
    }
    
    /**
     * Consulta el importe de la factura
     * @return importe 
     */
    public double getImporte(){
        return this.importe;
    }
    
    /**
     * Consulta el estado de la factura, pudiendo ser: emitida, vencida o pagada
     * @return estado factura
     */
    public EstadoFactura getEstado(){
        return this.estado;
    }
    
    /**
     * Consulta la fecha de pago
     * @return fechaPago
     */
    public LocalDate getFechaPago(){
        return this.fechaPago;
    }
    
    /**
     * Comprueba si han pasado menos de 30 desde la fecha introducida hasta la fecha actual
     * @param fecha fecha a comprobar
     * @return true si han pasado 30 dias o mas, false en cualquier otro caso
     */
    private static boolean comprobarFecha(LocalDate fecha) {
        boolean ok = false;
        long daysBetween = DAYS.between(fecha, LocalDate.now());
        if(daysBetween>=30) ok = true;
        return ok;
    }
    
    /**
     * Obtiene todas las facturas anteriores a una fecha que estan vencidas
     * 
     * @param fecha limite 
     * @return lista de facturas no vencidas anteriores a la fecha introducida
     * @throws FechaNoVencidaException cuando han pasado menos de 30 dias desde la fecha introducida 
     */
    public static ArrayList<Factura> getFacturasFecha(LocalDate fecha) throws FechaNoVencidaException{
       boolean ok = comprobarFecha(fecha);
       if( ok == false){
           throw new FechaNoVencidaException();
       }
       String facturasJSON = DAOFactura.consultaFacturasFecha(fecha);
       ArrayList<Factura> facturas = new ArrayList<>();
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

    /**
     * Consulta todos los pedidos asociados a una factura
     * 
     * @return lista de pedidos asociados al identificador de la factura
     */
    public ArrayList<Pedido> getPedidos() {
        return Pedido.getPedidosNumFactura(this.getNumeroFactura());
    }
}
