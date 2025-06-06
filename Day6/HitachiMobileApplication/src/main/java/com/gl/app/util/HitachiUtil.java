package com.gl.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class HitachiUtil {    
    
    private static final String CONFIG_FILE = "configuration.properties";

    public static Connection getConnection() throws SQLException {
        Properties props = new Properties();
        try (InputStream input = HitachiUtil.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new IOException("Configuration file not found: " + CONFIG_FILE);
            }

            props.load(input);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            return DriverManager.getConnection(url, user, password);
        } catch (IOException e) {
            throw new SQLException("Failed to load DB configuration.", e);
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
