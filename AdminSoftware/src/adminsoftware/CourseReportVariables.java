/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminsoftware;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class CourseReportVariables {
    
    // Declared as final so that it can only be assigned to the right query
    
    public static final String sql = "SELECT m.ModuleName, m.Programme, COUNT(DISTINCT sm.StudentNumber) AS NumberOfStudents, l.LecturerName, m.Classroom FROM module m LEFT JOIN studentmodule sm ON m.ModuleID = sm.ModuleID LEFT JOIN lecturermodule lm ON m.ModuleID = lm.ModuleID INNER JOIN lecturer l ON lm.LecturerNumber = l.LecturerNumber GROUP BY m.ModuleName, m.Programme, l.LecturerName, m.Classroom HAVING l.LecturerName IS NOT NULL;";

    public static List<CourseReportConstructor> fetchModuleInfo(String url, String user, String password) {
    
        List<CourseReportConstructor> modules = new ArrayList<>();
     
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                
                // Assign values to variables
                
                String moduleName = rs.getString("ModuleName");
                String programme = rs.getString("Programme");
                String numberOfStudents = rs.getString("NumberOfStudents");
                String lecturerName = rs.getString("LecturerName");
                String classroom = rs.getString("Classroom");

                modules.add(new CourseReportConstructor(moduleName, programme, numberOfStudents, lecturerName, classroom));
            }
            } catch (SQLException e) {
                System.err.println("Error fetching course data: " + e.getMessage());
            }  

        return modules;
        }

}
