package com.gl.app.client;

import com.gl.app.service.UserService;
import com.gl.app.service.impl.UserServiceImpl;
import com.gl.app.util.BaggageUtil;
import com.gl.app.service.BaggageService;
import com.gl.app.service.impl.BaggageServiceImpl;
import com.gl.app.entity.Baggage;
import com.gl.app.entity.User;
import com.gl.app.exception.BaggageNotFoundException;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaggageTrackingApplication {
    private static final String EMAIL_PATTERN = "^\\S+@\\S+\\.\\S+$";

    @SuppressWarnings("resource")
	public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        BaggageService baggageService = new BaggageServiceImpl();
        BaggageUtil baggageUtil = new BaggageUtil();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Baggage Tracking System ---");
            System.out.println("1. Register User");
            System.out.println("2. Check-in Baggage");
            System.out.println("3. Get Baggage Info");
            System.out.println("4. Get Baggage Status");
            System.out.println("5. Get All Checked-in Baggage");
            System.out.println("6. Update Baggage Status");
            System.out.println("7. Update Baggage Location");
            System.out.println("8. Claim Baggage");
            System.out.println("9. Get Baggage Location");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter first name: ");
                    String firstName = scanner.nextLine();

                    System.out.print("Enter last name: ");
                    String lastName = scanner.nextLine();

                    System.out.print("Enter email ID: ");
                    String emailId = scanner.nextLine();
                    while (!validateEmail(emailId)) {
                        System.out.print("Invalid email ID. Please enter a valid email ID: ");
                        emailId = scanner.nextLine();
                    }

                    String userId = baggageUtil.generateUniqueId("userid", "Users", "U", 1);
                    userService.registerNewUser(new User(userId, firstName, lastName, emailId));
                    System.out.println("User created successfully with ID: " + userId);
                    break;

                case 2:
                    System.out.print("Enter User ID: ");
                    String userId1 = scanner.nextLine();

                    System.out.print("Enter baggage location: ");
                    String location1 = scanner.nextLine();

                    String claimId = baggageUtil.generateUniqueId("claimId", "Baggage", "C", 1);
                    String status1 = "CheckedIn"; // Ensure this matches what's expected in DB

                    Baggage newBaggage = new Baggage(claimId, location1, status1, userId1);

                    try {
                        userService.checkInBaggage(newBaggage);
                        System.out.println("Baggage checked in successfully. User ID: " + userId1);
                    } catch (Exception e) {
                        System.out.println("Error checking in baggage: " + e.getMessage());
                    }
                    break;


                case 3:
                    System.out.print("Enter claim Tag ID: ");
                    String claimTagId = scanner.nextLine();
                    try {
                        Baggage baggage = userService.getBaggageInfo(claimTagId);
                        if (baggage != null) {
                            System.out.println("Claim ID: " + baggage.claimId());
                            System.out.println("Location: " + baggage.location());
                            System.out.println("Status: " + baggage.status());
                            System.out.println("User ID: " + baggage.userId());
                        } else {
                            System.out.println("Baggage not found.");
                        }
                    } catch (BaggageNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Enter Claim Tag Id: ");
                    String claimId1 = scanner.nextLine();
                    try{
                    	String status = baggageService.getBaggageStatus(claimId1);
                    	System.out.println("Baggage Status: " + status);
                    }catch (BaggageNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    
                    break;

                case 5:
                    try {
                        List<Baggage> baggages = baggageService.getAllCheckedInBaggage();
                        if (baggages.isEmpty()) {
                            System.out.println("There are no checked-in bags currently.");
                        } else {
                            for (Baggage bag : baggages) {
                                System.out.println("\nClaim ID: " + bag.claimId());
                                System.out.println("Location: " + bag.location());
                                System.out.println("Status: " + bag.status());
                                System.out.println("User ID: " + bag.userId());
                            }
                        }
                    } catch (BaggageNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 6:
                    System.out.print("Enter claim tag ID: ");
                    String claimIdForStatus = scanner.nextLine();
                    System.out.print("Enter new status: ");
                    String newStatus = scanner.nextLine();
                    try {
                        baggageService.updateBaggageStatus(claimIdForStatus, newStatus);
                        System.out.println("Baggage status updated successfully.");
                    } catch (BaggageNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 7:
                    System.out.print("Enter claim tag ID: ");
                    String claimIdForLocation = scanner.nextLine();
                    System.out.print("Enter new location: ");
                    String newLocation = scanner.nextLine();
                    try {
                        baggageService.updateBaggageLocation(claimIdForLocation, newLocation);
                        System.out.println("Baggage location updated successfully.");
                    } catch (BaggageNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 8:
                    System.out.print("Enter claim tag ID: ");
                    String claimIdToClaim = scanner.nextLine();
                    try {
                        baggageService.claimBaggage(claimIdToClaim);
                        System.out.println("Baggage claimed successfully.");
                    } catch (BaggageNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 9:
                    System.out.print("Enter claim tag ID: ");
                    String claimIdToLocate = scanner.nextLine();
                    try {
                        String location = baggageService.getBaggageLocation(claimIdToLocate);
                        System.out.println("Baggage location: " + location);
                    } catch (BaggageNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 10:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 10.");
            }
        }
    }

    private static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
