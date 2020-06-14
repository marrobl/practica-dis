package es.uva.eii.ds.vinoteca_g01.interfaz;

import es.uva.eii.ds.vinoteca_g01.interfaz.pares_vista_control.empleado.VistaConsultarImpagos;
import es.uva.eii.ds.vinoteca_g01.interfaz.pares_vista_control.empleado.VistaCrearPedidoAbonado;
import es.uva.eii.ds.vinoteca_g01.interfaz.pares_vista_control.empleado.VistaIdentificarse;
import es.uva.eii.ds.vinoteca_g01.interfaz.pares_vista_control.empleado.VistaPersonalAlmacen;
import es.uva.eii.ds.vinoteca_g01.interfaz.pares_vista_control.empleado.VistaPersonalAtencionCliente;
import es.uva.eii.ds.vinoteca_g01.interfaz.pares_vista_control.empleado.VistaPersonalContabilidad;
import es.uva.eii.ds.vinoteca_g01.interfaz.pares_vista_control.empleado.VistaRegistrarRecepcionCompra;
import es.uva.eii.ds.vinoteca_g01.negocio.modelos.TipoDeRol;
import javax.swing.JFrame;

/**
 *
 * @author ricalba
 * @author silmont
 * @author marrobl
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
    
    public void cambiarAVistaConsultarImpagos() {
        estadoActual.setVisible(false);
        estadoActual.dispose();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                estadoActual = new VistaConsultarImpagos();
                estadoActual.setVisible(true);
            }
        });
    }
    
    public void cambiarAVistaCrearPedidoAbonado() {
        estadoActual.setVisible(false);
        estadoActual.dispose();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                estadoActual = new VistaCrearPedidoAbonado();
                estadoActual.setVisible(true);
            }
        });
    }
    
    public void cambiarAVistaRegistarRecepcionCompra() {
        estadoActual.setVisible(false);
        estadoActual.dispose();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                estadoActual = new VistaRegistrarRecepcionCompra();
                estadoActual.setVisible(true);
            }
        });
    }

    public void volverAVistaPersonalAlmacen() {
        estadoActual.setVisible(false);
        estadoActual.dispose();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                estadoActual = new VistaPersonalAlmacen();
                estadoActual.setVisible(true);
            }
        });
    }
    
    public void volverAVistaPersonalAtencionCliente() {
        estadoActual.setVisible(false);
        estadoActual.dispose();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                estadoActual = new VistaPersonalAtencionCliente();
                estadoActual.setVisible(true);
            }
        });
    }
    
    public void volverAVistaPersonalContabilidad() {
        estadoActual.setVisible(false);
        estadoActual.dispose();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                estadoActual = new VistaPersonalContabilidad();
                estadoActual.setVisible(true);
            }
        });
    }

    public void cerrarSesion() {
        estadoActual.setVisible(false);
        estadoActual.dispose();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                estadoActual = new VistaIdentificarse();
                estadoActual.setVisible(true);
            }
        });
    }
}
