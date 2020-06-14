package es.uva.eii.ds.vinoteca_g01.app;

import es.uva.eii.ds.vinoteca_g01.interfaz.GestorDeInterfazDeUsuario;

/**
 * @author ricalba
 * @author silmont
 * @author marrobl
 */
public class Main {
    private static GestorDeInterfazDeUsuario gestorIU;
    
    public static void main(String[] args) {
        GestorDeInterfazDeUsuario.getInstance();
    }
}
