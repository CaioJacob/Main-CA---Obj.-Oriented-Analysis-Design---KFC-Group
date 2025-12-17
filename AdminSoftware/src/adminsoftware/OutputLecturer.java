/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminsoftware;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author User
 */
public class OutputLecturer {
    
    // Method to output headings in the console
    
    public static void consoleHeadings(ResultSetMetaData metaData) throws SQLException {
        System.out.format("%-30s%-30s%-30s%-30s%-30s\n", "Lecturer", "Role", "ModulesTeaching", "NumOfStudents", "ModulesCanTeach");
    }

    // Method to output rows in the console
    
    public static void consoleRowsLecturer(List<LecturerReportConstructor> lecturers) {
        for (LecturerReportConstructor lecturer : lecturers) {
                   System.out.format("%-30s%-30s%-30s%-30s%-30s\n",
                lecturer.getLecturer(),
                lecturer.getRole(),
                lecturer.getModulesTeaching(),
                lecturer.getNumberOfStudents(),
                lecturer.getModulesCanTeach());
        }
    }
    
    // Output method to call in Main - Print in the console
    
    public static void consoleOutput(String url, String user, String password) {
    
    List<LecturerReportConstructor> lecturers = LecturerReportVariables.fetchLecturerInfo();
        
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(LecturerReportVariables.sql);
         ResultSet rs = pstmt.executeQuery()) {

        ResultSetMetaData metaData = rs.getMetaData();
        consoleHeadings(metaData); // Print column headings
        consoleRowsLecturer(lecturers); // Print rows
    } catch (SQLException e) {
        System.err.println("Failed to connect to the database or execute the query.");
        e.printStackTrace();
    }
    }
    
    // Print headings in a file
    
    public static void printHeadingsToFile(ResultSetMetaData metaData, PrintWriter writer) throws SQLException {
        writer.format("%-30s%-30s%-30s%-30s%-30s\n", "Lecturer", "Role", "ModulesTeaching", "NumOfStudents", "ModulesCanTeach");
    }

    // Print rows in a file
    
    public static void printRowsToFile(List<LecturerReportConstructor> lecturers, PrintWriter writer) {
        for (LecturerReportConstructor lecturer : lecturers) {
        writer.format("%-30s%-30s%-30s%-30s%-30s\n",
                lecturer.getLecturer(),
                lecturer.getRole(),
                lecturer.getModulesTeaching(),
                lecturer.getNumberOfStudents(),
                lecturer.getModulesCanTeach());
        }
    }

    // Output method to call in Main - report in a .txt file
    
    public static void outputToFile(String url, String user, String password, String filePath) {

        List<LecturerReportConstructor> lecturers = LecturerReportVariables.fetchLecturerInfo();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(LecturerReportVariables.sql); 
             ResultSet rs = pstmt.executeQuery();
             PrintWriter writer = new PrintWriter(filePath)) {

            ResultSetMetaData metaData = rs.getMetaData();
            printHeadingsToFile(metaData, writer); // Column headings to file
            printRowsToFile(lecturers, writer); // Rows information to file
            
        } catch (SQLException e) {
            System.err.println("Error fetching lecturer report data: "  + e.getMessage());
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.err.println("File not found error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Headings to CSV
    
    public static void printHeadingsToCSV(ResultSetMetaData metaData, PrintWriter writer) throws SQLException {
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            writer.print("\"" + metaData.getColumnLabel(i) + "\"" + (i == columnCount ? "" : ","));
        }
        writer.println(); // Move to the next line to prevent errors
    }

    // Rows to CSV
    
    public static void printRowsToCSV(List<LecturerReportConstructor> lecturers, PrintWriter writer) {
        for (LecturerReportConstructor lecturer : lecturers) {
            writer.println("\"" + lecturer.getLecturer() + "\",\"" + lecturer.getRole() + "\",\"" + lecturer.getModulesTeaching() + "\",\"" + lecturer.getNumberOfStudents() + "\",\"" + lecturer.getModulesCanTeach() + "\"");
        }
    }

    // Output method to call in Main - report in CSV format
    
    public static void outputToCSV(String url, String user, String password, String csvPath) {
        List<LecturerReportConstructor> lecturers = LecturerReportVariables.fetchLecturerInfo();

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(LecturerReportVariables.sql);
            ResultSet rs = pstmt.executeQuery();
            PrintWriter writer = new PrintWriter(csvPath)) {

            ResultSetMetaData metaData = rs.getMetaData();
            printHeadingsToCSV(metaData, writer); // Column headings to CSV
            printRowsToCSV(lecturers, writer); // Rows information to CSV
            
        } catch (SQLException e) {
            System.err.println("Error fetching lecturer report data: "  + e.getMessage());
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.err.println("File not found error: " + e.getMessage());
            e.printStackTrace();
        }
}
    
}
    
