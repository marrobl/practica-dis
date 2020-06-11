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
 * @author richard
 */
class RolesEnEmpresa {
    
    private LocalDate comienzoEnRol;
    private TipoDeRol rol;

    public RolesEnEmpresa(String json) {
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(json));
        JsonObject rolEnEmpresaJSON = reader.readObject();
        
        comienzoEnRol = LocalDate.parse(rolEnEmpresaJSON.getJsonString("comienzoEnRol").getString());
        rol = TipoDeRol.valueOf(rolEnEmpresaJSON.getJsonString("rol").getString());
    }

    public TipoDeRol getRol() {
        return rol;
    }
    
}
