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
public class Admin extends User implements MenuOptions {
    
    // Create an Admin user constructor

    private UserManager userManager; 
    public Admin(String username, String password, UserManager userManager) {
        super(username, password, "ADMIN");
        this.userManager = userManager; 
    }

    // Use abstract class from User to display menus for every user (Admin in this case)
    
    @Override
    public void showOptions() {
        System.out.println("ADMIN MENU");
        System.out.println("1. Add user");
        System.out.println("2. Change username");
        System.out.println("3. Change password");
        System.out.println("4. Back to login (login as admin, officer or lecturer)");
        if (userManager.hasNonAdminUsers()) { // Show only if there are users other than non admin
            System.out.println("5. Modify user");
            System.out.println("6. Delete user");
            System.out.println("7. View existing users");
        }
    }
    
    // Coding logic for every option
    
    public void processAdminCommand(int choice, Scanner scanner, UserManager userManager, ConsoleMenu consoleMenu) {
        
    if (choice >= 5 && choice <= 7 && !userManager.hasNonAdminUsers()) {
        System.out.println("Invalid selection"); // Guide the user that is used to hitting some options so that they pay more attention
        return;
    }
        
    switch (choice) {
        case 1: // Add user
            System.out.println("Enter username:");
            String username = scanner.nextLine();
            System.out.println("Enter password:");
            String password = scanner.nextLine();
            System.out.println("Enter role (OFFICER/LECTURER):");
            String role = scanner.nextLine();
            userManager.addUser(username, password, role);
            break;
        case 2: // Change username
            System.out.println("Enter old username:");
            String oldUsername = scanner.nextLine();
            System.out.println("Enter new username:");
            String newUsername = scanner.nextLine();
            if (userManager.modifyUsername(oldUsername, newUsername)) {
                System.out.println("Username successfully changed.");
            } else {
                System.out.println("Failed to change username.");
            }
            break;
        case 3: // Change password
            System.out.println("Enter username:");
            String usr = scanner.nextLine();
            System.out.println("Enter new password:");
            String newPassword = scanner.nextLine();
            if (userManager.modifyPassword(usr, newPassword)) {
                System.out.println("Password successfully changed.");
            } else {
                System.out.println("Failed to change password.");
            }
            break;
        case 4: // Logout and back to login menu
            consoleMenu.setShowLoginMenu(true);
            System.out.println("Logged out successfully.");
            break;
        case 5: // Modify user
            modifyUser(scanner, userManager);
            break;
        case 6: // Delete user
            deleteUser(scanner, userManager);
            break;
        case 7: // View existing users
            userManager.displayUsers();
            break;
        default:
            System.out.println("Invalid selection");
    }
}
    
    // Display menu unless the user logs out (goes back to login menu)
    
    public void handleAdminActions(Scanner scanner, UserManager userManager, ConsoleMenu consoleMenu) {
    int choice;
    do {
        showOptions(); // Show admin menu options
        choice = scanner.nextInt();
        scanner.nextLine(); // Consume a new line
        processAdminCommand(choice, scanner, userManager, consoleMenu);
    } while (choice != 4);
}
    
    // Logic to modify users based on username
    
 private void modifyUser(Scanner scanner, UserManager userManager) {
        System.out.println("Enter the username of the user you want to modify:");
        String username = scanner.nextLine();

        if (!userManager.isUserModifiable(username)) {
            System.out.println("The user cannot be modified.");
            return;
        }

        System.out.println("Enter the new username (press Enter to skip):");
        String newUsername = scanner.nextLine();

        System.out.println("Enter the new password (press Enter to skip):");
        String newPassword = scanner.nextLine();

        if (!newUsername.isEmpty() && userManager.modifyUsername(username, newUsername)) {
            System.out.println("Username updated successfully.");
        } else if (newUsername.isEmpty()) {
            System.out.println("Username update skipped.");
        } else {
            System.out.println("Failed to update username.");
        }

        if (!newPassword.isEmpty() && userManager.modifyPassword(newUsername.isEmpty() ? username : newUsername, newPassword)) {
            System.out.println("Password updated successfully.");
        } else if (newPassword.isEmpty()) {
            System.out.println("Password update skipped.");
        } else {
            System.out.println("Failed to update password.");
        }
    }
    
    // Logic to delete user based on username
    
    private void deleteUser(Scanner scanner, UserManager userManager) {
        System.out.println("Enter the username of the user you want to delete:");
        String username = scanner.nextLine();

        if (!userManager.isUserModifiable(username)) {
            System.out.println("The user cannot be deleted.");
            return;
        }

        userManager.deleteUser(username);
    }     
}
