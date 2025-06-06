package com.gl.app;

import com.gl.app.exception.BaggageNotFoundException;
import com.gl.app.service.BaggageService;
import com.gl.app.service.impl.BaggageServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

public class BaggageServiceTest {

    private BaggageService baggageService;

    @BeforeEach
    public void setUp() {
        baggageService = new BaggageServiceImpl();
    }

    @Test
    public void testGetBaggageStatus() throws SQLException {
        String testClaimId = "C1001"; // Replace with a valid test claimId in your DB

        try {
            String status = baggageService.getBaggageStatus(testClaimId);
            assertNotNull(status);
            assertEquals("Checked In", status); // Update expected status as per DB test value
        } catch (BaggageNotFoundException e) {
            fail("Baggage not found: " + e.getMessage());
        }
    }
}
