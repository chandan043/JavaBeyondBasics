package com.gl.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

public class ContactUtil {
    private static final String CONFIG_FILE = "configurations.properties";

    public static Connection getConnection() throws SQLException,IOException {
        Properties props = new Properties();
        try (InputStream input = ContactUtil.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
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
        }}

    public static int generateUniqueId(String columnName, String tableName, int initialValue) throws IOException {
        AtomicInteger id = new AtomicInteger(initialValue);
        String query = String.format("SELECT %s as id FROM %s ORDER BY %s DESC LIMIT 1", columnName, tableName, columnName);

        try (
            Connection conn = ContactUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)
        ) {
            if (rs.next()) {
                id.set(rs.getInt("id") + 1);
            }
        } catch (SQLException e) {
            System.err.println("Error generating unique ID: " + e.getMessage());
        }

        return id.get();
    }
}
