package es.uva.eii.ds.vinoteca_g01.negocio.modelos;

import es.uva.eii.ds.vinoteca_g01.persistencia.daos.DAOCompra;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.CompraNotFoundException;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import javax.json.JsonValue;
import javax.json.JsonWriter;

/**
 *
 * @author ricalba
 * @author silmont
 * @author marrobl
 */
public class Compra {
    
    private int idCompra;
    private LocalDate fechaInicioCompra;
    private boolean recibidaCompleta;
    private LocalDate fechaCompraCompletada;
    private double importe;
    private boolean pagada;
    private LocalDate fechaPago;
    private int idProveedor;
    private ArrayList<LineaCompra> lineasCompra;
    private Bodega bodega;
    
    
    private Compra(String datosJSON) {
        JsonReaderFactory factory = Json.createReaderFactory(null);
        lineasCompra = new ArrayList<>();
        
        try {
            JsonReader reader = factory.createReader(new StringReader(datosJSON));
            JsonObject compraJson = reader.readObject();
            
            String idCompra = compraJson.getString("idCompra");
            String fechaInicioCompra = compraJson.getString("fechaInicioCompra");
            String recibidaCompra = compraJson.getString("recibidaCompleta");
            String fechaCompraCompletada = null;
            if(recibidaCompra.equals("T")){
                fechaCompraCompletada = compraJson.getString("fechaCompraCompletada");
            } 
            String importe = compraJson.getString("importe");
            String pagada = compraJson.getString("pagada");
            String fechaPago = null;
            if(pagada.equals("T")){
                fechaPago = compraJson.getString("fechaPago");
            } 
            String idProveedor = compraJson.getString("idProveedor");
            
            
            setIdCompra(Integer.parseInt(idCompra));
      
            LocalDate fechaIC = LocalDate.parse(fechaInicioCompra);
            setFechaInicioCompra(fechaIC);
            if(recibidaCompra.equals("T")) {
                this.setRecibidaCompra(true);
                LocalDate fechaCC = LocalDate.parse(fechaCompraCompletada);
                setFechaCompraCompletada(fechaCC);
            } else {
                this.setRecibidaCompra(false);
            }
            setImporte(Double.parseDouble(importe));
            if(pagada.equals("T")) {
                this.setPagada(true);
                LocalDate fechaP = LocalDate.parse(fechaPago);
                setFechaPago(fechaP);
            } else {
                this.setPagada(false);
            }
     
            setIdProveedor(Integer.parseInt(idProveedor));
            JsonArray lineasCompraJson = compraJson.getJsonArray("lineasCompra");
            
            LineaCompra lineaCompra;
            
            for (JsonValue lp: lineasCompraJson) {
                lineaCompra = new LineaCompra(lp.asJsonObject().toString());
                lineasCompra.add(lineaCompra);
            }
            
            String bodegaJson = compraJson.getString("bodega");
            bodega = new Bodega(bodegaJson);
          
            
        } catch(Exception ex) {
            Logger.getLogger(Compra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public static Compra getCompraPorId(String id) throws CompraNotFoundException {
        String datosJSON = DAOCompra.consultaCompraPorId(id);
        if(datosJSON == null){
            throw new CompraNotFoundException();
        }
        Compra c = new Compra(datosJSON);
        return c;
    }

    public String getNombreBodega() {
        return this.bodega.getNombre();
    }

    public Iterator getLineasCompra() {
        Iterator it = lineasCompra.iterator();
        return it;
    }

    private void setIdCompra(int parseInt) {
       this.idCompra=parseInt;
    }

    private void setFechaInicioCompra(LocalDate fechaIC) {
       this.fechaInicioCompra=fechaIC;
    }

    private void setRecibidaCompra(boolean recibidaCompleta) {
        this.recibidaCompleta = recibidaCompleta;
    }

    private void setFechaCompraCompletada(LocalDate fechaCC) {
        this.fechaCompraCompletada = fechaCC;
    }

    private void setImporte(double parseDouble) {
        this.importe = parseDouble;
    }

    private void setPagada(boolean pagada){
        this.pagada = pagada;
    }
    
    private void setFechaPago(LocalDate fechaP) {
        this.fechaPago=fechaP;
    }

    private void setIdProveedor(int parseInt) {
        this.idProveedor=parseInt;
    }

    public boolean getRecibidaCompleta() {
        return recibidaCompleta;
    }

    public ArrayList<LineaCompra> getLineasNoRecibidas() {
        Iterator it = getLineasCompra();
        ArrayList<LineaCompra> lcNoComp = new ArrayList<>();
        while(it.hasNext()){
            LineaCompra c = (LineaCompra)it.next();
            if(!c.getRecibida()) lcNoComp.add(c);
    }
        return lcNoComp;
    }

    public void setRecibidaCompleta() {
        recibidaCompleta = true;
    }

    public void setFechaCompraCompletada() {
        fechaCompraCompletada = LocalDate.now();
    }

    public String toJSON() {
        String compraJson = "";
        
        JsonObjectBuilder builder = Json.createObjectBuilder()
                .add("idCompra", idCompra)
                .add("fechaInicioCompra", fechaInicioCompra.toString())
                .add("recibidaCompleta", recibidaCompleta)
                .add("fechaCompraCompletada", fechaCompraCompletada.toString())
                .add("importe", importe)
                .add("pagada",pagada)
                .add("idProveedor", idProveedor);
        
        JsonReaderFactory factory = Json.createReaderFactory(null);
        StringBuilder lineasCompraJson = new StringBuilder("[");
        
        for(LineaCompra lc: lineasCompra) {
            lineasCompraJson.append(lc.toJSON());
            lineasCompraJson.append(",");
        }
        
        if (lineasCompraJson.charAt(lineasCompraJson.length() - 1) == ',') {
            lineasCompraJson.deleteCharAt(lineasCompraJson.length() - 1);
        }
        
        lineasCompraJson.append("]");
        JsonReader readerLineasCompra = factory.createReader(new StringReader(lineasCompraJson.toString()));
        JsonArray lcJsonArray = readerLineasCompra.readArray();

        builder.add("lineasCompra", lcJsonArray);
        
        JsonObject json = builder.build();
        
        try {
            StringWriter stringWriter = new StringWriter();
            JsonWriter writer = Json.createWriter(stringWriter);
            writer.writeObject(json);
            compraJson = stringWriter.toString();
        } catch(Exception ex) {
             Logger.getLogger(Compra.class.getName()).log(Level.SEVERE, null, ex);
        }

        return compraJson;
    }

    public void actualizar(String json) {
        DAOCompra.actualizarCompraLineasCompra(json);
    }

    public LineaCompra getLineaCompraId(int idLinea) {
        Iterator it = getLineasCompra();
        LineaCompra c = null;
        while(it.hasNext()){
            LineaCompra l = (LineaCompra)it.next();
            if(l.getId() == idLinea) c = l;
        }
        
        return c;
    }

 
    
}
