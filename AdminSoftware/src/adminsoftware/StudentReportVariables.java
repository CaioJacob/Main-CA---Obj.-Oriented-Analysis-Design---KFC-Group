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
public class StudentReportVariables {
    
    // Declared as final so that it can only be assigned to the right query
    
    public static final String sql =
    "SELECT CONCAT(s.StudentName, ' ', s.StudentNumber) AS Student, " +
    "s.Programme AS Programme, " +
    "GROUP_CONCAT(DISTINCT m.ModuleName SEPARATOR '; ') AS EnrolledModules, " +
    "GROUP_CONCAT(CONCAT(m.ModuleName, ' ', sm.Grades) SEPARATOR '; ') AS CompletedModulesAndGrades, " +
    "CASE WHEN SUM(CASE WHEN sm.Grades < 40 THEN 1 ELSE 0 END) > 0 THEN 'true' ELSE 'false' END AS ToRepeat " +
    "FROM student s " +
    "JOIN studentmodule sm ON s.StudentNumber = sm.StudentNumber " +
    "JOIN module m ON sm.ModuleID = m.ModuleID AND s.Programme = m.Programme " +
    "WHERE sm.Enrolled = 1 " +
    "GROUP BY s.StudentNumber, s.Programme;";
    
    public static List<StudentReportConstructor> fetchStudentInfo(String url, String user, String password) {
    
        List<StudentReportConstructor> students = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                
                // Assign values to variable
                
                String student = rs.getString("student");
                String programme = rs.getString("Programme");
                String enrolledModules = rs.getString("Enrolled Modules");
                String completedModulesAndGrades = rs.getString("Grades");
                String toRepeatModule = rs.getString("ToRepeat");

                students.add(new StudentReportConstructor(student, programme, enrolledModules, completedModulesAndGrades, toRepeatModule));
            }
            } catch (SQLException e) {
                System.err.println("Error fetching student data: " + e.getMessage());
            }  

        return students;
        }

}
