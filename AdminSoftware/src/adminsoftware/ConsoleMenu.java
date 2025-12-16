/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminsoftware;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class ConsoleMenu {
    
    private UserManager userManager;
    private Scanner scanner;
    private boolean exitApplication = false; // To be used to exit loops
    private boolean showLoginMenu = true; // For the interaction of user menu with login menu

    // The arraylist storing users is in "UserManager", so we are calling it here
    
    public ConsoleMenu(UserManager userManager) {
        this.userManager = userManager;
        this.scanner = new Scanner(System.in);
    }
    
    // To use to show user menu under certain conditions (used in lecturer class)
    
    public void setShowLoginMenu(boolean showLoginMenu) {
        this.showLoginMenu = showLoginMenu;
    }
    
    // Use to call an exit from the menu (exit loop)
    
    public void exitApplication() {
    this.exitApplication = true;
    scanner.close(); // Close scanner upon exit from application
    }

    // Prior to login - shows the options of login as different users
    
    public void showLoginMenu() {
    while (!exitApplication) {
        System.out.println("LOGIN MENU");
        
        // Always show login as Admin
        
        System.out.println("1. Login as Admin");
        
        int optionNumber = 2; // Start with 2 because 1 is for Admin
        
        // Dynamically add login options based on roles
        
        if (userManager.hasRole("OFFICER")) {
            System.out.println(optionNumber++ + ". Login as Officer");
        }
        
        // Dynamically add login options based on roles
        
        if (userManager.hasRole("LECTURER")) {
            System.out.println(optionNumber++ + ". Login as Lecturer");
        }
           
        // Last option is always to exit, dynamically adjust its position
        
        System.out.println(optionNumber + ". Exit Application");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Prevent errors

            // Handle user choice
            
            if (choice == 1) {
                performLogin("ADMIN");
            } else if (choice == optionNumber) {
            exitApplication();
            } else if (choice < optionNumber) {
                if (choice == 2 && userManager.hasRole("OFFICER")) {
                performLogin("OFFICER");
                } else if (choice == 3 && userManager.hasRole("LECTURER")) {
                performLogin("LECTURER");  
                } else if (choice == 2 && userManager.hasRole("LECTURER")) {
                performLogin("LECTURER");  
                }
                } else {
                System.out.println("Invalid choice. Please select a valid option.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Prevent errors
        }
    }
}


    // To use to display the login menu while option "exit application" is not set as true
    
    public void displayMenu() {
        while (!exitApplication) {
            if (showLoginMenu) {
                showLoginMenu();
            }
        }
    }
    
    // Login menu
    
    private void performLogin(String expectedRole) {
    System.out.println("Enter username:");
    String username = scanner.nextLine();
    System.out.println("Enter password:");
    String password = scanner.nextLine();

    Optional<User> userOptional = userManager.getUser(username, password);

    if (userOptional.isPresent()) {
        User user = userOptional.get();
        String userRole = user.getRole().toUpperCase();

        if (userRole.equals(expectedRole)) {
            System.out.println("Login successful.");
            // Handle role-specific actions here
            if (user instanceof Admin) {
                ((Admin) user).handleAdminActions(scanner, userManager, this);
            } else if (user instanceof Officer) {
                ((Officer) user).handleOfficerActions(scanner, userManager, this);
            } else if (user instanceof Lecturer) {
                ((Lecturer) user).handleLecturerActions(scanner, userManager, this);
            }
            setShowLoginMenu(true);
        } else {
            System.out.println("Login failed. Role does not match.");
        }
    } else {
        System.out.println("User not found or password incorrect.");
    }
}
}