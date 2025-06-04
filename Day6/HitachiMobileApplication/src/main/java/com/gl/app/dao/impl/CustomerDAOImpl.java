package com.gl.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.gl.app.dao.CustomerDAO;
import com.gl.app.entity.Customer;
import com.gl.app.entity.SIMDetails;
import com.gl.app.exception.CustomerDoesNotExistException;
import com.gl.app.exception.CustomerTableEmptyException;
import com.gl.app.util.HitachiUtil;

public class CustomerDAOImpl implements CustomerDAO{

	Connection con = HitachiUtil.getConnection();
	@Override
	public String updateCustomerAddress(Long customerId, String newAddress) throws CustomerDoesNotExistException {
	    String updateQuery = "UPDATE Customer SET address = ? WHERE customer_id = ?";

	    try (Connection con = HitachiUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(updateQuery)) {

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
		try {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from customer");

		if(!rs.isBeforeFirst())
			throw new CustomerTableEmptyException("Customer_EmptyTable");
		while(rs.next()) {
			Customer c =new Customer(
			rs.getInt("customer_id"),
			rs.getString("date_of_birth"),
			rs.getString("email_address"),
			rs.getString("first_name"),
			rs.getString("last_name"),
			rs.getString("id_type"),
			rs.getString("address"),
			rs.getString("state"));
			
			customers.add(c);
		}
		
	   } catch (SQLException e) {
		e.printStackTrace();
	   }
	   return customers;
	}

	@Override

	public List<SIMDetails> fetchSIMDetails(Long customerId) throws SQLException {
	    List<SIMDetails> SIMDetails= new ArrayList<>();
		Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery("select * from sim_details where customer_id ="+ customerId);
	    while (rs.next()) {
            SIMDetails sim = new SIMDetails(
                rs.getInt("sim_id"),
                rs.getLong("service_number"),
                rs.getLong("sim_number"),
                rs.getString("status"),
                rs.getLong("customer_id")
            );
            SIMDetails.add(sim);
        }
	    return SIMDetails;
	}
}
