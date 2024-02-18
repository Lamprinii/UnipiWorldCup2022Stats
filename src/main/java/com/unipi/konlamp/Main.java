package com.unipi.konlamp;


public class Main {
    public static void main(String[] args) {

        Database db = new Database();
        // db.InsertCity("Kozani");
        APIConnection con = APIConnection.getInstance();
       //db.InsertRecord(con.getWeatherData("London"));
        System.out.println(db.getRecords("London").get(1).getCity());
    }
}