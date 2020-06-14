package es.uva.eii.ds.vinoteca_g01.negocio.modelos;

import es.uva.eii.ds.vinoteca_g01.persistencia.daos.DAOAbonado;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.AbonadoNoExisteException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;

/**
 *
 * @author ricalba
 * @author silmont
 * @author marrobl
 */
public class Abonado extends Persona {
    private int numeroAbonado;
    private String openIDref;

    public static Abonado getAbonado(int numAbonado) throws AbonadoNoExisteException {
        String datosJSON = DAOAbonado.consultarAbonadoPorId(numAbonado);
        
        if (datosJSON == null) {
            throw new AbonadoNoExisteException();
        }
        return new Abonado(datosJSON);
    }    

    public void setNumeroAbonado(int numeroAbonado) {
        this.numeroAbonado = numeroAbonado;
    }
    
    public int getNumeroAbonado() {
        return numeroAbonado;
    }

    public void setOpenIDref(String openIDref) {
        this.openIDref = openIDref;
    }
    
    private Abonado(String json) {
        super();
        JsonReaderFactory factory = Json.createReaderFactory(null);
        
        try {
            JsonReader reader = factory.createReader(new StringReader(json));
            JsonObject abonadoJson = reader.readObject();
            
            setNif(abonadoJson.getString("nif"));
            setNombre(abonadoJson.getString("nombre"));
            setApellidos(abonadoJson.getString("apellidos"));
            setDireccion(abonadoJson.getString("direccion"));
            setTelefono(abonadoJson.getString("telefono"));
            setEmail(abonadoJson.getString("email"));
            setCuentaBancaria(abonadoJson.getString("cuentaBancaria"));
            setNumeroAbonado(abonadoJson.getInt("numeroAbonado"));
            setOpenIDref(abonadoJson.getString("openIDref"));
            
        } catch(Exception ex) {
            Logger.getLogger(Abonado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
