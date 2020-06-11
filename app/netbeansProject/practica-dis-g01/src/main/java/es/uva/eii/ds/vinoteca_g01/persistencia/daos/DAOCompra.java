/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.persistencia.daos;

import es.uva.eii.ds.vinoteca_g01.persistencia.dbaccess.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author silmont
 */
public class DAOCompra {
    private static final String SELECT_COMPRA_ID_COMPRA
            = "SELECT * FROM Compra C WHERE C.IdCompra = ? ";

    public static String consultaCompraPorId(String id) {
        String compraJSON = null;
        
        int idCompra, idProveedor;
        LocalDate fechaInicioCompra, fechaCompraCompletada, fechaPago;
        String recibidaCompleta, pagada;
        double importe;
        
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        JsonObjectBuilder jb = Json.createObjectBuilder();

        StringBuilder compra = new StringBuilder("[");
        
        try {
            PreparedStatement ps = connection.getStatement(SELECT_COMPRA_ID_COMPRA);
            ps.setString(1,id);

            ResultSet rs = ps.executeQuery();
 
            while (rs.next()) {
                idCompra = rs.getInt("IdCompra");
                fechaInicioCompra = rs.getDate("FechaInicioCompra").toLocalDate();
                recibidaCompleta = rs.getString("RecibidaCompleta");
                fechaCompraCompletada = rs.getDate("FechaCompraCompletada").toLocalDate();
                importe = rs.getDouble("Importe");
                pagada = rs.getString("FechaRecepcion");
                fechaPago = rs.getDate("FechaPago").toLocalDate();
                idProveedor = rs.getInt("IdProveedor");
                
                
              
                compra.append(obtenerCompraJsonString(Integer.toString(idCompra), fechaInicioCompra.toString(), recibidaCompleta, fechaCompraCompletada.toString(), Double.toString(importe), pagada, fechaPago.toString(), Integer.toString(idProveedor)));
                compra.append(",");

            }

            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOFactura.class.getName()).log(Level.SEVERE, null, ex);
        }

        connection.closeConnection();
        
        
        return compraJSON;
    }

    private static String obtenerCompraJsonString(String toString, String toString0, String recibidaCompleta, String toString1, String toString2, String pagada, String toString3, String toString4) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
