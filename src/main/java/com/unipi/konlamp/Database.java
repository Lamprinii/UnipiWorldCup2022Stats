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
    public void InsertRecord( int temp_C, int humidity, int windspeedKmph, int uvIndex, String weatherDesc, String city){
        String sql = "INSERT INTO Records(temp_C, humidity, windspeedKmph, uvIndex, weatherDesc, timestamp) VALUES (?,?,?,?,?,?,?)";
        LocalDateTime date = LocalDateTime.now();
        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, temp_C);
            pstmt.setInt(2, humidity);
            pstmt.setInt(3, windspeedKmph);
            pstmt.setInt(4, uvIndex);
            pstmt.setString(5, weatherDesc);
            pstmt.setString(6,city);
            pstmt.setString(7, date.toString());
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        }
        catch (SQLException e) {
            System.out.print(" The given record could not be inserted");
            e.printStackTrace();

        }
    }



    public ArrayList<String> getProducts(){
        Connection conn = connect();
        String sql = "SELECT * FROM Blockchain ORDER BY asc ASC";
        Statement stmt = null;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                rs.getInt("asc");
            }
            return null;
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
