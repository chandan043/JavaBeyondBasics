package com.gl.app.entity;

public record Contact (
	int contactId,
	String contactName,
	String contactEmail,
	String contactNumber
//	String contactAddress
) {};