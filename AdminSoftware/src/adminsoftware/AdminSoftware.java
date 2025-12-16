/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package adminsoftware;

import static adminsoftware.CourseReportVariables.fetchModuleInfo;
import static adminsoftware.LecturerReportVariables.fetchLecturerInfo;
import static adminsoftware.OutputLecturer.outputToFile;
import static adminsoftware.OutputLecturer.consoleOutput;
import static adminsoftware.OutputLecturer.outputToCSV;
import static adminsoftware.OutputStudent.consoleStudent;
import static adminsoftware.OutputStudent.studentToCSV;
import static adminsoftware.OutputStudent.studentToFile;
import static adminsoftware.StudentReportVariables.fetchStudentInfo;
import static adminsoftware.OutputCourse.courseToCSV;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author User
 */
public class AdminSoftware {

    /**
     * @param args the command line arguments
     * 
     * 
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
    // Loading UserManager and ConsoleMenu
    
    UserManager userManager = new UserManager();
    ConsoleMenu consoleMenu = new ConsoleMenu(userManager);
        
    // Calling the consoleMenu to run the program
    
    consoleMenu.displayMenu();

    } 
    
}
