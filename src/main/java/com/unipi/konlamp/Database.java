package com.unipi.konlamp;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The class that utilized the connection with the database
 */
public class Database {
    private Connection conn;

    /**
     * It is a singleton class so we used the instance variables
     */
    private static Database instance;

    /**
     * The singleton class creation
     * @return the new class
     */
    public static Database getInstance(){
        if (instance == null){
            instance = new Database();
        }
        return instance;
    }

    /**
     * This method is used to insert a new city in the database
     * @param name the name of the city
     */
    public void InsertCity(String name){
        String sql = "INSERT INTO Cities(name) VALUES (?)";
        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,name);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        }
        catch (SQLException e) {
            System.out.print(" Error the given city could not be inserted");
            e.printStackTrace();

        }
    }

    /**
     * The method is used to insert a new record in the database
     * @param record the new record
     */
    public void InsertRecord(Record record){
        String sql = "INSERT INTO Records(temp_C, humidity, windspeedKmph, uvIndex, weatherDesc, city, timestamp) VALUES (?,?,?,?,?,?,?)";
        LocalDateTime date = LocalDateTime.now();
        try {
            record.setTimestamp(date.toString());
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, record.getTemp_C());
            pstmt.setInt(2, record.getHumidity());
            pstmt.setInt(3, record.getWindspeedKmph());
            pstmt.setInt(4, record.getUvIndex());
            pstmt.setString(5, record.getWeatherDesc());
            pstmt.setString(6, record.getCity());
            pstmt.setString(7, record.getTimestamp());
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        }
        catch (SQLException e) {
            System.out.print(" The given record could not be inserted");
            e.printStackTrace();

        }
    }

    /**
     * This method is used to get all the records of the given city
     * @param city the given city
     * @return the arraylist with all the records
     */
    public ArrayList<Record> getRecords(String city){
        ArrayList <Record> records = new ArrayList<>();
        Connection conn = connect();
        String sql = "SELECT * FROM Records WHERE city = ?"; // The SQL query
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql); // we created a new preparedStatement
            ResultSet rs;
            pstmt.setString(1, city); // We added the name of the city in the SQL query
            rs = pstmt.executeQuery();
            while (rs.next()){
                // The data is transformed in a record class
                Record record = new Record();
                rs.getInt("id");
                record.setTemp_C(rs.getInt("temp_C"));
                record.setHumidity(rs.getInt("humidity"));
                record.setWindspeedKmph(rs.getInt("windspeedKmph"));
                record.setUvIndex(rs.getInt("uvIndex"));
                record.setWeatherDesc(rs.getString("weatherDesc"));
                record.setCity(rs.getString("city"));
                record.setTimestamp(rs.getString("timestamp"));
                records.add(record);
            }
            pstmt.close();
            conn.close();
            return records;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * This method to check if the given city exists in the database
     * @param city the given city
     * @return true if the city exists else otherwise
     */
    public Boolean getCity(String city){
        Connection conn = connect();
        String sql = "SELECT Name FROM Cities WHERE Name = ?"; // The SQL query
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql); // we created a new preparedStatement
            ResultSet rs;
            pstmt.setString(1, city); // We added the name of the city in the SQL query

            rs = pstmt.executeQuery();
            conn.close();
            pstmt.close();
            if (rs.next()){
                return true; // if the results set has any data it returns true
            }
            return false; // otherwise false
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * This method performs the connection with the database
     * @return the connection
     */
    private static Connection connect(){
        String connectionString = "jdbc:sqlite:Weatherapp.db"; // We specify the SQL type and the database file
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(connectionString); // we perform the connection
        } catch (SQLException ex) {
            System.out.print(" Connection could not be initiated");
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return connection;
    }
}
