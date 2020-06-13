/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.modelos;

import es.uva.eii.ds.vinoteca_g01.persistencia.daos.DAOLineaPedido;
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
 * @author silmont
 */
public class LineaPedido {


    
    private int id;
    private int unidades;
    private boolean completada;
    private int codigoReferencia;
    private int numeroPedido;
    private int idLineaCompra;

    private LineaPedido(String datosJSON) {
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(datosJSON));
        JsonObject lineaPedidoJSON = reader.readObject();
        
        this.id = Integer.parseInt(lineaPedidoJSON.getJsonString("id").getString());
        this.unidades = Integer.parseInt(lineaPedidoJSON.getJsonString("unidades").getString());
        String completadaJson = lineaPedidoJSON.getJsonString("completada").getString();
        if(completadaJson.equals("T")) {
            this.completada = true;
        } else {
            this.completada = false;
        }
        this.numeroPedido = Integer.parseInt(lineaPedidoJSON.getJsonString("numeroPedido").getString());
        this.idLineaCompra = Integer.parseInt(lineaPedidoJSON.getJsonString("idLineaCompra").getString());
    }

    public void setCompletada() {
        completada=true;
    }
    
    public static ArrayList<LineaPedido> getLineasPedidoPorIdLineaCompra(int idLineaCompra) {
        String lineasPedidoJSON = DAOLineaPedido.consultaLineasPedidoPorIdLineaCompra(idLineaCompra);
        ArrayList<LineaPedido> lineasPedido = new ArrayList<>();
        JsonReaderFactory factory = Json.createReaderFactory(null);
        try{
             JsonReader reader = factory.createReader(new StringReader(lineasPedidoJSON));
             JsonObject lineasPedidoJson = reader.readObject();
             JsonArray lineaPedidoJson = lineasPedidoJson.getJsonArray("lineasPedido");

             LineaPedido lineaPedido;

             for (JsonValue lp: lineaPedidoJson) {
                     lineaPedido = new LineaPedido(lp.asJsonObject().toString());
                     lineasPedido.add(lineaPedido);
             }
        } catch(Exception ex) {
             Logger.getLogger(LineaPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

    
        return lineasPedido;         
    }
}
