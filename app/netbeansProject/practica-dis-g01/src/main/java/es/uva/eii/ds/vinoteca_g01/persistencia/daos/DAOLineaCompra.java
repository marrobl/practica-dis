/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.persistencia.daos;

import es.uva.eii.ds.vinoteca_g01.persistencia.dbaccess.DBConnection;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import javax.json.JsonValue;
import javax.json.JsonWriter;

/**
 *
 * @author maria
 */
public class DAOLineaCompra {
    private static final String SELECT_LINEAS_COMPRA_POR_ID_COMPRA = 
            "SELECT * FROM LineaCompra Lc WHERE Lc.IdCompra = ?";

    
    private static final String UPDATE_LINEACOMPRA_LINEASPEDIDO
            = "UPDATE LineaCompra Lc SET Lc.Recibida = ?, Lc.FechaRecepcion = ? WHERE Lc.id = ?";
    
    
    static String consultarLineasCompraPorIdCompra(String id_compra) {
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        StringBuilder lineasCompra = new StringBuilder("[");
        
        try {
            PreparedStatement s = connection.getStatement(SELECT_LINEAS_COMPRA_POR_ID_COMPRA);
            s.setString(1, id_compra);
            ResultSet result = s.executeQuery();
            
            int id, unidades, idCompra, codigoReferencia;
            String recibida;
            LocalDate fechaRecepcion;
            String fechaRecep = null;
            
            while (result.next()) {
                id = result.getInt("Id");
                unidades = result.getInt("Unidades");
                recibida = result.getString("Recibida");
                if(recibida.equals("T")){    
                    fechaRecepcion = result.getDate("FechaRecepcion").toLocalDate();
                    fechaRecep = fechaRecepcion.toString();
                } else {
                    fechaRecepcion = null;
                }
                idCompra = result.getInt("IdCompra");
                codigoReferencia = result.getInt("CodigoReferencia");
             
                lineasCompra.append(obtenerLineaCompraJsonString(Integer.toString(id), Integer.toString(unidades), recibida, fechaRecep, Integer.toString(idCompra),Integer.toString(codigoReferencia)));
                lineasCompra.append(",");
            }
            
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOLineaCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        connection.closeConnection();
        
        if (lineasCompra.charAt(lineasCompra.length()-1) == ',') {
            lineasCompra.deleteCharAt(lineasCompra.length()-1);
        }
        
        lineasCompra.append("]");
        
        return lineasCompra.toString();
    }

    private static String obtenerLineaCompraJsonString(String id, String unidades, String recibida, String fechaRecepcion, String idCompra, String codigoReferencia) {
        String lineaCompraJsonString = "";
        
        try {
            StringWriter stringWriter = new StringWriter();
            JsonWriter writer = Json.createWriter(stringWriter);
            
            JsonObject lineaCompraJson = Json.createObjectBuilder()
                    .add("id", id)
                    .add("unidades", unidades)
                    .add("recibida", recibida)
                    .add("idCompra", idCompra)
                    .add("codigoReferencia", codigoReferencia)
                    .build();
            
            writer.writeObject(lineaCompraJson);
            lineaCompraJsonString = stringWriter.toString();
        } catch(Exception ex) {
            Logger.getLogger(DAOLineaCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lineaCompraJsonString;
    }

    public static void actualizaLineasCompraAPartirDeJson(String lineasCompraJson) {
        int id = 0;
        String fechaRecepcion = "";
        String recibida;
        String lineasPedidoJson = "[]";

        JsonReaderFactory factory = Json.createReaderFactory(null);

        try {
            JsonReader reader = factory.createReader(new StringReader(lineasCompraJson));

            JsonArray lineasCompra = reader.readArray();
            for (JsonValue j : lineasCompra) {
                id = j.asJsonObject().getInt("id");
                fechaRecepcion = null == j.asJsonObject().getString("fechaRecepcion") ? null : j.asJsonObject().getString("fechaRecepcion");
                recibida = j.asJsonObject().getBoolean("recibida") ? "T" : "F";

                StringWriter stringWriter = new StringWriter();
                JsonWriter writer = Json.createWriter(stringWriter);
                JsonArray lineasCompraJsonArray = j.asJsonObject().getJsonArray("lineasPedido");
                writer.writeArray(lineasCompraJsonArray);
                lineasPedidoJson = stringWriter.toString();

                DBConnection connection = DBConnection.getInstance();
                connection.openConnection();

                try {
                    PreparedStatement ps = connection.getStatement(UPDATE_LINEACOMPRA_LINEASPEDIDO);

                    ps.setString(1, recibida);
                    ps.setDate(2, Date.valueOf(LocalDate.parse(fechaRecepcion)));
                    ps.setInt(3, id);

                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOLineaCompra.class.getName()).log(Level.SEVERE, null, ex);
                }

                connection.closeConnection();
                DAOLineaPedido.actualizarLineasPedidoAPartirDeJson(lineasPedidoJson);

            }
        } catch (Exception ex) {
            Logger.getLogger(DAOLineaCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
           
            




    
}
