/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminsoftware;

import org.mindrot.jbcrypt.BCrypt;
import java.util.*;

public class UserManager {
    private List<User> users = new ArrayList<>();

    public UserManager() {
       String adminHash = BCrypt.hashpw("java", BCrypt.gensalt());
       users.add(new Admin("admin", adminHash, this));
    }

    public boolean addUser(String username, String password, String role) {
        if (doesUserExist(username)) return false;
        String hash = BCrypt.hashpw(password, BCrypt.gensalt());
        switch (role.toUpperCase()) {
            case "OFFICER": users.add(new Officer(username, hash)); return true;
            case "LECTURER": users.add(new Lecturer(username, hash)); return true;
            default: return false;
        }
    }

    public boolean modifyPassword(String username, String newPassword) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                user.setPasswordHash(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
                return true;
            }
        }
        return false;
    }

    public Optional<User> getUser(String username, String password) {
        return users.stream()
            .filter(u -> u.getUsername().equals(username) && BCrypt.checkpw(password, u.getPasswordHash()))
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

