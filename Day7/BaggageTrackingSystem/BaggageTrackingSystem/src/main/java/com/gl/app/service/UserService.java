package com.gl.app.service;

import java.sql.SQLException;

import com.gl.app.entity.Baggage;
import com.gl.app.entity.User;

public interface UserService {
	public void registerNewUser(User user) throws SQLException;

	public void checkInBaggage(Baggage baggage) throws SQLException;

	public Baggage getBaggageInfo(String claimTagId)throws SQLException;

}
