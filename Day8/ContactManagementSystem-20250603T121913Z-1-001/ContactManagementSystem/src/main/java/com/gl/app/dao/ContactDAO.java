package com.gl.app.dao;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import com.gl.app.entity.Contact;

public interface ContactDAO {
	void addContact(Contact contact) throws IOException;
	List<Contact> getContacts() throws IOException;
	void sortContact(String criteria) throws IOException;
	Contact getContactByID(int id) throws IOException;
	void importContacts(Stream<String> contactsData);

}
