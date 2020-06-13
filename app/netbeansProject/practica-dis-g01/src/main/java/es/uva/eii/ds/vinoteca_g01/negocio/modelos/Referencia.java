/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.modelos;

import es.uva.eii.ds.vinoteca_g01.persistencia.daos.DAOReferencia;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.ReferenciaNoExisteException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;

/**
 *
 * @author richard
 */
public class Referencia {

    private int codigo;
    private boolean esPorCajas;
    private int contenidoEnCL;
    private double precio;
    private boolean disponible;
    private int vinoId;
    
    public static Referencia getReferenciaPorCodigo(int numRef) throws ReferenciaNoExisteException {
        String datosJson = DAOReferencia.consultarReferenciaPorCodigo(numRef);

        if (datosJson == null) {
            throw new ReferenciaNoExisteException();
        } else {
            return new Referencia(datosJson);
        }
    }

    private Referencia(String json) {
        JsonReaderFactory factory = Json.createReaderFactory(null);

        try {
            JsonReader reader = factory.createReader(new StringReader(json));
            JsonObject referenciaJson = reader.readObject();

            setCodigo(referenciaJson.getInt("codigo"));
            setEsPorCajas(referenciaJson.getBoolean("esPorCajas"));
            setContenidoEnCL(referenciaJson.getInt("contenidoEnCL"));
            setPrecio(referenciaJson.getJsonNumber("precio").bigDecimalValue().doubleValue());
            setDisponible(referenciaJson.getBoolean("disponible"));
            setVinoId(referenciaJson.getInt("vinoId"));
        } catch (Exception ex) {
            Logger.getLogger(Abonado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setEsPorCajas(boolean esPorCajas) {
        this.esPorCajas = esPorCajas;
    }

    public void setContenidoEnCL(int contenidoEnCL) {
        this.contenidoEnCL = contenidoEnCL;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setVinoId(int vinoId) {
        this.vinoId = vinoId;
    }

    public boolean isDisponible() {
        return disponible;
    }

}
