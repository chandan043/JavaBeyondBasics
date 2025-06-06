package com.gl.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class BaggageUtil {
	private static final String CONFIG_FILE = "configurations.properties";
    public static Connection getConnection() throws SQLException {
        Properties props = new Properties();
        try (InputStream input = BaggageUtil.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
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

    public String generateUniqueId(String columnName, String tableName, String prefix, int initialValue) {
        int newId = initialValue;

        String sql = "SELECT MAX(CAST(SUBSTRING(" + columnName + ", LENGTH(?) + 1) AS INTEGER)) FROM " + tableName;

        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, prefix);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int maxId = rs.getInt(1); // e.g. 1, 2, 3
                newId = rs.wasNull() ? initialValue : maxId + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prefix + String.format("%03d", newId);
    }

 }
