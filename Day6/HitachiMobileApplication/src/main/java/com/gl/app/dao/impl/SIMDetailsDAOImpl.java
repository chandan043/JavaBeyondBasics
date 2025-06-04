package com.gl.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.gl.app.dao.SIMDetailsDAO;
import com.gl.app.entity.SIMDetails;
import com.gl.app.exception.SIMDoesNotExistsException;
import com.gl.app.util.HitachiUtil;

public class SIMDetailsDAOImpl implements SIMDetailsDAO{
HitachiUtil hitachiUtil = new HitachiUtil();
	
	
@Override
public List<SIMDetails> fetchSIMDetailsWithActiveStatus() {
	List<SIMDetails> activeSimDetails = fetchSIMDetails().stream()
		    .filter(x -> "Active".equalsIgnoreCase(x.status()))
		    .collect(Collectors.toList());

    return activeSimDetails;
}


	@Override
	public String getSimStatus(long simNumber, long serviceNumber) throws SIMDoesNotExistsException {
	    return fetchSIMDetails().stream()
	        .filter(sim -> sim.simNumber() == simNumber && sim.serviceNumber() == serviceNumber)
	        .map(SIMDetails::status)
	        .findFirst()
	        .orElseThrow(() -> new SIMDoesNotExistsException("SIM_NOTFOUND"));
	}
	
	
	
	@Override
	public List<SIMDetails> fetchSIMDetails(){
		List<SIMDetails> SimDetails = new ArrayList<>();
	    
	    try (Connection conn = HitachiUtil.getConnection();
	         PreparedStatement ps = conn.prepareStatement("SELECT * FROM sim_details");
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            SIMDetails sim = new SIMDetails(
	                rs.getLong("sim_id"),
	                rs.getLong("service_number"),
	                rs.getLong("sim_number"),
	                rs.getString("status"),
	                rs.getLong("customer_id")
	            );
	            SimDetails.add(sim);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return SimDetails;
	}
	

	
}
