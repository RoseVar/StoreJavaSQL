/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.store.model.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author tarda
 */
public class DbConnect {
//static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";      
    static final String PROTOCOL = "jdbc:mysql:";
    static final String HOST = "127.0.0.1";
    static final String BD_NAME = "dbstoreapp";
    static final String USER = "usrstoreapp";
    static final String PASSWORD = "pswstore";

    public static void loadDriver() throws ClassNotFoundException {
        Class.forName(DRIVER);
    }
    
    /**
     * gets and returns a connection to database
     *
     * @return connection
     * @throws java.sql.SQLException
     */
    public static Connection getConnection() throws SQLException {
        final String BD_URL = String.format("%s//%s/%s", PROTOCOL, HOST, BD_NAME);
        Connection conn = null;
        conn = DriverManager.getConnection(BD_URL, USER, PASSWORD);
        return conn;
    }
}

