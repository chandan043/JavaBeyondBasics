package com.gl.app.dao;

import java.util.List;

import com.gl.app.entity.SIMDetails;
import com.gl.app.exception.SIMDoesNotExistsException;

public interface SIMDetailsDAO {
	public List<SIMDetails> fetchSIMDetailsWithActiveStatus();
	public String getSimStatus(long simNumber, long serviceNumber) throws SIMDoesNotExistsException;
	public List<SIMDetails> fetchSIMDetails();
}
