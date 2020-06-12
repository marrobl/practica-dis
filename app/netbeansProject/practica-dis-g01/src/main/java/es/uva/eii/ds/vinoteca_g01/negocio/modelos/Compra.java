/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.negocio.modelos;

import es.uva.eii.ds.vinoteca_g01.persistencia.daos.DAOCompra;
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
    private String recibidaCompleta;
    private LocalDate fechaCompraCompletada;
    private double importe;
    private LocalDate fechaPago;
    private int idProveedor;
    
    

    public static Compra getCompraPorId(String id) {
        String datosJSON = DAOCompra.consultaCompraPorId(id);
        Compra c = new Compra(datosJSON);
        return c;
    }

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
            String fechaPago = compraJson.getString("fechaPago");
            String idProveedor = compraJson.getString("idProveedor");
            
            setIdCompra(Integer.parseInt(idCompra));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            LocalDate fechaIC = LocalDate.parse(fechaInicioCompra, formatter);
            setFechaInicioCompra(fechaIC);
            setRecibidaCompra(recibidaCompra);
            LocalDate fechaCC = LocalDate.parse(fechaCompraCompletada, formatter);
            setFechaCompraCompletada(fechaCC);
            setImporte(Double.parseDouble(importe));
            LocalDate fechaP = LocalDate.parse(fechaPago, formatter);
            setFechaPago(fechaP);
            setIdProveedor(Integer.parseInt(idProveedor));
            
            
            
           
           
            
        } catch(Exception ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void setRecibidaCompra(String recibidaCompra) {
        this.recibidaCompleta = recibidaCompra;
    }

    private void setFechaCompraCompletada(LocalDate fechaCC) {
        this.fechaCompraCompletada = fechaCC;
    }

    private void setImporte(double parseDouble) {
        this.importe = parseDouble;
    }

    private void setFechaPago(LocalDate fechaP) {
        this.fechaPago=fechaP;
    }

    private void setIdProveedor(int parseInt) {
        this.idProveedor=parseInt;
    }
    
}
