package com.gl.app.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.gl.app.dao.CustomerDAO;
import com.gl.app.entity.Customer;
import com.gl.app.entity.SIMDetails;
import com.gl.app.exception.CustomerDoesNotExistException;
import com.gl.app.exception.CustomerTableEmptyException;
import com.gl.app.util.HitachiUtil;

public class CustomerDAOImpl implements CustomerDAO {

    private Connection con;

    public CustomerDAOImpl() {
        try {
            this.con = HitachiUtil.getConnection(); // ✅ Properly initialized in constructor
        } catch (SQLException e) {
            throw new RuntimeException("DB connection failed", e);
        }
    }

    @Override
    public String updateCustomerAddress(Long customerId, String newAddress) throws CustomerDoesNotExistException {
        String updateQuery = "UPDATE Customer SET address = ? WHERE customer_id = ?";

        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setString(1, newAddress);
            ps.setLong(2, customerId);

            int result = ps.executeUpdate();

            if (result != 1) {
                throw new CustomerDoesNotExistException("Customer_Invalid");
            }

            return "Update Successful";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Update Failed Due to Database Error";
        }
    }

    @Override
    public List<Customer> getAllCustomers() throws CustomerTableEmptyException {
        List<Customer> customers = new ArrayList<>();
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM customer")) {

            if (!rs.isBeforeFirst()) {
                throw new CustomerTableEmptyException("Customer_EmptyTable");
            }

            while (rs.next()) {
                Customer c = new Customer(
                    rs.getInt("customer_id"),
                    rs.getString("date_of_birth"),
                    rs.getString("email_address"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("id_type"),
                    rs.getString("address"),
                    rs.getString("state")
                );
                customers.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    @Override
    public List<SIMDetails> fetchSIMDetails(Long customerId) throws SQLException {
        List<SIMDetails> simDetailsList = new ArrayList<>();
        String query = "SELECT * FROM sim_details WHERE customer_id = ?";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setLong(1, customerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SIMDetails sim = new SIMDetails(
                    rs.getInt("sim_id"),
                    rs.getLong("service_number"),
                    rs.getLong("sim_number"),
                    rs.getString("status"),
                    rs.getLong("customer_id")
                );
                simDetailsList.add(sim);
            }

        }

        return simDetailsList;
    }
}
