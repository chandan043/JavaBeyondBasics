package com.gl.app;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.gl.app.entity.Customer;
import com.gl.app.exception.CustomerTableEmptyException;
import com.gl.app.exception.SIMDoesNotExistsException;
import com.gl.app.service.*;
import com.gl.app.service.impl.*;


public class HitachiMobileApplication {
	
public static void main(String[] args) throws SQLException {
	final CustomerService customerService = new CustomerServiceImpl();
    final SIMDetailsService simDetailsService = new SIMDetailsServiceImpl();
	
	 Scanner scanner = new Scanner(System.in);
    while (true) {
        System.out.println("1. Fetch Sim Details by Customer ID");
        System.out.println("2. Update customer address");
        System.out.println("3. Get all customers");
        System.out.println("4. Fetch active SIM details");
        System.out.println("5. Get SIM status");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
            	System.out.println("Enter UniqueId/CustomerId :");
            	long customerId =scanner.nextLong();
            	customerService.fetchCustomerList(customerId).forEach(x -> System.out.println(x.toString()));
                break;
            case 2:
            	System.out.print("\nEnter UniqueId(CustomerId):");
            	long customerId1 =scanner.nextLong();
            	System.out.print("\nEnter New Address:");
            	String city = scanner.next();
            	customerService.updateCustomerAddress(customerId1, city);
                break;                 
            case 3:
                try {
                    List<Customer> allCustomers = customerService.getAllCustomers();
//                    System.out.println("All Customers:");
                    allCustomers.forEach(System.out::println);
                } catch (CustomerTableEmptyException e) {
                    System.out.println("No customers found in the system."+ e.getMessage());
                }
                break;
            case 4:
//            	System.out.println("SIM Details With Active Status");
            	simDetailsService.fetchSIMDetailsWithActiveStatus().forEach(x->System.out.println(x));
                break;
            case 5:
            	System.out.print("Enter SIM Number:");
            	final long simNumber = scanner.nextLong();
            	System.out.print("\nEnter ServiceNumber:");
            	final long serviceNumber= scanner.nextLong();
            	try {
            		System.out.println("SIM Status: "+ simDetailsService.getSimStatus(simNumber,serviceNumber));
            	}
            	catch(SIMDoesNotExistsException e) {
            		System.out.println("SIM Status: "+ e.getMessage() );
            	}
            	
                break;
           
            case 6:
                System.out.println("Exiting...");
                scanner.close();
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 6.");
        }
    }
}

}
