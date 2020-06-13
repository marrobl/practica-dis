/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.modelos;

import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import javax.json.JsonWriter;

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
    private ArrayList<LineaPedido> lineasPedido = new ArrayList<>();
    
    public LineaCompra(String json) {
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(json));
        JsonObject lineaCompraJSON = reader.readObject();
        
        this.id = Integer.parseInt(lineaCompraJSON.getJsonString("id").getString());
        this.unidades = Integer.parseInt(lineaCompraJSON.getJsonString("unidades").getString());
        String recibidaLC = lineaCompraJSON.getString("recibida");
        if(recibidaLC.equals("T")){
           this.recibida = true;
           this.fechaRecepcion = LocalDate.parse(lineaCompraJSON.getJsonString("fechaRecepcion").getString());
        } else {
            this.recibida = false;
            this.fechaRecepcion = null;
        }
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
        this.lineasPedido = LineaPedido.getLineasPedidoPorIdLineaCompra(this.id);
        return lineasPedido;
    }

    boolean getRecibida() {
        return recibida;
    }

    public int getId() {
        return id;
    }

    public String toJSON() {
     String lineaCompraJson = "";
        
        JsonObjectBuilder builder = Json.createObjectBuilder()
                .add("id", id)
                .add("unidades", unidades)
                .add("recibida", recibida)
                .add("idCompra", idCompra)
                .add("codigoReferencia",codigoReferencia);
                
        JsonReaderFactory factory = Json.createReaderFactory(null);
        StringBuilder lineasPedidoJson = new StringBuilder("[");
        
        for(LineaPedido lp: lineasPedido) {
            lineasPedidoJson.append(lp.toJSON());
            lineasPedidoJson.append(",");
        }
        
        if (lineasPedidoJson.charAt(lineasPedidoJson.length() - 1) == ',') {
            lineasPedidoJson.deleteCharAt(lineasPedidoJson.length() - 1);
        }
        
        lineasPedidoJson.append("]");
        JsonReader readerLineasPedido = factory.createReader(new StringReader(lineasPedidoJson.toString()));
        JsonArray lpJsonArray = readerLineasPedido.readArray();
        builder.add("lineasPedido", lpJsonArray);
        
        JsonObject json = builder.build();
        
        try {
            StringWriter stringWriter = new StringWriter();
            JsonWriter writer = Json.createWriter(stringWriter);
            writer.writeObject(json);
            lineaCompraJson = stringWriter.toString();
        } catch(Exception ex) {
             Logger.getLogger(LineaCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lineaCompraJson;
    }
    
}
