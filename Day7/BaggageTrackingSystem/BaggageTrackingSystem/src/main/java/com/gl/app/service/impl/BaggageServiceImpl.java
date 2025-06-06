package com.gl.app.service.impl;

import java.sql.SQLException;
import java.util.List;
import com.gl.app.entity.Baggage;
import com.gl.app.exception.BaggageNotFoundException;
import com.gl.app.service.BaggageService;
import com.gl.app.dao.BaggageDAO;
import com.gl.app.dao.impl.BaggageDAOImpl;

public class BaggageServiceImpl implements BaggageService {

	BaggageDAO baggageDAO = new BaggageDAOImpl();

	@Override
	public String getBaggageStatus(String claimTagId) throws SQLException, BaggageNotFoundException {
		try{
			return baggageDAO.getBaggageStatus(claimTagId);
		}
		catch (BaggageNotFoundException e) {
			throw new BaggageNotFoundException("No baggage found with claimID: " + claimTagId);
		}
	}

	@Override
	public void updateBaggageStatus(String claimTagId, String status) throws SQLException, BaggageNotFoundException {
		try{
			baggageDAO.updateBaggageStatus(claimTagId, status);
		}
		catch (BaggageNotFoundException e) {
			throw new BaggageNotFoundException("No baggage found with claimID: " + claimTagId);
		}

	}

	@Override
	public String getBaggageLocation(String claimTagId) throws SQLException {
		try{
			return baggageDAO.getBaggageLocation(claimTagId);
		}
		catch (BaggageNotFoundException e) {
			throw new BaggageNotFoundException("No baggage found with claimID: " + claimTagId);
		}
	}

	@Override
	public void updateBaggageLocation(String claimTagId, String location) throws SQLException {
		try{
			baggageDAO.updateBaggageLocation(claimTagId, location);
		}
		catch (BaggageNotFoundException e) {
			throw new BaggageNotFoundException("No baggage found with claimID: " + claimTagId);
		}
	}

	@Override
	public void claimBaggage(String claimTagId) throws SQLException {
		try{
			baggageDAO.claimBaggage(claimTagId);
		}
		catch (BaggageNotFoundException e) {
			throw new BaggageNotFoundException("No baggage found with claimID: " + claimTagId);
		}
	}

	@Override
	public List<Baggage> getAllCheckedInBaggage() throws SQLException {
		try{
			return baggageDAO.getAllCheckedInBaggage();
		}
		catch (BaggageNotFoundException e) {
			throw new BaggageNotFoundException("No CheckedIn Baggage Found");
		}
	}

}
