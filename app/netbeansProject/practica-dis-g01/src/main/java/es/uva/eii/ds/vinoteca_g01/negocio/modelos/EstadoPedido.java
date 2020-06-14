package es.uva.eii.ds.vinoteca_g01.negocio.modelos;

/**
 * Enumerado que representa los estados que puede tener un pedido
 * 
 * @author ricalba
 * @author silmont
 * @author marrobl
 * 
 */
public enum EstadoPedido {
    pendiente,
    tramitado,
    completado,
    servido,
    facturado,
    pagado
}
