package com.gl.app.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import com.gl.app.entity.Contact;

public interface ContactService {
	void addContact(Contact contact)throws IOException;
	List<Contact> getContacts()throws IOException;
	void sortContact(String criteria) throws IOException;
	//Use Java8  Streams API for sorting 
	//Sorts the contacts based on criteria 
	Contact getContactByID(int id)throws IOException;
	void importContacts(Stream<String> contactsData)throws IOException;

}
