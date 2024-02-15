package com.unipi.konlamp;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class APIConnection {
    private static APIConnection instance;
    private APIConnection(){



    }
    public static APIConnection getInstance(){
        if (instance == null){
            instance = new APIConnection();
        }
        return instance;
    }
    public void getWeatherData(String city){

        try
        {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://wttr.in/" + city + "?format=j1")).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject weatherdata = new JSONObject(response.body());
            String tempC = weatherdata.getJSONArray("current_condition").getJSONObject(0).get("temp_C").toString();
            System.out.println(tempC);
            String humidity = weatherdata.getJSONArray("current_condition").getJSONObject(0).get("humidity").toString();
            System.out.println(humidity);
            String windspeedKmph = weatherdata.getJSONArray("current_condition").getJSONObject(0).get("windspeedKmph").toString();
            System.out.println(windspeedKmph);
            String uvIndex = weatherdata.getJSONArray("current_condition").getJSONObject(0).get("uvIndex").toString();
            System.out.println(uvIndex);
            String weatherDesc = weatherdata.getJSONArray("current_condition").getJSONObject(0).get("weatherDesc").toString();
            System.out.println(weatherDesc);

        }
        catch (Exception e){

            e.printStackTrace();

        }
    }
}
