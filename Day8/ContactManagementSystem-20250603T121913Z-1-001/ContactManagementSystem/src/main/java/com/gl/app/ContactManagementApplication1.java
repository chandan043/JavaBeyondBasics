package com.gl.app;

import com.gl.app.entity.Contact;
import com.gl.app.service.ContactService;
import com.gl.app.service.impl.ContactServiceImpl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;


public class ContactManagementApplication1 {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("\\d{10}");
    
    public static void main(String[] args) throws IOException {
    	List<String> contactsData = Arrays.asList(
    	        "John,john.doe@example.com,6234567890",
    	        "Jane,jane.doe@example.com,6234567891",
    	        "Bob,bob.smith@example.com,6234567892"
    	    );
    	
    	
        ContactService contactService = new ContactServiceImpl();
        Scanner scanner = new Scanner(System.in);
        boolean input = true;

        while (input) {
            System.out.println("1. Add contact");
            System.out.println("2. Display contact List");
            System.out.println("3. Sort contact List");
            System.out.println("4. Get contact by ID");
            System.out.println("5. Import contacts");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over

            switch (choice) {
                case 1:
                	System.out.print("Enter Name: ");
                    String name = scanner.nextLine();

                    String email;
                    do {
                        System.out.print("Enter Email: ");
                        email = scanner.nextLine();
                        if (!validateEmail(email)) {
                            System.out.println("Invalid email format. Try again.");
                        }
                    } while (!validateEmail(email));

                    String phone;
                    do {
                        System.out.print("Enter Phone Number: ");
                        phone = scanner.nextLine();
                        if (!validatePhoneNumber(phone)) {
                            System.out.println("Invalid phone number. Try again.");
                        }
                    } while (!validatePhoneNumber(phone));

                    Contact newContact = new Contact(0, name, email, phone);
                    contactService.addContact(newContact);
                    System.out.println("Contact added successfully.");
                    break;
                    
				case 2:
					List<Contact> contacts = contactService.getContacts();
                    if (contacts.isEmpty()) {
                        System.out.println("No contacts found.");
                    } else {
                        contacts.forEach(System.out::println);
                    }
					break;
				case 3:
                    System.out.print("Enter criteria (name, email, phonenumber, address): ");
                    String criteria = scanner.nextLine();
                    contactService.sortContact(criteria);
                    break;
                case 4:
                    System.out.print("Enter Contact ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        Contact contact = contactService.getContactByID(id);
                        System.out.println(contact);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 5:
                    contactService.importContacts(contactsData.stream());
                    System.out.println("Contacts imported.");
                    break;
                case 6:
                    System.out.println("Exiting...");
                    input = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }
    private static boolean validateEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private static boolean validatePhoneNumber(String phoneNumber) {
        return PHONE_PATTERN.matcher(phoneNumber).matches();
    }
}