/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.persistencia.daos;

import es.uva.eii.ds.vinoteca_g01.persistencia.dbaccess.DBConnection;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import javax.json.JsonWriter;

/**
 *
 * @author maria
 */
public class DAOLineaPedido {
    
    private static final String SELECT_LINEASPEDIDO_ID_LINEA_COMPRA
            = "SELECT * FROM LineaPedido Lp WHERE Lp.IdLineaCompra = ? ";
    
    public static String consultaLineasPedidoPorIdLineaCompra(int idLC) {
        String lineasPedidoJsonString;

        int id, unidades, codigoReferencia, numeroPedido, idLineaCompra;
        String completada;
     

        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        JsonObjectBuilder jb = Json.createObjectBuilder();

        StringBuilder lineasPedido = new StringBuilder("[");

        try {
            PreparedStatement ps = connection.getStatement(SELECT_LINEASPEDIDO_ID_LINEA_COMPRA);
            ps.setInt(1, idLC);

            ResultSet rs = ps.executeQuery();
 
            while (rs.next()) {
                id = rs.getInt("Id");
                unidades = rs.getInt("Unidades");
                completada = rs.getString("Completada");
                codigoReferencia = rs.getInt("CodigoReferencia");
                numeroPedido = rs.getInt("NumeroPedido");
                idLineaCompra = rs.getInt("IdLineaCompra");
               
                lineasPedido.append(obtenerLineaPedidoJsonString(Integer.toString(id), Integer.toString(unidades),completada,Integer.toString(codigoReferencia), Integer.toString(numeroPedido), Integer.toString(idLineaCompra)));
                lineasPedido.append(",");

            }

            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOLineaPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

        connection.closeConnection();

        if (lineasPedido.charAt(lineasPedido.length() - 1) == ',') {
            lineasPedido.deleteCharAt(lineasPedido.length() - 1);
        }

        lineasPedido.append("]");
        
        lineasPedidoJsonString = obtenerLineasPedidoJsonString(lineasPedido.toString());

        return lineasPedidoJsonString;
  
    }

    private static String obtenerLineaPedidoJsonString(String id, String unidades, String completada, String codigoReferencia, String numeroPedido, String idLineaCompra) {
        String lineaPedidoJsonString = "";

        try {
            StringWriter stringWriter = new StringWriter();
            JsonWriter writer = Json.createWriter(stringWriter);

            JsonObject lineaPedidoJson = Json.createObjectBuilder()
                    .add("id", id)
                    .add("unidades", unidades)
                    .add("completada", completada)
                    .add("codigoReferencia", codigoReferencia)
                    .add("numeroPedido", numeroPedido)
                    .add("idLineaCompra", idLineaCompra)
                    .build();

            writer.writeObject(lineaPedidoJson);
            lineaPedidoJsonString = stringWriter.toString();
        } catch (Exception ex) {
            Logger.getLogger(DAOLineaPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lineaPedidoJsonString;
    }

    private static String obtenerLineasPedidoJsonString(String lineasPedido) {
        String lineasPedidoJsonString = "";
        JsonReaderFactory factory = Json.createReaderFactory(null);
        
        try {
            JsonReader readerFacturas = factory.createReader(new StringReader(lineasPedido));
            StringWriter stringWriter = new StringWriter();
            JsonWriter writer = Json.createWriter(stringWriter);
            
            JsonArray lineasPedidoJsonArray = readerFacturas.readArray();
            
            JsonObject lineasPedidoJson = Json.createObjectBuilder()
                    .add("lineasPedido", lineasPedidoJsonArray)
                    .build();
            
            writer.writeObject(lineasPedidoJson);
            lineasPedidoJsonString = stringWriter.toString();
        } catch(Exception ex) {
            Logger.getLogger(DAOFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lineasPedidoJsonString;
    }   
}
