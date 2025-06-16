package com.gl.app.dao.impl;

import java.io.IOException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import java.util.ArrayList;


import com.gl.app.dao.ContactDAO;
import com.gl.app.entity.Contact;
import com.gl.app.exception.ContactNotFoundException;
import com.gl.app.util.ContactUtil;

public class ContactDAOImpl implements ContactDAO{
	private Connection connection =null;
	public ContactDAOImpl() throws SQLException, IOException {
	    connection = ContactUtil.getConnection(); // This throws SQLException
	}

	
	public void addContact(Contact contact) throws IOException {
		String insertSQL = "INSERT INTO Contacts (name, email, phoneNumber) VALUES (?, ?, ?)";
		
        try (Connection connection = ContactUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {

            pstmt.setString(1, contact.contactName());
            pstmt.setString(2, contact.contactEmail());
            pstmt.setString(3, contact.contactNumber());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error inserting contact: " + e.getMessage());
        }
    }

    public List<Contact> getContacts() throws IOException {
    	List<Contact> contactList = new ArrayList<>();
        String query = "SELECT * FROM Contacts";

        try (Connection connection = ContactUtil.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
//        	if(rs.isBeforeFirst())
//        		throw new ContactNotFoundException("Contact_NotFound");
        	while (rs.next()) {
        	    Contact contact = new Contact(
        	        rs.getInt("contact_id"),
        	        rs.getString("name"),
        	        rs.getString("email"),
        	        rs.getString("phoneNumber")
//        	        rs.getString("contactAddress")
        	    );
                contactList.add(contact);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching contacts: " + e.getMessage());
        }

        return contactList;
    }
    
    @Override
    public void sortContact(String criteria) throws IOException {
        List<Contact> contacts = getContacts();

        Comparator<Contact> comparator = switch (criteria.toLowerCase()) {
            case "name" -> Comparator.comparing(Contact::contactName);
            case "email" -> Comparator.comparing(Contact::contactEmail);
            case "phonenumber" -> Comparator.comparing(Contact::contactNumber);
//            case "address" -> Comparator.comparing(Contact::contactAddress);
            default -> null;
        };

        if (comparator != null) {
            List<Contact> sorted = contacts.stream()
                                          .sorted(comparator)
                                          .toList();

            sorted.forEach(System.out::println);
        } else {
            System.out.println("Invalid sort criteria: " + criteria);
        }
    }

    
    
    @Override
    public Contact getContactByID(int id) throws IOException {
        List<Contact> contacts = getContacts();

        return contacts.stream()
                       .filter(c -> c.contactId() == id)
                       .findFirst()
                       .orElseThrow(() -> new ContactNotFoundException("Contact not found with ID " + id));
    }

	

    @Override
    public void importContacts(Stream<String> contactData) {
        contactData.forEach(line -> {
            String[] fields = line.split(",");

            if (fields.length >= 2) {
                Contact contact = null;
				try {
					contact = new Contact(
						ContactUtil.generateUniqueId("contact_id","contacts",0),  // contactId
					    fields[0].trim(),  // contactName
					    fields[1].trim(),  // contactEmail
					    fields[2].trim()  // contactNumber
					);
				} catch (IOException e) {
					e.printStackTrace();
				}
                try {
					addContact(contact);
				} catch (IOException e) {
					e.printStackTrace();
				}
            } else {
                System.out.println("Skipping invalid line: " + line);
            }
        });
    }

}

	
	


