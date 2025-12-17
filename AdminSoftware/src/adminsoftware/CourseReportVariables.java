/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminsoftware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Query holder and fetcher for course/module reports.
 */
public class CourseReportVariables {

    public static final String sql =
        "SELECT m.ModuleName AS ModuleName, m.Programme AS Programme, " +
        "COUNT(DISTINCT sm.StudentNumber) AS NumberOfStudents, " +
        "l.LecturerName AS LecturerName, m.Classroom AS Classroom " +
        "FROM module m " +
        "LEFT JOIN studentmodule sm ON m.ModuleID = sm.ModuleID " +
        "LEFT JOIN lecturermodule lm ON m.ModuleID = lm.ModuleID " +
        "LEFT JOIN lecturer l ON lm.LecturerNumber = l.LecturerNumber " +
        "GROUP BY m.ModuleName, m.Programme, l.LecturerName, m.Classroom " +
        "HAVING l.LecturerName IS NOT NULL;";

    /**
     * Fetch module/course information using DatabaseConnection.
     * Note: signature no longer accepts DB credentials; DatabaseConnection reads env vars.
     */
    public static List<CourseReportConstructor> fetchModuleInfo() {

        List<CourseReportConstructor> modules = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                String moduleName = rs.getString("ModuleName");
                String programme = rs.getString("Programme");
                String numberOfStudents = rs.getString("NumberOfStudents");
                String lecturerName = rs.getString("LecturerName");
                String classroom = rs.getString("Classroom");

                modules.add(new CourseReportConstructor(moduleName, programme, numberOfStudents, lecturerName, classroom));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching course data: " + e.getMessage());
            e.printStackTrace();
        }

        return modules;
    }
}
