package com.gl.app.util;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicInteger;

public class HitachiUtil {
	
	private static final String URL = "jdbc:postgresql://localhost:5432/HitachiTelicomData";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";
    static AtomicInteger counter = new AtomicInteger();

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    public int generateUniqueId(String columnName, String tableName, int initialValue) {
       int newId =initialValue;
    	try {
    	   Connection conn = getConnection();
    	   Statement st = conn.createStatement();
           ResultSet rs = st.executeQuery("SELECT MAX(" + columnName + ") FROM " + tableName);
           if (rs.next()) {
               int maxId = rs.getInt(1);
               newId =  (rs.wasNull()) ? initialValue : maxId + 1;
           }
       }
       catch (SQLException e) {
           e.printStackTrace();
       }
 
        return newId;
    }

}
