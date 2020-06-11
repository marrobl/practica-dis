/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.modelos;

import es.uva.eii.ds.vinoteca_g01.persistencia.daos.DAOPedido;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
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
public class Pedido {
    private int numero;
    private EstadoPedido estado;
    private LocalDate fechaRealizacion;
    private String notaEntrega;
    private double importe;
    private LocalDate fechaRecepcion;
    private LocalDate fechaEntrega;
    private int numeroFactura;
    private int numeroAbonado;
    
    public Pedido(int numero, EstadoPedido estado, LocalDate fechaRealizacion, String notaEntrega, double importe, LocalDate fechaRecepcion, LocalDate fechaEntrega, int numeroFactura, int numeroAbonado){
        this.numero = numero;
        this.estado = estado;
        this.fechaRealizacion = fechaRealizacion;
        this.notaEntrega = notaEntrega;
        this.importe = importe;
        this.fechaRecepcion = fechaRecepcion;
        this.fechaEntrega = fechaEntrega;
        this.numeroFactura = numeroFactura;
        this.numeroAbonado = numeroAbonado;
        
    }
    
    public Pedido(String datosJSON){
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(datosJSON));
        JsonObject pedidoJSON = reader.readObject();
        
        this.numero = Integer.parseInt(pedidoJSON.getJsonString("numero").getString());
        this.estado = EstadoPedido.values()[Integer.parseInt(pedidoJSON.getJsonString("estado").getString())-1];  
        this.fechaRealizacion = LocalDate.parse(pedidoJSON.getJsonString("fechaRealizacion").getString());
        this.notaEntrega = pedidoJSON.getJsonString("notaEntrega").getString();
        this.importe = Double.parseDouble(pedidoJSON.getJsonString("importe").getString());
        this.fechaRecepcion = LocalDate.parse(pedidoJSON.getJsonString("fechaRecepcion").getString());        
        this.fechaEntrega = LocalDate.parse(pedidoJSON.getJsonString("fechaEntrega").getString());
        this.numeroFactura = Integer.parseInt(pedidoJSON.getJsonString("numeroFactura").getString());
        this.numeroAbonado = Integer.parseInt(pedidoJSON.getJsonString("numeroAbonado").getString());
    }
    
    public int getNumero(){
        return this.numero;
    }
    
    public EstadoPedido getEstado(){
        return this.estado;
    }
    
    public LocalDate getFechaRealizacion(){
        return this.fechaRealizacion;
    }
    
    public String getNotaEntrega(){
        return this.notaEntrega;
    }
    
    public double getImporte(){
        return this.importe;
    }
        
    public LocalDate getFechaRecepcion(){
        return this.fechaRecepcion;
    }
        
    public LocalDate getFechaEntrega(){
        return this.fechaEntrega;
    }
    
    public int getNumeroFactura(){
        return this.numeroFactura;
    }
    
    public int getNumeroAbonado(){
        return this.numeroAbonado;
    }
    
    public static ArrayList<Pedido> getPedidosNumFactura(int numeroFactura) {
       String pedidosJSON = DAOPedido.consultaPedidosNumFactura(numeroFactura);
       ArrayList<Pedido> pedidos = new ArrayList<>();
       JsonReaderFactory factory = Json.createReaderFactory(null);
       try{
            JsonReader reader = factory.createReader(new StringReader(pedidosJSON));
            JsonObject pedidosJson = reader.readObject();
            JsonArray pedJson = pedidosJson.getJsonArray("pedidos");

            Pedido pedido;

            for (JsonValue j: pedJson) {
                    pedido = new Pedido(j.asJsonObject().toString());
                    pedidos.add(pedido);
            }
       } catch(Exception ex) {
            Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
       }
       
    
        return pedidos;
    }
    
}
