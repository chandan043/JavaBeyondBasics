package com.gl.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gl.app.dao.UserDAO;
import com.gl.app.entity.Baggage;
import com.gl.app.entity.User;
import com.gl.app.exception.BaggageNotFoundException;
import com.gl.app.exception.UserNotFoundException;
import com.gl.app.util.BaggageUtil;

public class UserDAOImpl implements UserDAO {
    private Connection connection;

    public UserDAOImpl() {
        try {
            connection = BaggageUtil.getConnection();
        } catch (Exception e) {
            System.out.println("Error getting connection: " + e.getMessage());
        }
    }

    @Override
    public void registerNewUser(User user) throws SQLException, UserNotFoundException {
        String sql = "INSERT INTO Users (userID, firstName, lastName, email) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            pstmt.setString(1, user.userId());
            pstmt.setString(2, user.firstName());
            pstmt.setString(3, user.lastName());
            pstmt.setString(4, user.email());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void checkInBaggage(Baggage baggage) throws SQLException {
        String sql = "UPDATE Baggage SET status = 'Checked In', location = ? WHERE userId = ?";

        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            pstmt.setString(1, baggage.location());
            pstmt.setString(2, baggage.userId());
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated == 0) {
                throw new SQLException("Baggage not found for claimId: " + baggage.claimId());
            }
        }
    }

    @Override
    public Baggage getBaggageInfo(String claimTagId) throws SQLException, BaggageNotFoundException {
        String sql = "SELECT * FROM Baggage WHERE claimId = ?";

        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            pstmt.setString(1, claimTagId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Baggage(
                    rs.getString("claimId"),
                    rs.getString("location"),
                    rs.getString("status"),
                    rs.getString("userId")
                );
            } else {
                throw new BaggageNotFoundException("Baggage not found with claimId: " + claimTagId);
            }
        }
    }
}
