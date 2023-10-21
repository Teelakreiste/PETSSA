/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.*;

/**
 *
 * @author osmel
 */
public class ConnectionDB {

    private Connection con;
    private final String HOST = "localhost";
    private final String PORT = "3306";
    private final String DB = "db_petssa";
    private final String USER = "root";
    private final String PASSWORD = "12345678";
    private final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB
            + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";

    public ConnectionDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println(con);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public Connection getConnection() {
        return con;
    }
}
