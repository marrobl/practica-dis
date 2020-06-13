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
 *
 * @author maria
 */
public class Bodega {
    private int id;
    private String nombre;
    private String cif;
    private String direccion;
    
    Bodega(String json) {
       JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(json));
        JsonObject bodegaJSON = reader.readObject();
        
        this.id = Integer.parseInt(bodegaJSON.getJsonString("id").getString());
        this.nombre = bodegaJSON.getString("nombre");
        this.cif = bodegaJSON.getString("cif");
        this.direccion = bodegaJSON.getString("direccion");
  
    }

}
