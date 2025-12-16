/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminsoftware;

public abstract class User {
    protected String username;
    protected String passwordHash; // store hash, not plain text
    protected String role;

    public User(String username, String passwordHash, String role) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public void setUsername(String newUsername) { this.username = newUsername; }
    public void setPasswordHash(String newHash) { this.passwordHash = newHash; }
    public String getUsername() { return username; }
    public String getRole() { return role; }
    public String getPasswordHash() { return passwordHash; }
}
