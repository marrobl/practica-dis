/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.persistencia.daos;

import es.uva.eii.ds.vinoteca_g01.persistencia.dbaccess.DBConnection;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

/**
 *
 * @author maria
 */
public class DAOFactura {
    private static final String SELECT_FACTURAS_FECHA = 
            "SELECT * FROM Factura F, EstadoFactura E WHERE F.FechaEmision <= ? AND F.Estado = E.Id AND E.Nombre = 'vencida' ";
    
    public static String consultaFacturasFecha(LocalDate fecha) {
        String facturaJsonString = "";
        
        int numeroFactura, estado;
        double importe;
        LocalDate fechaEmision, fechaPago = null;
        String idExtractoBancario = "";
        
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        StringBuilder facturas = new StringBuilder("[");
        
        try {
            PreparedStatement ps = connection.getStatement(SELECT_FACTURAS_FECHA);
            ps.setDate(1, java.sql.Date.valueOf(fecha));
           
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                numeroFactura = rs.getInt("NumeroFactura");
                importe = rs.getDouble("Importe");
                fechaEmision = rs.getDate("FechaEmision").toLocalDate();
                estado = rs.getInt("Estado");
                if(rs.getDate("FechaPago")!= null){
                    fechaPago = rs.getDate("FechaPago").toLocalDate();
                } 
                idExtractoBancario = rs.getString("IdExtractoBancario");
                
                
                facturas.append(obtenerFacturaJsonString(Integer.toString(numeroFactura), Double.toString(importe),fechaEmision.toString(), Integer.toString(estado), fechaPago == null ? "" : fechaPago.toString(), idExtractoBancario));
                facturas.append(",");
             
            }
            
            if (facturas.charAt(facturas.length()-1) == ',') {
                facturas.deleteCharAt(facturas.length()-1);
            }

            facturas.append("]");
            JsonObject json = Json.createObjectBuilder().add("facturas", facturas.toString()).build();
            rs.close();
        } catch(SQLException ex) {
            Logger.getLogger(DAOFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        connection.closeConnection();
       
        
        return facturas.toString();
    }

    private static String obtenerFacturaJsonString(String numeroFactura, String importe, String fechaEmision, String estado, String fechaPago, String idExtractoBancario) {
        String facturaJsonString = "";
        
        try {
            StringWriter stringWriter = new StringWriter();
            JsonWriter writer = Json.createWriter(stringWriter);
            
            JsonObject facturaJson = Json.createObjectBuilder()
                    .add("numeroFactura", numeroFactura)
                    .add("importe", importe)
                    .add("fechaEmision", fechaEmision)
                    .add("estado", estado)
                    .add("fechaPago", fechaPago)
                    .add("idExtractoBancario", idExtractoBancario)
                    .build();
            
            writer.writeObject(facturaJson);
            facturaJsonString = stringWriter.toString();
        } catch(Exception ex) {
            Logger.getLogger(DAORolesEnEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return facturaJsonString;
    }

   
    
}
