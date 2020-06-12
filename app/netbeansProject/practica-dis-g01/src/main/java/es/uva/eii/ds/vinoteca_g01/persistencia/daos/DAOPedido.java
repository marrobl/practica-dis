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
 * Clase que accede a la base de datos para consultar pedidos
 *
 * @author ricalba
 * @author silmont
 * @author marrobl
 */
public class DAOPedido {

    private static final String SELECT_PEDIDOS_NUM_FACTURA = "SELECT * FROM Pedido P WHERE P.NumeroFactura = ? ";
    private static final String SELECT_PEDIDOS_VENCIDOS_NUM_ABONADO = "SELECT * FROM Pedido P, Factura F, EstadoFactura EF "
            + "WHERE P.numeroPedido=? AND P.numeroFactura=F.numeroFactura AND F.estado=EF.id AND EF.nombre='vencida'";

    /**
     * Consulta pedidos asociados a un numero de factura
     *
     * @param numFactura identificador de la factura
     * @return String en formato Json de los pedidos asociados
     */
    public static String consultaPedidosNumFactura(int numFactura) {
        String pedidosJsonString;

        int numeroPedido, estado, numeroFactura, numeroAbonado;
        LocalDate fechaRealizacion, fechaRecepcion, fechaEntrega;
        String notaEntrega;
        double importe;

        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();

        StringBuilder pedidos = new StringBuilder("[");

        try {
            PreparedStatement ps = connection.getStatement(SELECT_PEDIDOS_NUM_FACTURA);
            ps.setInt(1, numFactura);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                numeroPedido = rs.getInt("Numero");
                estado = rs.getInt("Estado");
                fechaRealizacion = rs.getDate("FechaRealizacion").toLocalDate();
                notaEntrega = rs.getString("NotaEntrega");
                importe = rs.getDouble("Importe");
                fechaRecepcion = rs.getDate("FechaRecepcion").toLocalDate();
                fechaEntrega = rs.getDate("FechaEntrega").toLocalDate();
                numeroFactura = rs.getInt("NumeroFactura");
                numeroAbonado = rs.getInt("NumeroAbonado");

                pedidos.append(obtenerPedidoJsonString(Integer.toString(numeroPedido), Integer.toString(estado), fechaRealizacion.toString(), notaEntrega, Double.toString(importe), fechaRecepcion.toString(), fechaEntrega.toString(), Integer.toString(numeroFactura), Integer.toString(numeroAbonado)));
                pedidos.append(",");

            }

            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

        connection.closeConnection();

        if (pedidos.charAt(pedidos.length() - 1) == ',') {
            pedidos.deleteCharAt(pedidos.length() - 1);
        }

        pedidos.append("]");

        pedidosJsonString = obtenerPedidosJsonString(pedidos.toString());

        return pedidosJsonString;
    }

    public static String consultarPedidosVencidosPorNumeroAbonado(int numeroAbonado) {
        String pedidosVencidosJsonString = null;
        int numero, estado, numeroFactura;
        LocalDate fechaRealizacion, fechaRecepcion, fechaEntrega;
        String notaEntrega;
        double importe;
        boolean hayDatos = false;

        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();

        StringBuilder pedidosVencidos = new StringBuilder("[");

        try {
            PreparedStatement ps = connection.getStatement(SELECT_PEDIDOS_VENCIDOS_NUM_ABONADO);
            ps.setInt(1, numeroAbonado);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                hayDatos = true;
                numero = rs.getInt("Numero");
                estado = rs.getInt("Estado");
                fechaRealizacion = rs.getDate("FechaRealizacion").toLocalDate();
                notaEntrega = rs.getString("NotaEntrega");
                importe = rs.getDouble("Importe");
                fechaRecepcion = rs.getDate("FechaRecepcion").toLocalDate();
                fechaEntrega = rs.getDate("FechaEntrega").toLocalDate();
                numeroFactura = rs.getInt("NumeroFactura");
                numeroAbonado = rs.getInt("NumeroAbonado");

                pedidosVencidos.append(obtenerPedidoJsonString(Integer.toString(numero), Integer.toString(estado),
                        fechaRealizacion.toString(), notaEntrega, Double.toString(importe), fechaRecepcion.toString(), fechaEntrega.toString(), Integer.toString(numeroFactura), Integer.toString(numeroAbonado)));
                pedidosVencidos.append(",");
            }

            connection.closeConnection();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (hayDatos) {
            if (pedidosVencidos.charAt(pedidosVencidos.length() - 1) == ',') {
                pedidosVencidos.deleteCharAt(pedidosVencidos.length() - 1);
            }

            pedidosVencidos.append("]");

            pedidosVencidosJsonString = obtenerPedidosJsonString(pedidosVencidos.toString());
        }

        return pedidosVencidosJsonString;
    }

    /**
     * Consulta el String en formato Json que se obtiene a partir de los datos
     * de un pedido
     *
     * @param numeroPedido identificador del pedido
     * @param estado estado
     * @param fechaRealizacion fecha de realizacion
     * @param notaEntrega nota de entrega
     * @param importe importe
     * @param fechaRecepcion fecha de recepcion
     * @param fechaEntrega fecha de entrega
     * @param numeroFactura numero de la factura a la que esta asociado
     * @param numeroAbonado numero del abonado al que esta asociado
     * @return String en formato Json que representa el pedido
     */
    private static String obtenerPedidoJsonString(String numeroPedido, String estado, String fechaRealizacion, String notaEntrega, String importe, String fechaRecepcion, String fechaEntrega, String numeroFactura, String numeroAbonado) {
        String pedidoJsonString = "";

        try {
            StringWriter stringWriter = new StringWriter();
            JsonWriter writer = Json.createWriter(stringWriter);

            JsonObject pedidoJson = Json.createObjectBuilder()
                    .add("numero", numeroPedido)
                    .add("estado", estado)
                    .add("fechaRealizacion", fechaRealizacion)
                    .add("notaEntrega", notaEntrega)
                    .add("importe", importe)
                    .add("fechaRecepcion", fechaRecepcion)
                    .add("fechaEntrega", fechaEntrega)
                    .add("numeroFactura", numeroFactura)
                    .add("numeroAbonado", numeroAbonado)
                    .build();

            writer.writeObject(pedidoJson);
            pedidoJsonString = stringWriter.toString();
        } catch (Exception ex) {
            Logger.getLogger(DAORolesEnEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pedidoJsonString;
    }

    /**
     * Obtiene un array en formato String Json a partir de un Json de pedidos
     *
     * @param pedidos lista en formato Json de pedidos
     * @return array en formato Json
     */
    private static String obtenerPedidosJsonString(String pedidos) {
        String pedidosJsonString = "";
        JsonReaderFactory factory = Json.createReaderFactory(null);

        try {
            JsonReader readerPedidos = factory.createReader(new StringReader(pedidos));
            StringWriter stringWriter = new StringWriter();
            JsonWriter writer = Json.createWriter(stringWriter);

            JsonArray pedidosJsonArray = readerPedidos.readArray();

            JsonObject pedidosJson = Json.createObjectBuilder()
                    .add("pedidos", pedidosJsonArray)
                    .build();

            writer.writeObject(pedidosJson);
            pedidosJsonString = stringWriter.toString();
        } catch (Exception ex) {
            Logger.getLogger(DAOFactura.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pedidosJsonString;
    }
}
