/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laptrinhmang.controller.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hoang
 */
public class MySQLConnUtils {
    public static Connection getMySQLConnection() throws SQLException,
         ClassNotFoundException {
     String hostName = "localhost";
     String dbName = "ltm";
     String userName = "root";
     String password = "hoang17122000";
 
     return getMySQLConnection(hostName, dbName, userName, password);
 }
 
 public static Connection getMySQLConnection(String hostName, String dbName,
         String userName, String password) throws SQLException,
         ClassNotFoundException {
     
     Class.forName("com.mysql.jdbc.Driver");
 
     String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName+"?zeroDateTimeBehavior=convertToNull";
 
     Connection conn = DriverManager.getConnection(connectionURL, userName,
             password);
     return conn;
 }
}
