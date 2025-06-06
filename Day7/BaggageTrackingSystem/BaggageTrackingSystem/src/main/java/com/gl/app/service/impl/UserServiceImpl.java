package com.gl.app.service.impl;

import java.sql.SQLException;

import com.gl.app.entity.Baggage;
import com.gl.app.entity.User;
import com.gl.app.exception.BaggageNotFoundException;
import com.gl.app.service.UserService;
import com.gl.app.dao.UserDAO;
import com.gl.app.dao.impl.UserDAOImpl;

public class UserServiceImpl implements UserService {
	UserDAO userDAO = new UserDAOImpl();

	@Override
	public void registerNewUser(User user) throws SQLException {
		try {
			userDAO.registerNewUser(user);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void checkInBaggage(Baggage baggage) throws SQLException {
		userDAO.checkInBaggage(baggage);
	}

	@Override
	public Baggage getBaggageInfo(String claimTagId) throws SQLException {
		try{
			return userDAO.getBaggageInfo(claimTagId);
		}catch(BaggageNotFoundException e) {
			System.out.println(e.getMessage());
	}
		return null;
}
}
