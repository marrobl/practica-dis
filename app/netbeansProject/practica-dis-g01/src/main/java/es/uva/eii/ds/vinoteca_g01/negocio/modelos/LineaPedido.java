/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.modelos;

import es.uva.eii.ds.vinoteca_g01.persistencia.daos.DAOLineaPedido;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
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
import javax.json.JsonWriter;

/**
 *
 * @author silmont
 */
public class LineaPedido {

    private int id;
    private int unidades;
    private boolean completada;
    private Referencia referencia;
    private int numeroPedido;
    private int idLineaCompra;

    public LineaPedido(Referencia referencia, int unidades) {
        this.referencia = referencia;
        this.unidades = unidades;
        completada = false;
    }

    public LineaPedido(String datosJSON) {
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(datosJSON));
        JsonObject lineaPedidoJSON = reader.readObject();
        referencia = new Referencia();

        this.id = Integer.parseInt(lineaPedidoJSON.getJsonString("id").getString());
        this.unidades = Integer.parseInt(lineaPedidoJSON.getJsonString("unidades").getString());
        String completadaJson = lineaPedidoJSON.getJsonString("completada").getString();
        this.completada = completadaJson.equals("T");
        this.numeroPedido = Integer.parseInt(lineaPedidoJSON.getJsonString("numeroPedido").getString());
        this.idLineaCompra = Integer.parseInt(lineaPedidoJSON.getJsonString("idLineaCompra").getString());
        this.referencia.setCodigo(Integer.parseInt(lineaPedidoJSON.getJsonString("codigoReferencia").getString()));
    }

    public void setCompletada() {
        completada = true;
    }

    public static ArrayList<LineaPedido> getLineasPedidoPorIdLineaCompra(int idLineaCompra) {
        String lineasPedidoJSON = DAOLineaPedido.consultaLineasPedidoPorIdLineaCompra(idLineaCompra);
        ArrayList<LineaPedido> lineasPedido = new ArrayList<>();
        JsonReaderFactory factory = Json.createReaderFactory(null);
        try {
            JsonReader reader = factory.createReader(new StringReader(lineasPedidoJSON));
            JsonObject lineasPedidoJson = reader.readObject();
            JsonArray lineaPedidoJson = lineasPedidoJson.getJsonArray("lineasPedido");

            LineaPedido lineaPedido;

            for (JsonValue lp : lineaPedidoJson) {
                lineaPedido = new LineaPedido(lp.asJsonObject().toString());
                lineasPedido.add(lineaPedido);
            }
        } catch (Exception ex) {
            Logger.getLogger(LineaPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lineasPedido;
    }

    public Referencia getReferencia() {
        return referencia;
    }

    public int getUnidades() {
        return unidades;
    }

    public String toJSON() {
        String lineaPedidoJSON = "";
        JsonObject json = Json.createObjectBuilder()
                .add("id", id)
                .add("unidades", unidades)
                .add("completada", completada)
                .add("codigoReferencia", referencia.getCodigo())
                .build();

        try (
                StringWriter stringWriter = new StringWriter();
                JsonWriter writer = Json.createWriter(stringWriter);) {
            writer.writeObject(json);
            lineaPedidoJSON = stringWriter.toString();
        } catch (Exception ex) {
            Logger.getLogger(LineaPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lineaPedidoJSON;
    }

    public boolean getCompletada() {
        return completada;
    }
}
