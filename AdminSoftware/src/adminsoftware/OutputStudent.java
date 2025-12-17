/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminsoftware;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class OutputStudent {

    /**
     * Print student report to console using StudentReportVariables.sql
     */
    public static void consoleStudent() {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(StudentReportVariables.sql);
             ResultSet rs = ps.executeQuery()) {

            ResultSetMetaData md = rs.getMetaData();
            int cols = md.getColumnCount();

            // header
            for (int i = 1; i <= cols; i++) {
                System.out.print(md.getColumnLabel(i));
                if (i < cols) System.out.print(" | ");
            }
            System.out.println();
            System.out.println("-".repeat(80));

            // rows
            while (rs.next()) {
                for (int i = 1; i <= cols; i++) {
                    String value = rs.getString(i);
                    System.out.print(value == null ? "" : value);
                    if (i < cols) System.out.print(" | ");
                }
                System.out.println();
            }

        } catch (SQLException e) {
            System.err.println("DB error while generating student console report: " + e.getMessage());
        }
    }

    /**
     * Write student report to a TXT file (tab-separated)
     */
    public static void studentToFile(String studentFilePath) {
        try {
            Path parent = Paths.get(studentFilePath).getParent();
            if (parent != null) Files.createDirectories(parent);
        } catch (Exception e) {
            System.err.println("Unable to create output directory: " + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(studentFilePath);
             Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(StudentReportVariables.sql);
             ResultSet rs = ps.executeQuery()) {

            ResultSetMetaData md = rs.getMetaData();
            int cols = md.getColumnCount();

            // header
            for (int i = 1; i <= cols; i++) {
                writer.print(md.getColumnLabel(i));
                if (i < cols) writer.print("\t");
            }
            writer.println();

            // rows
            while (rs.next()) {
                for (int i = 1; i <= cols; i++) {
                    String value = rs.getString(i);
                    writer.print(value == null ? "" : value);
                    if (i < cols) writer.print("\t");
                }
                writer.println();
            }

        } catch (FileNotFoundException e) {
            System.err.println("Unable to open file for writing: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("DB error while writing student report file: " + e.getMessage());
        }
    }

    /**
     * Write student report to CSV (comma-separated, quoted)
     */
    public static void studentToCSV(String csvPath) {
        try {
            Path parent = Paths.get(csvPath).getParent();
            if (parent != null) Files.createDirectories(parent);
        } catch (Exception e) {
            System.err.println("Unable to create output directory: " + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(csvPath);
             Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(StudentReportVariables.sql);
             ResultSet rs = ps.executeQuery()) {

            ResultSetMetaData md = rs.getMetaData();
            int cols = md.getColumnCount();

            // header
            for (int i = 1; i <= cols; i++) {
                writer.print("\"" + md.getColumnLabel(i).replace("\"", "\"\"") + "\"");
                if (i < cols) writer.print(",");
            }
            writer.println();

            // rows
            while (rs.next()) {
                for (int i = 1; i <= cols; i++) {
                    String value = rs.getString(i);
                    String safe = value == null ? "" : value.replace("\"", "\"\"");
                    writer.print("\"" + safe + "\"");
                    if (i < cols) writer.print(",");
                }
                writer.println();
            }

        } catch (FileNotFoundException e) {
            System.err.println("Unable to open CSV for writing: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("DB error while writing student CSV report: " + e.getMessage());
        }
    }
}

