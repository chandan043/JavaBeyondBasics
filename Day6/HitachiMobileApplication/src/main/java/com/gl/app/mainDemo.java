package com.gl.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.gl.app.entity.SIMDetails;
import java.util.ArrayList;
import java.util.List;

public class mainDemo {

    public static void main(String[] args) {
        final String URL = "jdbc:postgresql://localhost:5432/HitachiTelicomData";
        final String USER = "postgres";
        final String PASSWORD = "root";

        List<SIMDetails> simDetailsList = new ArrayList<>();

        try (
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM sim_details")
        ) {
            Class.forName("org.postgresql.Driver");

            while (rs.next()) {
                SIMDetails sim = new SIMDetails(
                    rs.getLong("sim_id"),
                    rs.getLong("service_number"),
                    rs.getLong("sim_number"),
                    rs.getString("status"),
                    rs.getLong("customer_id")
                );
                simDetailsList.add(sim);
            }

            // Print all SIM details
            simDetailsList.forEach(System.out::println);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
