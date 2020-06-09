/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.vinoteca_g01.persistencia.dbaccess;

/**
 *
 * @author richard
 */
public class DBConnection {
    
    private DBConnection() {
    }
    
    public static DBConnection getInstance() {
        return DBConnectionHolder.INSTANCE;
    }
    
    private static class DBConnectionHolder {

        private static final DBConnection INSTANCE = new DBConnection();
    }
}
