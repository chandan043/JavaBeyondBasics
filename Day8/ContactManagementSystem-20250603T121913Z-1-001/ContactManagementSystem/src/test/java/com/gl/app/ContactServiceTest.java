package com.gl.app;

import com.gl.app.dao.ContactDAO;
import com.gl.app.entity.Contact;
import com.gl.app.service.impl.ContactServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContactServiceTest {

    private ContactDAO contactDAOMock;
    private ContactServiceImpl contactService;  // Use impl so you can access dao field

    private Contact aliceContact;

    @BeforeEach
    void setUp() {
        contactDAOMock = mock(ContactDAO.class);
        contactService = new ContactServiceImpl();
        contactService.dao = contactDAOMock;
        aliceContact = new Contact(1, "Alice", "alice@example.com", "9876543210");
    }

    @Test
    void testAddContact() throws Exception {
        List<Contact> contactsBefore = new ArrayList<>();
        List<Contact> contactsAfter = new ArrayList<>();
        contactsAfter.add(aliceContact);

        when(contactDAOMock.getContacts()).thenReturn(contactsBefore).thenReturn(contactsAfter);
        doAnswer(invocation -> null).when(contactDAOMock).addContact(aliceContact);

        var before = contactService.getContacts();
        assertEquals(0, before.size());

        contactService.addContact(aliceContact);

        var after = contactService.getContacts();
        assertEquals(1, after.size());

        Contact added = after.get(0);
        assertEquals("Alice", added.contactName());
        assertEquals("alice@example.com", added.contactEmail());
        assertEquals("9876543210", added.contactNumber());

        verify(contactDAOMock, times(2)).getContacts();
        verify(contactDAOMock).addContact(aliceContact);
    }
}