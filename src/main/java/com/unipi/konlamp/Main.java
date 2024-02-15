package com.unipi.konlamp;


public class Main {
    public static void main(String[] args) {

        // Database db = new Database();
        // db.InsertCity("Kozani");
        APIConnection con = APIConnection.getInstance();
        con.getWeatherData("London");
    }
}