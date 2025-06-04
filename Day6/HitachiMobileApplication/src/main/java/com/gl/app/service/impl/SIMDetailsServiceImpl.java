package com.gl.app.service.impl;
import java.util.List;
import com.gl.app.dao.*;
import com.gl.app.dao.impl.*;
import com.gl.app.entity.SIMDetails;
import com.gl.app.exception.SIMDoesNotExistsException;
import com.gl.app.service.SIMDetailsService;


public class SIMDetailsServiceImpl implements SIMDetailsService{
	SIMDetailsDAO simDetailsDAO = new SIMDetailsDAOImpl();

	@Override
	public List<SIMDetails> fetchSIMDetailsWithActiveStatus() {
		        return simDetailsDAO.fetchSIMDetailsWithActiveStatus();
	    }

	@Override
	 public String getSimStatus(long simNumber,long serviceNumber) throws SIMDoesNotExistsException {
		try{
			return simDetailsDAO.getSimStatus(simNumber,serviceNumber);
		}
		catch(SIMDoesNotExistsException e){
			throw e;
		}
    }
	
		
	}


