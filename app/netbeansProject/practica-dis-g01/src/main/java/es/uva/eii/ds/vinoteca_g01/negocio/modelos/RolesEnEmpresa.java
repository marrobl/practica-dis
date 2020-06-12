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
 * Clase que representa el Rol en una empresa.
 * 
 * @author ricalba
 * @author silmont
 * @author marrobl
 */
class RolesEnEmpresa {
    
    private LocalDate comienzoEnRol;
    private TipoDeRol rol;

    /**
     * Constructor de RolesEnEmpresa a partir de string JSON.
     * @param json cadena de texto con formato JSON cuya contenido representa un rol en una empresa, así
     * como el comienzo en ese rol
     */
    public RolesEnEmpresa(String json) {
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(json));
        JsonObject rolEnEmpresaJSON = reader.readObject();
        
        comienzoEnRol = LocalDate.parse(rolEnEmpresaJSON.getJsonString("comienzoEnRol").getString());
        rol = TipoDeRol.valueOf(rolEnEmpresaJSON.getJsonString("rol").getString());
    }

    /**
     * Consulta el tipo de rol en la empresa.
     * @return el tipo de rol entre los posibles
     */
    public TipoDeRol getRol() {
        return rol;
    }
    
}
