/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminsoftware;

/**
 *
 * @author User
 */
public class CourseReportConstructor {
    private String moduleName;
    private String programme;
    private String numberOfStudents;
    private String lecturerName;
    private String classroom;
    
    
   // To be used to extract the variables
    
    public CourseReportConstructor(String moduleName, String programme, String numberOfStudents, String lecturerName, String classroom) {
        this.moduleName = moduleName;
        this.programme = programme;
        this.numberOfStudents = numberOfStudents;
        this.lecturerName = lecturerName;
        this.classroom = classroom;
    }

    
    // Getters and setters
    
    public String getModuleName() {
        return moduleName;
    }

    public String getProgramme() {
        return programme;
    }

    public String getNumberOfStudents() {
        return numberOfStudents;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public String getClassroom() {
        return classroom;
    }
    
    
    
}
