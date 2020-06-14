package es.uva.eii.ds.vinoteca_g01.negocio.controladoresCasoUso;

import es.uva.eii.ds.vinoteca_g01.negocio.modelos.Abonado;
import es.uva.eii.ds.vinoteca_g01.negocio.modelos.Pedido;
import es.uva.eii.ds.vinoteca_g01.negocio.modelos.Referencia;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.AbonadoNoExisteException;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.ImpagosAbonadoException;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.ReferenciaNoDisponibleException;
import es.uva.eii.ds.vinoteca_g01.servicioscomunes.excepciones.ReferenciaNoExisteException;
import java.time.LocalDate;

/**
 *
 * @author ricalba
 * @author silmont
 * @author marrobl
 */
public class ControladorCUCrearPedidoAbonado {
    
    private Abonado abonado;
    private Pedido pedido;

    public Abonado getAbonadoPorId(int numAbonado) throws AbonadoNoExisteException {
        abonado = Abonado.getAbonado(numAbonado);
        
        return abonado;
    }

    public void compruebaImpagosAbonado() throws ImpagosAbonadoException {
        boolean impagos = Pedido.hayPedidosVencidosPorNumeroAbonado(abonado.getNumeroAbonado());
        
        if (impagos) {
            throw new ImpagosAbonadoException();
        }
        
        pedido = new Pedido();
    }

    public void comprobarReferencia(int numRef, int cantidad) throws ReferenciaNoExisteException, ReferenciaNoDisponibleException {
        Referencia referencia = Referencia.getReferenciaPorCodigo(numRef);
        
        if (!referencia.isDisponible()) {
            throw new ReferenciaNoDisponibleException();
        }
        
        pedido.crearLineaPedido(referencia, cantidad);
    }

    public void registrarPedido() {
        pedido.setFechaRealizacion(LocalDate.now());
        pedido.calcularImporte();
        pedido.setNumeroAbonado(abonado.getNumeroAbonado());
        String json = pedido.toJson();
        Pedido.registrarPedido(json);
    }
}
