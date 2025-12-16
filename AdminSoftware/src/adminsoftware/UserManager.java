/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminsoftware;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author User
 */

public class UserManager {
     
    // Arraylist storing users
    
    private List<User> users = new ArrayList<>();

    // Adding our admin to the list
    
    public UserManager() {
       users.add(new Admin("admin", "java", this));
    }
        
    private boolean isPasswordWeak(String password) { // Method to create a warning when weak passwords are selected
        boolean hasLetter = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isLetter(ch)) {
                hasLetter = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else {
                hasSpecial = true;
            }
        }

        return password.length() < 8 || !(hasLetter && hasDigit && hasSpecial);
    }
    
    // To use to add users

    public void addUser(String username, String password, String role) {
        
    if (isPasswordWeak(password)) {
        System.out.println("Warning: The chosen password is weak. If you want an increased security, consider using more than 8 character, a mix of letters, digits, and special characters.");
    }
        
        switch (role.toUpperCase()) {
            case "ADMIN":
                System.out.println("Admin creation is not allowed.");
                break;
            case "OFFICER":
                users.add(new Officer(username, password));
                break;
            case "LECTURER":
                users.add(new Lecturer(username, password));
                break;
            default:
                System.out.println("Invalid role.");
        }
    }

    // To be used to delete users (admin option)
    


    // To be used to modify username (admin option)
    
    public boolean modifyUsername(String oldUsername, String newUsername) {
        for (User user : users) {
            if (user.getUsername().equals(oldUsername)) {
                user.setUsername(newUsername);
                return true;
            }
        }
        return false;
    }

    // To be used to modify password (admin option)
    
    public boolean modifyPassword(String username, String newPassword) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                user.setPassword(newPassword);
                return true;
            }
        }
        return false;
    }

    public Optional<User> getUser(String username, String password) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username) && user.password.equals(password))
                .findFirst();
    }

    // To isolate instances in which menu options must hide (for example, user logins other than admin)
    
    public boolean hasNonAdminUsers() {
        return users.stream().anyMatch(user -> !(user instanceof Admin));
    }

    // Handling invalid option to display users when none was created
    
    public void displayUsers() {
        
    // Handling invalid option to display users when none was created
        
    if (users.isEmpty()) {
        System.out.println("No users found.");
        return;
    }
    
    // Show existing users
    
    System.out.println("Existing users:");
    for (User user : users) {
        System.out.println("Username: " + user.getUsername() + ", Role: " + user.getRole());
    }
}
    
    // Check if username exists
    
    public boolean doesUserExist(String username) {
        return users.stream().anyMatch(user -> user.getUsername().equals(username));
    }

    // Check if the username is modifiable (i.e., not "admin")
    
    public boolean isUserModifiable(String username) {
        return doesUserExist(username) && !username.equalsIgnoreCase("admin");
    }

    // Modify deleteUser to use doesUserExist for initial check
    
    public void deleteUser(String username) {
        if (!doesUserExist(username)) {
            System.out.println("User not found.");
            return;
        }
        
        users.removeIf(user -> user.getUsername().equals(username));
        System.out.println("User " + username + " deleted.");
    }  
    
    public boolean hasRole(String role) {
    return users.stream().anyMatch(user -> user.getRole().equalsIgnoreCase(role));
    }

}
    
