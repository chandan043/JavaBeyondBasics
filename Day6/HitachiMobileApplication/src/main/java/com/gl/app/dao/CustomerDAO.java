package com.gl.app.dao;

import java.sql.SQLException;
import java.util.List;


import com.gl.app.entity.Customer;
import com.gl.app.entity.SIMDetails;
import com.gl.app.exception.CustomerDoesNotExistException;
import com.gl.app.exception.CustomerTableEmptyException;

// CustomerDAO interface
public interface CustomerDAO {
	  List<SIMDetails> fetchSIMDetails(Long customerId) throws SQLException;
	  String updateCustomerAddress(Long customerId, String newAddress) throws CustomerDoesNotExistException ;
	  List<Customer> getAllCustomers() throws CustomerTableEmptyException;
}
