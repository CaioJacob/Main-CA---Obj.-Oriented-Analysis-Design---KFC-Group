/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminsoftware;

/**
 *
 * @author User
 */
public class StudentReportConstructor {
    private String student;
    private String programme;
    private String enrolledModules;
    private String completedModulesAndGrades;
    private String toRepeatModule;
    
    // To be used to extract variables from database data
    
    public StudentReportConstructor(String student, String programme, String enrolledModules, String completedModulesAndGrades, String toRepeatModule) {
        this.student = student;
        this.programme = programme;
        this.enrolledModules = enrolledModules;
        this.completedModulesAndGrades = completedModulesAndGrades;
        this.toRepeatModule = toRepeatModule;
    }

    // Setters and getters
    
    public String getStudent() {
        return student;
    }

    public String getProgramme() {
        return programme;
    }

    public String getEnrolledModules() {
        return enrolledModules;
    }

    public String getCompletedModulesAndGrades() {
        return completedModulesAndGrades;
    }

    public String getToRepeatModules() {
        return toRepeatModule;
    }
    
    
    
}
