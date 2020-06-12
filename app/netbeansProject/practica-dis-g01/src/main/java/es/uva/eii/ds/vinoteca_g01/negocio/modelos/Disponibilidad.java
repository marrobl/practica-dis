/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.modelos;

import java.io.StringReader;
import java.time.LocalDate;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;

/**
 * Clase que representa la disponibilidad en una empresa.
 * 
 * @author ricalba
 * @author silmont
 * @author marrobl
 */
class Disponibilidad {
    
    private LocalDate comienzo;
    private LocalDate finalPrevisto;
    private TipoDeDisponibilidad disponibilidad;

    /**
     * Constructor de Disponibilidad a partir de string JSON.
     * @param json cadena de texto con formato JSON cuya contenido representa una Disponibilidad
     */
    public Disponibilidad(String json) {
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(json));
        JsonObject disponibilidadJSON = reader.readObject();
        
        comienzo = LocalDate.parse(disponibilidadJSON.getJsonString("comienzo").getString());
        finalPrevisto = LocalDate.parse(disponibilidadJSON.getJsonString("finalPrevisto").getString());
        disponibilidad = TipoDeDisponibilidad.valueOf(disponibilidadJSON.getJsonString("disponibilidad").getString());
    }

    /**
     * Consulta el tipo de disponibilidad.
     * @return el tipo de disponibilidad entre los posibles
     */
    public TipoDeDisponibilidad getDisponibilidad() {
        return disponibilidad;
    }
    
}
