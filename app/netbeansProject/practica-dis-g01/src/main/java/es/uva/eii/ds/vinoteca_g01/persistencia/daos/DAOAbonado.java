/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.persistencia.daos;

import es.uva.eii.ds.vinoteca_g01.persistencia.dbaccess.DBConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author richard
 */
public class DAOAbonado {

    private static final String SELECT_ABONADO_POR_NUMERO = 
            "SELECT * FROM Persona P, Abonado A WHERE P.nif=A.nif AND A.numeroAbonado=?";
    public static String consultarAbonadoPorId(int numAbonado) {
        String abonadoJsonString = null;
        
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        
        try {
            PreparedStatement ps = connection.getStatement(SELECT_ABONADO_POR_NUMERO);
            ps.setInt(1, numAbonado);
        } catch(SQLException ex) {
            Logger.getLogger(DAOAbonado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return abonadoJsonString;
    }
    
}
