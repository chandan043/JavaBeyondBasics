package com.gl.app.client;

import java.sql.*;
import com.gl.app.entity.Baggage;
import com.gl.app.exception.BaggageNotFoundException;
import com.gl.app.util.BaggageUtil;

public class demo {

    public Baggage getBaggageInfo(String claimTagId) throws SQLException, BaggageNotFoundException {
        String sql = "SELECT * FROM Baggage WHERE claimId = ?";

        try (Connection connection = BaggageUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

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
                throw new BaggageNotFoundException("Baggage_NotFound");
            }

        }
    }

    public static void main(String[] args) {
        demo app = new demo();

        try {
            Baggage x = app.getBaggageInfo("C1005");
            System.out.println("Claim ID:"+" "+x.claimId());
			System.out.println("Location:"+" "+x.location());
			System.out.println("Status:"+" "+x.status());
			System.out.println("User ID:"+" "+x.userId());
        } catch (BaggageNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
