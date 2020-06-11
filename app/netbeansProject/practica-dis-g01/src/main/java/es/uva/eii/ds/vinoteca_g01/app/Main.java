/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.app;

import es.uva.eii.ds.vinoteca_g01.interfaz.GestorDeInterfazDeUsuario;

/**
 *
 * @author richard
 */
public class Main {
    private static GestorDeInterfazDeUsuario gestorIU;
    
    public static void main(String[] args) {
        GestorDeInterfazDeUsuario.getInstance();
    }
}
