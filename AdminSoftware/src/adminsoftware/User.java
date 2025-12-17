/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminsoftware;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Base user model used by Admin / Officer / Lecturer.
 * Centralizes password hashing and exposes simple getters/setters required by callers.
 */
public abstract class User {
    protected String username;
    protected String passwordHash;
    protected String role;

    public User(String username, String plainPassword, String role) {
        this.username = username;
        this.role = role;
        if (plainPassword != null) {
            setPassword(plainPassword);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    /**
     * Hashes and stores the password using BCrypt.
     */
    public void setPassword(String plainPassword) {
        if (plainPassword == null) {
            this.passwordHash = null;
            return;
        }
        this.passwordHash = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    /**
     * Verifies a plaintext password against the stored hash.
     */
    public boolean checkPassword(String plainPassword) {
        if (passwordHash == null || plainPassword == null) return false;
        return BCrypt.checkpw(plainPassword, passwordHash);
    }

    /**
     * Return stored hash for persistence layers (if needed).
     */
    public String getPasswordHash() {
        return passwordHash;
    }
}
