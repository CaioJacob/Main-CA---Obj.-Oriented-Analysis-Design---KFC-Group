/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminsoftware;

/**
 *
 * @author User
 */
public class LecturerReportConstructor {
    private String lecturer;
    private String role;
    private String modulesTeaching;
    private String numberOfStudents;
    private String modulesCanTeach;
    
    // To be used to extract variables from database data
    
    public LecturerReportConstructor(String lecturer, String role, String modulesTeaching, String numberOfStudents, String modulesCanTeach) {
        this.lecturer = lecturer;
        this.role = role;
        this.modulesTeaching = modulesTeaching;
        this.numberOfStudents = numberOfStudents;
        this.modulesCanTeach = modulesCanTeach;
    }
    
    // Getters and setters
    
    public String getLecturer() {
        return lecturer;
    }

    public String getRole() {
        return role;
    }

    public String getModulesTeaching() {
        return modulesTeaching;
    }

    public String getNumberOfStudents() {
        return numberOfStudents;
    }

    public String getModulesCanTeach() {
        return modulesCanTeach;
    }
    
    

}
