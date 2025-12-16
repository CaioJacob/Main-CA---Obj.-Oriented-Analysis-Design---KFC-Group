/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminsoftware;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
public class OutputStudent {
    
    // Method to output headings in the console
    
    public static void consoleHeadings(ResultSetMetaData metaData) throws SQLException {
        System.out.format("%-30s%-30s%-30s%-30s%-30s\n", "Student", "Programme", "EnrolledModules", "CompletedModulesAndGrades", "ToRepeatModules");
    }

    // Method to output rows in the console
    
    public static void consoleRowsStudent(List<StudentReportConstructor> students) {
        for (StudentReportConstructor student : students) {
            System.out.format("%-30s%-30s%-30s%-30s%-30s\n",
                student.getStudent(),
                student.getProgramme(),
                student.getEnrolledModules(),
                student.getCompletedModulesAndGrades(),
                student.getToRepeatModules());
        }
    }
    
    // Output method to call in Main - Print in the console
    
    public static void consoleStudent(String url, String user, String password) {
    
    List<StudentReportConstructor> students = StudentReportVariables.fetchStudentInfo(url, user, password);
        
    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(StudentReportVariables.sql);
        ResultSet rs = pstmt.executeQuery()) {

        ResultSetMetaData metaData = rs.getMetaData();
        consoleHeadings(metaData); // Print column headings
        consoleRowsStudent(students); // Print rows
    } catch (SQLException e) {
        System.err.println("Failed to connect to the database or execute the query.");
        e.printStackTrace();
    }
    }
    
    // Print headings in a file
    
    public static void printHeadingsToFile(ResultSetMetaData metaData, PrintWriter writer) throws SQLException {
        writer.format("%-30s%-30s%-30s%-30s%-30s\n", "Student", "Programme", "EnrolledModules", "CompletedModulesAndGrades", "ToRepeatModules");
}

    // Print rows in a file
    
    public static void printRowsToFile(List<StudentReportConstructor> students, PrintWriter writer) {
        for (StudentReportConstructor student : students) {
            writer.format("%-30s%-30s%-30s%-30s%-30s\n",
                student.getStudent(),
                student.getProgramme(),
                student.getEnrolledModules(),
                student.getCompletedModulesAndGrades(),
                student.getToRepeatModules());
        }
    }

    // Output method to call in Main - report in a .txt file
    
    public static void studentToFile(String url, String user, String password, String studentFilePath) {

        List<StudentReportConstructor> students = StudentReportVariables.fetchStudentInfo(url, user, password);

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(StudentReportVariables.sql); 
             ResultSet rs = pstmt.executeQuery();
             PrintWriter writer = new PrintWriter(studentFilePath)) {

            ResultSetMetaData metaData = rs.getMetaData();
            printHeadingsToFile(metaData, writer); // Column headings to file
            printRowsToFile(students, writer); // Rows information to file
            
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
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
        writer.println(); // Move to the next line to avoid errors
    }

    // Rows to CSV
    
    public static void printRowsToCSV(List<StudentReportConstructor> students, PrintWriter writer) {
        for (StudentReportConstructor student : students) {
            writer.println("\"" + student.getStudent() + "\",\"" + student.getProgramme() + "\",\"" + student.getEnrolledModules() + "\",\"" + student.getCompletedModulesAndGrades() + "\",\"" + student.getToRepeatModules() + "\"");
        }
    }

    // Output method to call in Main - report in CSV format
    
    public static void studentToCSV(String url, String user, String password, String csvPath) {
        List<StudentReportConstructor> students = StudentReportVariables.fetchStudentInfo(url, user, password);

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(StudentReportVariables.sql);
            ResultSet rs = pstmt.executeQuery();
            PrintWriter writer = new PrintWriter(csvPath)) {

            ResultSetMetaData metaData = rs.getMetaData();
            printHeadingsToCSV(metaData, writer); // Column headings to CSV
            printRowsToCSV(students, writer); // Rows information to CSV
            
        } catch (SQLException e) {
            System.err.println("Error fetching student report data: "  + e.getMessage());
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.err.println("File not found error: " + e.getMessage());
            e.printStackTrace();
        }
}
    
}
    
