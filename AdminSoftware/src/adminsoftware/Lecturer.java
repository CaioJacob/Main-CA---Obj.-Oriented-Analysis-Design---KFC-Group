/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminsoftware;

import java.util.Scanner;

/**
 *
 * @author User
 */
public class Lecturer extends User implements MenuOptions {
    
    // Create an instance of lecturer
    
    public Lecturer(String username, String password) {
        super(username, password, "LECTURER");
    }

    // Use showOptions method here, but with our own lecturer menu
    
    @Override
    public void showOptions() {
        System.out.println("LECTURER MENU");
        System.out.println("1. Generate Lecturer Report");
        System.out.println("2. Change username");
        System.out.println("3. Change password");
        System.out.println("4. Logout");
    }

    // Code selections
    
    public void handleLecturerActions(Scanner scanner, UserManager userManager, ConsoleMenu consoleMenu) {
        int choice;
        do {
            showOptions();
            System.out.println("Please choose an option:");
            choice = scanner.nextInt();
            scanner.nextLine(); // Move to next line to prevent errors
            switch (choice) {
                case 1:
                    generateLecturerReport(scanner); // Lecturer report
                    break;
                case 2:
                    changeUsername(scanner, userManager); // Change username
                    break;
                case 3:
                    changePassword(scanner, userManager); // Change password
                    break;
                case 4:
                    consoleMenu.setShowLoginMenu(true); // Method from ConsoleMenu
                    System.out.println("Logged out");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        } while (choice != 4); // Show menu while not logged out
    }

// Method to generate report
    
private void generateLecturerReport(Scanner scanner) {
    System.out.println("Select the report format:");
    System.out.println("1. TXT format");
    System.out.println("2. CSV format");
    System.out.println("3. Console output");
    int formatChoice = scanner.nextInt();
    scanner.nextLine();  // Move to next line to prevent errors


    // Database connection details
    
    String url = "jdbc:mysql://localhost:3306/adminsoftware_2";
    String user = "root";
    String password = "root24";
    String filePath = "C:\\Users\\User\\Documents\\NetBeansProjects\\adminsoftware\\LecturerReport.txt";
    String csvPath = "C:\\Users\\User\\Documents\\NetBeansProjects\\adminsoftware\\LecturerReport.csv";
    
    switch (formatChoice) {
        case 1:
            // Generate report in TXT format
            OutputLecturer.outputToFile(url, user, password, filePath);
            System.out.println("Report generated in TXT format at " + filePath);
            break;
        case 2:
            // Generate report in CSV format
            OutputLecturer.outputToCSV(url, user, password, csvPath);
            System.out.println("Report generated in CSV format at " + csvPath);
            break;
        case 3:
            // Generate report in the console
            OutputLecturer.consoleOutput(url, user, password);
            break;
        default:
            System.out.println("Invalid option. Please select a valid format.");
            break;
    }
}

    // Method to change username

    private void changeUsername(Scanner scanner, UserManager userManager) {
        System.out.println("Enter your new username:");
        String newUsername = scanner.nextLine();
        
        boolean success = userManager.modifyUsername(getUsername(), newUsername);
        if (success) {
            System.out.println("Username successfully changed to: " + newUsername);
            setUsername(newUsername); // Update the username in the current User object
        } else {
            System.out.println("Failed to change username.");
        }
    }

    // Method to change password
    
    private void changePassword(Scanner scanner, UserManager userManager) {
        System.out.println("Enter your new password:");
        String newPassword = scanner.nextLine();
        
        boolean success = userManager.modifyPassword(getUsername(), newPassword);
        if (success) {
            System.out.println("Password successfully changed.");
            setPassword(newPassword); // Update the password in the current User object
        } else {
            System.out.println("Failed to change password.");
        }
    }
}