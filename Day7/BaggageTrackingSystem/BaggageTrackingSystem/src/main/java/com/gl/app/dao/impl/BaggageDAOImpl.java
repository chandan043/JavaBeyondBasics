package com.gl.app.dao.impl;

import com.gl.app.dao.BaggageDAO;
import com.gl.app.entity.Baggage;
import com.gl.app.exception.BaggageNotFoundException;
import com.gl.app.util.BaggageUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaggageDAOImpl implements BaggageDAO {
	private Connection connection;

	public BaggageDAOImpl() {
		try {
			connection = BaggageUtil.getConnection();
		} catch (Exception e) {
			System.out.println("Error getting connection: " + e.getMessage());
		}
	}

	@Override
	public String getBaggageStatus(String claimTagId) throws SQLException, BaggageNotFoundException {
		String sql = "SELECT status FROM Baggage WHERE claimID = ?";
		try (PreparedStatement st = connection.prepareStatement(sql)) {
			st.setString(1, claimTagId);
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					return rs.getString("status");
				} else {
					throw new BaggageNotFoundException("No baggage found with claimID: " + claimTagId);
				}
			}
		}
	}

	@Override
	public void updateBaggageStatus(String claimTagId, String status) throws SQLException, BaggageNotFoundException {
		String sql = "UPDATE Baggage SET status = ? WHERE claimID = ?";
		try (PreparedStatement st = connection.prepareStatement(sql)) {
			st.setString(1, status);
			st.setString(2, claimTagId);
			int rowsUpdated = st.executeUpdate();

			if (rowsUpdated == 0) {
				throw new BaggageNotFoundException("No baggage found with claimID: " + claimTagId);
			}
		}
	}

	@Override
	public String getBaggageLocation(String claimTagId) throws SQLException, BaggageNotFoundException {
		String sql = "SELECT location FROM Baggage WHERE claimID = ?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, claimTagId);
			try (ResultSet rs = statement.executeQuery()) {
				if (rs.next()) {
					return rs.getString("location");
				} else {
					throw new BaggageNotFoundException("No baggage found with claimID: " + claimTagId);
				}
			}
		}
	}

	@Override
	public void updateBaggageLocation(String claimTagId, String location) throws SQLException, BaggageNotFoundException {
		String sql = "UPDATE Baggage SET location = ? WHERE claimID = ?";
		try (PreparedStatement st = connection.prepareStatement(sql)) {
			st.setString(1, location);
			st.setString(2, claimTagId);
			int rowsUpdated = st.executeUpdate();

			if (rowsUpdated == 0) {
				throw new BaggageNotFoundException("No baggage found with claimID: " + claimTagId);
			}
		}
	}

	@Override
	public void claimBaggage(String claimTagId) throws SQLException, BaggageNotFoundException {
		String sql = "DELETE FROM Baggage WHERE claimID = ?";
		try (PreparedStatement st = connection.prepareStatement(sql)) {
			st.setString(1, claimTagId);
			int rowsDeleted = st.executeUpdate();

			if (rowsDeleted == 0) {
				throw new BaggageNotFoundException("No baggage found with claimID: " + claimTagId);
			}
		}
	}

	@Override
	public List<Baggage> getAllCheckedInBaggage() throws SQLException, BaggageNotFoundException {
		String sql = "SELECT * FROM Baggage WHERE status = 'CheckedIn'";
		try (PreparedStatement statement = connection.prepareStatement(sql);
			 ResultSet rs = statement.executeQuery()) {

			List<Baggage> baggageList = new ArrayList<>();

			while (rs.next()) {
				Baggage baggage = new Baggage(
						rs.getString("claimId"),
						rs.getString("location"),
						rs.getString("status"),
						rs.getString("userId")
				);
				baggageList.add(baggage);
			}

			if (baggageList.isEmpty()) {
				throw new BaggageNotFoundException("No CheckedIn baggage found.");
			}

			return baggageList;
		}
	}
}
