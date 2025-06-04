package com.gl.app.entity;


public record Customer(
	long customer_id,
	String dateOfBirth,
	String emailAddress,
	String firstName,
	String lastName,
	String idType,
	String address,
	String state 
	){}