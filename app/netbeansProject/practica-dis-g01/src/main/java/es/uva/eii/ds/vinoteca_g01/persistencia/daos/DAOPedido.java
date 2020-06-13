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
    private static final String SELECT_PEDIDOS_TRAMITADOS = "SELECT * FROM Pedido P WHERE P.Estado = 2 ";
    private static final String SELECT_PEDIDOS_VENCIDOS_NUM_ABONADO = "SELECT * FROM Pedido P, Factura F, EstadoFactura EF "
            + "WHERE P.numeroAbonado=? AND P.numeroFactura=F.numeroFactura AND F.estado=EF.id AND EF.nombre='vencida'";
    private static final String INSERT_NUEVO_PEDIDO
            = "INSERT INTO Pedido (Estado, FechaRealizacion, NotaEntrega, Importe, FechaRecepcion, FechaEntrega, NumeroFactura, NumeroAbonado) "
            + "VALUES (?, ?, NULL, ?, NULL, NULL, NULL, ?)";

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

        if (pedidosVencidos.charAt(pedidosVencidos.length() - 1) == ',') {
            pedidosVencidos.deleteCharAt(pedidosVencidos.length() - 1);
        }

        pedidosVencidos.append("]");

        pedidosVencidosJsonString = obtenerPedidosJsonString(pedidosVencidos.toString());

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
            Logger.getLogger(DAOPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pedidosJsonString;
    }

    public static void insertarPedidoAPartirDeJSON(String json) {
        int estado = 0;
        String fechaRealizacion = "";
        double importe = 0;
        int numeroAbonado = 0;
        String lineasPedidoJson = "[]";

        JsonReaderFactory factory = Json.createReaderFactory(null);

        try {
            JsonReader reader = factory.createReader(new StringReader(json));
            JsonObject pedidoJSON = reader.readObject();
            estado = pedidoJSON.getInt("estado");
            fechaRealizacion = pedidoJSON.getString("fechaRealizacion");
            importe = pedidoJSON.getJsonNumber("importe").bigDecimalValue().doubleValue();
            numeroAbonado = pedidoJSON.getJsonNumber("numeroAbonado").intValue();

            StringWriter stringWriter = new StringWriter();
            JsonWriter writer = Json.createWriter(stringWriter);
            JsonArray lineasPedidoJsonArray = pedidoJSON.getJsonArray("lineasPedido");
            writer.writeArray(lineasPedidoJsonArray);
            lineasPedidoJson = stringWriter.toString();

            DBConnection connection = DBConnection.getInstance();
            connection.openConnection();

            try {
                PreparedStatement ps = connection.getStatement(INSERT_NUEVO_PEDIDO);
                ps.setInt(1, estado);
                ps.setDate(2, Date.valueOf(LocalDate.parse(fechaRealizacion)));
                ps.setDouble(3, importe);
                ps.setInt(4, numeroAbonado);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DAOPedido.class.getName()).log(Level.SEVERE, null, ex);
            }

            connection.closeConnection();
        } catch (Exception ex) {
            Logger.getLogger(DAOPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

        DAOLineaPedido.insertarLineasPedidoAPartirDeJson(lineasPedidoJson);
    }

    public static String consultaPedidosLineasPedidoTramitados() {
        String pedidosJsonString;

        int numero, estado, numeroFactura, numeroAbonado;
        double importe;
        LocalDate fechaRealizacion, fechaRecepcion, fechaEntrega;
        String notaEntrega;

        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        JsonObjectBuilder jb = Json.createObjectBuilder();

        StringBuilder pedidos = new StringBuilder("[");

        try {
            PreparedStatement ps = connection.getStatement(SELECT_PEDIDOS_TRAMITADOS);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                numero = rs.getInt("Numero");
                estado = rs.getInt("Estado");
                fechaRealizacion = rs.getDate("FechaRealizacion").toLocalDate();
                notaEntrega = rs.getString("NotaEntrega");
                importe = rs.getDouble("Importe");
                fechaRecepcion = rs.getDate("FechaRecepcion").toLocalDate();
                fechaEntrega = rs.getDate("FechaEntrega").toLocalDate();
                numeroFactura = rs.getInt("NumeroFactura");
                numeroAbonado = rs.getInt("NumeroAbonado");

                String lineasPedido = DAOLineaPedido.consultarLineasPedidoNumPedido(numero);

                pedidos.append(obtenerPedidoLineasPedidoJsonString(Integer.toString(numero), Integer.toString(estado),
                        fechaRealizacion.toString(), notaEntrega, Double.toString(importe), fechaRecepcion.toString(),
                        fechaEntrega.toString(), Integer.toString(numeroFactura), Integer.toString(numeroAbonado),
                        lineasPedido));
                pedidos.append(",");

            }

            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOFactura.class.getName()).log(Level.SEVERE, null, ex);
        }

        connection.closeConnection();

        if (pedidos.charAt(pedidos.length() - 1) == ',') {
            pedidos.deleteCharAt(pedidos.length() - 1);
        }

        pedidos.append("]");

        pedidosJsonString = obtenerPedidosLineaPedidoJsonString(pedidos.toString());

        return pedidosJsonString;

    }

    private static String obtenerPedidoLineasPedidoJsonString(String numero, String estado, String fechaRealizacion, String notaEntrega, String importe, String fechaRecepcion, String fechaEntrega, String numeroFactura, String numeroAbonado, String lineasPedido) {
        String pedidoLineasPedidoJsonString = "";
        JsonReaderFactory factory = Json.createReaderFactory(null);

        try {
            JsonReader readerLineasPedido = factory.createReader(new StringReader(lineasPedido));

            StringWriter stringWriter = new StringWriter();
            JsonWriter writer = Json.createWriter(stringWriter);

            JsonArray lineasPedidoJsonArray = readerLineasPedido.readArray();

            JsonObject pedidoLineasPedidoJson = Json.createObjectBuilder()
                    .add("numero", numero)
                    .add("estado", estado)
                    .add("fechaRealizacion", fechaRealizacion)
                    .add("notaEntrega", notaEntrega)
                    .add("importe", importe)
                    .add("fechaRecepcion", fechaRecepcion)
                    .add("fechaEntrega", fechaEntrega)
                    .add("numeroFactura", numeroFactura)
                    .add("numeroAbonado", numeroAbonado)
                    .add("lineasPedido", lineasPedidoJsonArray)
                    .build();

            writer.writeObject(pedidoLineasPedidoJson);
            pedidoLineasPedidoJsonString = stringWriter.toString();
        } catch (Exception ex) {
            Logger.getLogger(DAOPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pedidoLineasPedidoJsonString;
    }

    private static String obtenerPedidosLineaPedidoJsonString(String pedidos) {
        String pedidosLineasPedidoJsonString = "";
        JsonReaderFactory factory = Json.createReaderFactory(null);

        try {
            JsonReader readerPedidosLineasPedido = factory.createReader(new StringReader(pedidos));
            StringWriter stringWriter = new StringWriter();
            JsonWriter writer = Json.createWriter(stringWriter);

            JsonArray pedidosLineasPedidoJsonArray = readerPedidosLineasPedido.readArray();

            JsonObject pedidosLineasPedidoJson = Json.createObjectBuilder()
                    .add("pedidosLineasPedido", pedidosLineasPedidoJsonArray)
                    .build();

            writer.writeObject(pedidosLineasPedidoJson);
            pedidosLineasPedidoJsonString = stringWriter.toString();
        } catch (Exception ex) {
            Logger.getLogger(DAOPedido.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pedidosLineasPedidoJsonString;
    }

}
