/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminsoftware;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = System.getenv().getOrDefault("JDBC_URL", "jdbc:mysql://localhost:3306/adminsoftware_2");
    private static final String USER = System.getenv().getOrDefault("JDBC_USER", "root");
    private static final String PASSWORD = System.getenv().getOrDefault("JDBC_PASSWORD", "root24");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}