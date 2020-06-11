/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.interfaz;

import es.uva.eii.ds.vinoteca_g01.interfaz.pares_vista_control.empleado.VistaIdentificarse;
import es.uva.eii.ds.vinoteca_g01.interfaz.pares_vista_control.empleado.VistaPersonalAlmacen;
import es.uva.eii.ds.vinoteca_g01.interfaz.pares_vista_control.empleado.VistaPersonalAtencionCliente;
import es.uva.eii.ds.vinoteca_g01.interfaz.pares_vista_control.empleado.VistaPersonalContabilidad;
import es.uva.eii.ds.vinoteca_g01.negocio.modelos.TipoDeRol;
import javax.swing.JFrame;

/**
 *
 * @author richard
 */
public class GestorDeInterfazDeUsuario {

    private static GestorDeInterfazDeUsuario gestorIU;

    public static GestorDeInterfazDeUsuario getInstance() {
        if (gestorIU == null) {
            gestorIU = new GestorDeInterfazDeUsuario();
        }

        return gestorIU;
    }

    private JFrame estadoActual;

    private GestorDeInterfazDeUsuario() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                estadoActual = new VistaIdentificarse();
                estadoActual.setVisible(true);
            }
        });
    }

    public void cambiarVistaSegunRol(TipoDeRol rol) {
        estadoActual.setVisible(false);
        estadoActual.dispose();

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (rol == TipoDeRol.Almacen) {
                    estadoActual = new VistaPersonalAlmacen();
                } else if (rol == TipoDeRol.AtencionCliente) {
                    estadoActual = new VistaPersonalAtencionCliente();
                } else if (rol == TipoDeRol.Contabilidad) {
                    estadoActual = new VistaPersonalContabilidad();
                }
                
                estadoActual.setVisible(true);
            }
        });
    }
}
