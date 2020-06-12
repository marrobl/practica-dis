package es.uva.eii.ds.vinoteca_g01.negocio.modelos;

import java.io.StringReader;
import java.time.LocalDate;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;

/**
 * Clase que representa la vinculación con la empresa.
 * 
 * @author ricalba
 * @author silmont
 * @author marrobl
 */
class VinculacionConLaEmpresa {

    private LocalDate inicio;
    private TipoDeVinculacion vinculo;
            
    /**
     * Constructor de VinculacionConLaEmpresa a partir de string JSON.
     * @param json cadena de texto con formato JSON cuya contenido representa una VinculacionConLaEmpresa.
     */
    public VinculacionConLaEmpresa(String json) {
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(json));
        JsonObject vinculoJSON = reader.readObject();
        
        inicio = LocalDate.parse((vinculoJSON.getJsonString("inicio").getString()));        
        vinculo = TipoDeVinculacion.valueOf(vinculoJSON.getJsonString("vinculo").getString());
    }

    /**
     * Consulta el tipo de vínculo.
     * @return el tipo de vínculo entre los posibles
     */
    public TipoDeVinculacion getVinculo() {
        return vinculo;
    }    
}
