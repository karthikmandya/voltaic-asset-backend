package com.database;

import com.entity.TransformerReadingDetails;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseOperation {

    public static void main(String[] args) {
        // insert("DT-500-11", 52.2, 53.2, 61);
        // read();

        String url = "jdbc:sqlite:mydata.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            String query = "UPDATE transformer_historic_data SET timestamp = datetime(timestamp, '+15 days');";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    public  ArrayList<TransformerReadingDetails> read() {
        ArrayList<TransformerReadingDetails> details = new ArrayList<>();
        String url = "jdbc:sqlite:mydata.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            String query = "SELECT * FROM transformer_historic_data";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {

                    TransformerReadingDetails detail = new TransformerReadingDetails(rs.getString("name"), rs.getTimestamp("timestamp").toLocalDateTime()
                    , rs.getDouble("winding_temperature") ,rs.getDouble("top_oil_temperature"), rs.getDouble("humidity") );

                    details.add(detail);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return details;
    }

    public void insert(String name, double winding_temperature, double top_oil_temperature, double humidity) {
        String url = "jdbc:sqlite:mydata.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("Connected to SQLite database!");

                String insertStatement = "INSERT INTO transformer_historic_data(name,timestamp, winding_temperature, top_oil_temperature, humidity) VALUES(?, CURRENT_TIMESTAMP, ?, ? ,?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertStatement)) {
                    pstmt.setString(1, name);
                    pstmt.setDouble(2, winding_temperature);
                    pstmt.setDouble(3, top_oil_temperature);
                    pstmt.setDouble(4, humidity);
                    pstmt.executeUpdate();
                    System.out.println("Inserted with timestamp.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void setup() {
        String url = "jdbc:sqlite:mydata.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("Connected to SQLite database!");

                String createTable = """
                    CREATE TABLE IF NOT EXISTS transformer_historic_data (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT NOT NULL,
                        timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
                        winding_temperature REAL,
                        top_oil_temperature REAL,
                        humidity REAL
                    );
                """;
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute(createTable);
                    System.out.println("Table created or already exists.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
