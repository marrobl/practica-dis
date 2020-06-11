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
class VinculacionConLaEmpresa {

    private LocalDate inicio;
    private TipoDeVinculacion vinculo;
            
    public VinculacionConLaEmpresa(String json) {
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(json));
        JsonObject vinculoJSON = reader.readObject();
        
        inicio = LocalDate.parse((vinculoJSON.getJsonString("inicio").getString()));        
        vinculo = TipoDeVinculacion.valueOf(vinculoJSON.getJsonString("vinculo").getString());
    }

    public TipoDeVinculacion getVinculo() {
        return vinculo;
    }    
}
