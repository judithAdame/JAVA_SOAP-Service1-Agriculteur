package com.adame.utilitaire;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Connexion {
     //  driver JDBC nom et URL BD
    static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@207.180.227.26:1521:XE";

    //  identifiants de connexion BD
    static final String USER = "yuni";
    static final String PASS = "yuni2019";
    
    public static Connection createConnexion() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        System.out.println("Connecting to database...");
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        return conn;
    }

    public static void cleanConnexion(PreparedStatement stmt, Connection conn) throws SQLException {
        System.out.println("\nClosing to database...");
        stmt.close();
        conn.close();
    }
}