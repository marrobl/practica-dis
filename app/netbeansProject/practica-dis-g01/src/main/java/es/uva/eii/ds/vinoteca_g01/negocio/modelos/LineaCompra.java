/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.modelos;

import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;

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
    private ArrayList<LineaPedido> lineasPedido;
    public LineaCompra(String json) {
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(json));
        JsonObject lineaCompraJSON = reader.readObject();
        
        this.id = Integer.parseInt(lineaCompraJSON.getJsonString("id").getString());
        this.unidades = Integer.parseInt(lineaCompraJSON.getJsonString("unidades").getString());
        String recibida = lineaCompraJSON.getString("recibida");
        if(recibida.equals("T")){
           this.recibida = true;
        } else {
            this.recibida = false;
        }
        this.fechaRecepcion = LocalDate.parse(lineaCompraJSON.getJsonString("fechaRecepcion").getString());
        this.idCompra = Integer.parseInt(lineaCompraJSON.getJsonString("idCompra").getString());
        this.codigoReferencia = Integer.parseInt(lineaCompraJSON.getJsonString("codigoReferencia").getString());

    }

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
        ArrayList<LineaPedido> lp = LineaPedido.getLineasPedidoPorIdLineaCompra(this.id);
           // Falta guardar el array de lineaPedido en el atributo que he hecho para ello
        return lp;
    }
    
}
