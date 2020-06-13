/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
