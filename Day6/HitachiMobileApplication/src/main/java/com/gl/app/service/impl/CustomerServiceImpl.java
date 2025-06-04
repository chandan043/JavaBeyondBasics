package com.gl.app.service.impl;
import com.gl.app.dao.*;
import com.gl.app.entity.*;
import com.gl.app.dao.impl.*;
import java.util.List;
import java.sql.SQLException;
import com.gl.app.exception.CustomerDoesNotExistException;
import com.gl.app.exception.CustomerTableEmptyException;
import com.gl.app.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
	CustomerDAO customerDAO = new CustomerDAOImpl();
	
	public List<SIMDetails> fetchCustomerList(Long customerId) throws SQLException{
			return customerDAO.fetchSIMDetails(customerId);
	}

	@Override
	 public String updateCustomerAddress(Long customerId, String city) throws CustomerDoesNotExistException,SQLException {
        return customerDAO.updateCustomerAddress(customerId,city);
    }

	@Override
	public List<Customer> getAllCustomers() throws CustomerTableEmptyException {
		List<Customer> customers;
		try {
			customers=customerDAO.getAllCustomers();
		}
		catch(CustomerTableEmptyException e){
			throw new CustomerTableEmptyException("Customer_EmptyTable");
		}
		
		return customers;
	}
	
}
