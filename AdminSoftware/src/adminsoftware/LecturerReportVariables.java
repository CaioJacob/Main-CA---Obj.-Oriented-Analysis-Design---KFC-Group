/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminsoftware;

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
public class LecturerReportVariables {
    
    // Declared as final so that it can only be assigned to the right query
    public static final String sql =
  "SELECT l.LecturerName AS LecturerName, l.LecturerRole AS LecturerRole, " +
  "GROUP_CONCAT(DISTINCT CASE WHEN lm.IsTeaching = 1 THEN m.ModuleName END SEPARATOR '; ') AS ModulesTeaching, " +
  "COUNT(DISTINCT CASE WHEN sm.Enrolled = 1 THEN sm.StudentNumber END) AS NumberOfStudents, " +
  "GROUP_CONCAT(DISTINCT CASE WHEN lm.CanTeach = 1 THEN m.ModuleName END SEPARATOR '; ') AS CanTeach " +
  "FROM lecturer l " +
  "LEFT JOIN lecturermodule lm ON l.LecturerNumber = lm.LecturerNumber " +
  "LEFT JOIN module m ON lm.ModuleID = m.ModuleID " +
  "LEFT JOIN studentmodule sm ON m.ModuleID = sm.ModuleID " +
  "GROUP BY l.LecturerName, l.LecturerRole;";
        
    public static List<LecturerReportConstructor> fetchLecturerInfo(String url, String user, String password) {
    
        List<LecturerReportConstructor> lecturers = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                
                // Assign values to variables
                
                String lecturer = rs.getString("LecturerName");
                String role = rs.getString("LecturerRole");
                String modulesTeaching = rs.getString("ModulesTeaching");
                String numberOfStudents = rs.getString("NumberOfStudents");
                String modulesCanTeach = rs.getString("CanTeach");

                lecturers.add(new LecturerReportConstructor(lecturer, role, modulesTeaching, numberOfStudents, modulesCanTeach));
            }
            } catch (SQLException e) {
                System.err.println("Error fetching lecturer data: " + e.getMessage());
            }  

        return lecturers; // Isolate our lecturers
        }

}
