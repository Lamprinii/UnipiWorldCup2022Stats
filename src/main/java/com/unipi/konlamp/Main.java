package com.unipi.konlamp;

/*
    Konstantinos Perrakis mpsp2333
    Lamprini Zerva mpsp2312
 */

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *The main class of the program
 */
public class Main {
    /**
     * The main method
     * @param args
     */
    public static void main(String[] args) {
        int choice;
        do {
            // Main menu
            System.out.println("");
            System.out.println("Main Menu: ");
            System.out.println("Press 1: To get a new Temperature");
            System.out.println("Press 2: To get history of records");
            System.out.println("Press 3: To get the temperature graph");
            System.out.println("Press 0: To exit the program");

            Scanner scanner = new Scanner(System.in);
            Database db=Database.getInstance();
            do {
                while (!scanner.hasNextInt()) {
                    System.out.println("Wrong Input! Try Again");
                    System.out.println("Choice:");
                    scanner.next();
                }
                choice = scanner.nextInt();
                if (choice < 0 || choice > 4) {
                    System.out.println("Wrong Input! Try Again");
                    System.out.println("Choice:");
                }
            }
            while (choice < 0 || choice >= 4); // Error input detection
            switch (choice) {
                case 1: {
                    System.out.println("Please insert the name of the city you want to search for");
                    Scanner scannerCity = new Scanner(System.in);
                    String city = scannerCity.next();
                    city = city.toUpperCase(); // We convert the input to uppercase to avoid errors
                    if (db.getCity(city) == false) {
                        db.InsertCity(city);
                    }
                    APIConnection con = APIConnection.getInstance();
                    db.InsertRecord(con.getWeatherData(city)); //getting data from the API and insert them in the database
                    break;

                }
                case 2: {
                    System.out.println("Please insert the name of the city you want to search for");
                    Scanner scannerCity = new Scanner(System.in);
                    String city = scannerCity.next();
                    city = city.toUpperCase(); // We convert the input to uppercase to avoid errors
                    System.out.println("You gave the following city:" + city);
                    while (!db.getCity(city)) { // Check if the given city exists in the database
                        System.out.println("The given city does not exist in the database");
                        System.out.println("Give another insert or press E+Enter to return to the main menu");

                        city = scannerCity.next();
                        if (city.toLowerCase().equals("e")){
                            break;
                        }
                        city = city.toUpperCase();

                    }
                    if (city.toLowerCase().equals("e")){
                        continue;
                    }
                    ArrayList<Record> records = db.getRecords(city);
                    System.out.println("//////////////////" + city + "////////////////");
                    for (int i = 0; i < records.size(); i++) { //print the record history of the given city
                        System.out.println(records.get(i).toString());
                    }
                    break;
                }
                case 3: {
                    System.out.println("Please insert the name of the city you want to search for");
                    Scanner scannerCity = new Scanner(System.in);
                    String city = scannerCity.next();
                    city = city.toUpperCase();
                    System.out.println("You gave the following city:" + city);
                    while (!db.getCity(city)) { // Check if the given city exists in the database
                        System.out.println("The given city does not exist in the database");
                        System.out.println("Give another insert or press E+Enter to return to the main menu");

                        city = scannerCity.next();
                        if (city.toLowerCase().equals("e")){
                            break;
                        }
                        city = city.toUpperCase();
                    }
                    if (city.toLowerCase().equals("e")){
                        continue;
                    }
                    ArrayList<Record> records = db.getRecords(city);
                    int[] temps = new int[records.size()]; // we extract the temperatures from the records of the given city
                    for (int i = 0; i < records.size(); i++) {
                        temps[i] = records.get(i).getTemp_C();
                    }
                    JFrame frame = new JFrame(); // a new jframe is created
                    // set size, layout and location for frame.
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.add(new Plot(temps)); // we print the plot of the jframe
                    frame.setSize(500, 500);
                    frame.setLocation(250, 250);
                    frame.setVisible(true);
                    break;
                }
            }
        }while (choice!=0);

    }
}