package com.gl.app.test;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import com.gl.app.entity.SIMDetails;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.gl.app.service.SIMDetailsService;
import com.gl.app.service.impl.SIMDetailsServiceImpl;
import com.gl.app.util.HitachiUtil;

class SIMDetailsServiceTest {

	
	@Test
	 public void testFetchSIMDetailsWithActiveStatus() throws SQLException {
		 SIMDetailsService simDetailsService = new SIMDetailsServiceImpl();
		 List<SIMDetails> actualActiveSimDetails = simDetailsService.fetchSIMDetailsWithActiveStatus();
		 List<SIMDetails> expectedActiveSimDetails = new ArrayList<>();
		 Connection con=HitachiUtil.getConnection();
		 Statement st = con.createStatement();
		 ResultSet rs = st.executeQuery("SELECT * FROM sim_details where status='Active'");
		 while(rs.next()) {
			 SIMDetails sim = new SIMDetails(
		                rs.getLong("sim_id"),
		                rs.getLong("service_number"),
		                rs.getLong("sim_number"),
		                rs.getString("status"),
		                rs.getLong("customer_id")
		            );
			 expectedActiveSimDetails.add(sim);
		 }
		 Assertions.assertEquals(expectedActiveSimDetails, actualActiveSimDetails);
	    }
}
