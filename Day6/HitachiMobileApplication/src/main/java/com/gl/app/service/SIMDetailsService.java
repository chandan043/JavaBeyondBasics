package com.gl.app.service;

import java.util.List;
import com.gl.app.exception.SIMDoesNotExistsException;

import com.gl.app.entity.SIMDetails;

public interface SIMDetailsService {
	public List<SIMDetails> fetchSIMDetailsWithActiveStatus();
	public String getSimStatus(long simNumber, long serviceNumber) throws SIMDoesNotExistsException;
}
