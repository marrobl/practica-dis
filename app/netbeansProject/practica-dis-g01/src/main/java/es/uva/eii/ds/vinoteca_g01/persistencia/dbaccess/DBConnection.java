package es.uva.eii.ds.vinoteca_g01.persistencia.dbaccess;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ricalba
 * @author silmont
 * @author marrobl
 */
public class DBConnection {

    private static DBConnection theDBConnection;

    public static DBConnection getInstance() {
        if (theDBConnection == null) {
            Properties prop = new Properties();
            InputStream read;
            String url, user, password;
            try {
                FileInputStream fis = new FileInputStream("../../db/config.db");
                prop.load(fis);
                url = prop.getProperty("url");
                user = prop.getProperty("user");
                password = prop.getProperty("password");
                fis.close();
                theDBConnection = new DBConnection(url, user, password);
            } catch (FileNotFoundException e) {
                System.err.println("DB configuration file not found.");
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, "DB configuration file not found.");
            } catch (IOException e) {
                System.err.println("Couln't read DB configuration file.");
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, "Couln't read DB configuration file.");
            }
        }
        return theDBConnection;
    }

    private Connection conn;
    private String url;
    private String user;
    private String password;

    private DBConnection(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Derby driver not found.");
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, "Derby driver not found.");
        }
    }

    public void openConnection() {
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PreparedStatement getStatement(String s) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(s);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stmt;
    }
}
