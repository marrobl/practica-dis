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
 * @author maria
 */
public class DAOLineaPedido {
    
    private static final String SELECT_LINEASPEDIDO_ID_LINEA_COMPRA
            = "SELECT * FROM LineaPedido Lp WHERE Lp.IdLineaCompra = ? ";
    private static final String SELECT_LINEASPEDIDO_NUMERO_PEDIDO
            = "SELECT * FROM LineaPedido Lp WHERE Lp.NumeroPedido = ? ";
    private static final String MAX_ID_NUMERO_PEDIDO
            = "SELECT MAX(P.numero) as lastId FROM Pedido P";
    private static final String INSERT_LINEA_PEDIDO
            = "INSERT INTO LineaPedido (Unidades, Completada, CodigoReferencia, NumeroPedido, IdLineaCompra) "
            + "VALUES (?, ?, ?, ?, NULL)";
    private static final String UPDATE_LINEA_PEDIDO = "UPDATE LINEAPEDIDO P SET P.Completada=? WHERE P.Id=?";
    
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
                
                lineasPedido.append(obtenerLineaPedidoJsonString(Integer.toString(id), Integer.toString(unidades), completada, Integer.toString(codigoReferencia), Integer.toString(numeroPedido), Integer.toString(idLineaCompra)));
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
        } catch (Exception ex) {
            Logger.getLogger(DAOFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lineasPedidoJsonString;
    }
    
    static void insertarLineasPedidoAPartirDeJson(String json) {
        JsonReaderFactory factory = Json.createReaderFactory(null);
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        
        try (
                JsonReader reader = factory.createReader(new StringReader(json));) {
            JsonArray lineasPedidoJsonArray = reader.readArray();
            
            for (JsonValue j : lineasPedidoJsonArray) {
                int unidades = j.asJsonObject().getInt("unidades");
                String completada = j.asJsonObject().getBoolean("completada") ? "T" : "F";
                int codigoReferencia = j.asJsonObject().getInt("codigoReferencia");
                
                try (
                        PreparedStatement s = connection.getStatement(INSERT_LINEA_PEDIDO);
                        PreparedStatement lastIdSt = connection.getStatement(MAX_ID_NUMERO_PEDIDO);) {
                    
                    int lastIdPedido = 1;
                    ResultSet result = lastIdSt.executeQuery();
                    if (result.next()) {
                        lastIdPedido = result.getInt("lastId");
                        result.close();
                    }
                    
                    s.setInt(1, unidades);
                    s.setString(2, completada);
                    s.setInt(3, codigoReferencia);
                    s.setInt(4, lastIdPedido);
                    s.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOLineaPedido.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOLineaPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        connection.closeConnection();
    }

    public static String consultarLineasPedidoNumPedido(int numPedido) {
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        StringBuilder lineasPedido = new StringBuilder("[");
        
        try {
            PreparedStatement s = connection.getStatement(SELECT_LINEASPEDIDO_NUMERO_PEDIDO);
            s.setInt(1, numPedido);
            ResultSet rs = s.executeQuery();
            
            int id, unidades, codigoReferencia, numeroPedido, idLineaCompra;
            String completada;
            
            while (rs.next()) {
                id = rs.getInt("Id");
                unidades = rs.getInt("Unidades");
                completada = rs.getString("Completada");
                codigoReferencia = rs.getInt("CodigoReferencia");
                numeroPedido = rs.getInt("NumeroPedido");
                idLineaCompra = rs.getInt("IdLineaCompra");
                
                lineasPedido.append(obtenerLineaPedidoJsonString(Integer.toString(id), Integer.toString(unidades), completada, Integer.toString(codigoReferencia), Integer.toString(numeroPedido), Integer.toString(idLineaCompra)));
                lineasPedido.append(",");
            }
            
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOLineaPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        connection.closeConnection();
        
        if (lineasPedido.charAt(lineasPedido.length()-1) == ',') {
            lineasPedido.deleteCharAt(lineasPedido.length()-1);
        }
        
        lineasPedido.append("]");
        
        return lineasPedido.toString();
    }

    static void actualizarLineasPedidoAPartirDeJson(String lineasPedidoJson) {
        JsonReaderFactory factory = Json.createReaderFactory(null);
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();

        
        try (
            JsonReader reader = factory.createReader(new StringReader(lineasPedidoJson));) {
            JsonArray lineasPedidoJsonArray = reader.readArray();
            
            for (JsonValue j : lineasPedidoJsonArray) {
                int id = j.asJsonObject().getInt("id");
                int unidades = j.asJsonObject().getInt("unidades");
                String completada = j.asJsonObject().getBoolean("completada") ? "T" : "F";
                int codigoReferencia = j.asJsonObject().getInt("codigoReferencia");
                
                try (
                        PreparedStatement s = connection.getStatement(UPDATE_LINEA_PEDIDO);) {
                    
                    
                    s.setString(1, completada);
                    s.setInt(2, id);
                    s.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOLineaPedido.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOLineaPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        connection.closeConnection();
    }
}
