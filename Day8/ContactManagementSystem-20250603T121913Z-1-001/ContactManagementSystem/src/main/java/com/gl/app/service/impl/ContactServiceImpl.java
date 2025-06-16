package com.gl.app.service.impl;

import com.gl.app.dao.ContactDAO;
import com.gl.app.dao.impl.ContactDAOImpl;
import com.gl.app.entity.Contact;
import com.gl.app.exception.ContactNotFoundException;
import com.gl.app.service.ContactService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ContactServiceImpl implements ContactService {

	public ContactDAO dao;

    public ContactServiceImpl() {
        try {
            this.dao = new ContactDAOImpl();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize ContactServiceImpl", e);
        }
    }
    
    @Override
    public void addContact(Contact contact) throws IOException {
        dao.addContact(contact);
    }

    @Override
    public List<Contact> getContacts() throws IOException{
        try {
            List<Contact> contacts = dao.getContacts();
            return (contacts != null) ? contacts : new ArrayList<>();
        } catch (ContactNotFoundException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }
        

    @Override
    public void sortContact(String criteria) throws IOException {
        dao.sortContact(criteria);
    }

    @Override
    public Contact getContactByID(int id) throws IOException {
    	Contact contact =null;
    	try{
        	contact= dao.getContactByID(id);
        }catch (ContactNotFoundException e) {
			System.out.println(e.getMessage());
		}
    	return contact;
    }

    @Override
    public void importContacts(Stream<String> contactsData) {
        dao.importContacts(contactsData);
    }
}
