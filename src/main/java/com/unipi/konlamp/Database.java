package com.unipi.konlamp;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Database {
    private Connection conn;


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

    public ArrayList<Record> getRecords(String city){
        ArrayList <Record> records = new ArrayList<>();
        Connection conn = connect();
        String sql = "SELECT * FROM Records WHERE city = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs;
            pstmt.setString(1, city);
            rs = pstmt.executeQuery();
            while (rs.next()){
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
            return records;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private static Connection connect(){
        String connectionString = "jdbc:sqlite:Weatherapp.db";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(connectionString);
        } catch (SQLException ex) {
            System.out.print(" Connection could not be initiated");
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return connection;
    }
}
