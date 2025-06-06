package com.gl.app.dao;

import java.sql.SQLException;

import com.gl.app.entity.Baggage;
import com.gl.app.entity.User;
import com.gl.app.exception.BaggageNotFoundException;

public interface UserDAO {
	public void registerNewUser(User user) throws SQLException;

	public void checkInBaggage(Baggage baggage) throws SQLException;

	public Baggage getBaggageInfo(String claimTagId) throws SQLException,BaggageNotFoundException;

}
