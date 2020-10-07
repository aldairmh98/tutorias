/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aldai
 */
public class ConnectionSQL {

    String databaseName = "tutorias";
    String user = "root";
    String pswd = "";
    private static Connection conection = null;

    private ConnectionSQL() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            ConnectionSQL.conection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + databaseName, user, pswd);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getConnection() {
        if(ConnectionSQL.conection == null){
            try {
                new ConnectionSQL();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ConnectionSQL.conection;
    }
}
