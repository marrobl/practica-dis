/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.modelos;

import es.uva.eii.ds.vinoteca_g01.persistencia.daos.DAOCompra;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.CompraNotFoundException;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import javax.json.JsonValue;

/**
 *
 * @author silmont
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
    
    
    
    private Compra(String datosJSON) {
        JsonReaderFactory factory = Json.createReaderFactory(null);
        
        try {
            JsonReader reader = factory.createReader(new StringReader(datosJSON));
            JsonObject compraJson = reader.readObject();
            
            String idCompra = compraJson.getString("idCompra");
            String fechaInicioCompra = compraJson.getString("fechaInincioCompra");
            String recibidaCompra = compraJson.getString("recibidaCompra");
            String fechaCompraCompletada = compraJson.getString("fechaCompraCompletada");
            String importe = compraJson.getString("importe");
            String pagada = compraJson.getString("pagada");
            String fechaPago = compraJson.getString("fechaPago");
            String idProveedor = compraJson.getString("idProveedor");
            
            setIdCompra(Integer.parseInt(idCompra));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            LocalDate fechaIC = LocalDate.parse(fechaInicioCompra, formatter);
            setFechaInicioCompra(fechaIC);
            if(recibidaCompra.equals("T")) {
                this.setRecibidaCompra(true);
            } else {
                this.setRecibidaCompra(false);
            }
            LocalDate fechaCC = LocalDate.parse(fechaCompraCompletada, formatter);
            setFechaCompraCompletada(fechaCC);
            setImporte(Double.parseDouble(importe));
            LocalDate fechaP = LocalDate.parse(fechaPago, formatter);
             if(pagada.equals("T")) {
                this.setPagada(true);
            } else {
                this.setPagada(false);
            }
            setFechaPago(fechaP);
            setIdProveedor(Integer.parseInt(idProveedor));
  
        } catch(Exception ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
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

    public String getBodega() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Iterator getLineasCompra() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
}
