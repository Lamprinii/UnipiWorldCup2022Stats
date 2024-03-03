package com.unipi.konlamp;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

/**
 * This class is used to connect with the API
 */
public class APIConnection {
    /**
     * It is a singleton class so we used the instance variables
     */
    private static APIConnection instance;

    /**
     * The empty constructor
     */
    private APIConnection(){



    }

    /**
     * The singleton class creation
     * @return the new class
     */
    public static APIConnection getInstance(){
        if (instance == null){
            instance = new APIConnection();
        }
        return instance;
    }

    /**
     * This method is used to return the current record of the given city
     * @param city the given city
     * @return the current record
     */
    public Record getWeatherData(String city){

        try
        {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://wttr.in/" + city + "?format=j1")).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject weatherdata = new JSONObject(response.body());
            String tempC = weatherdata.getJSONArray("current_condition").getJSONObject(0).get("temp_C").toString();
            String humidity = weatherdata.getJSONArray("current_condition").getJSONObject(0).get("humidity").toString();
            String windspeedKmph = weatherdata.getJSONArray("current_condition").getJSONObject(0).get("windspeedKmph").toString();
            String uvIndex = weatherdata.getJSONArray("current_condition").getJSONObject(0).get("uvIndex").toString();
            String weatherDesc = weatherdata.getJSONArray("current_condition").getJSONObject(0).getJSONArray("weatherDesc").getJSONObject(0).get("value").toString();
            Record record = new Record(Integer.valueOf(tempC), Integer.valueOf(humidity), Integer.valueOf(windspeedKmph), Integer.valueOf(uvIndex), weatherDesc, city);
            return record;

        }
        catch (Exception e){

            e.printStackTrace();
            return null;

        }
    }
}
