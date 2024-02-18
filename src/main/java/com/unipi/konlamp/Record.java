package com.unipi.konlamp;

public class Record {

    private int temp_C;
    private int humidity;
    private int windspeedKmph;
    private int uvIndex;
    private String weatherDesc;
    private String city;

    private String timestamp;

    public Record(int temp_C, int humidity, int windspeedKmph, int uvIndex, String weatherDesc, String city, String timestamp) {
        this.temp_C = temp_C;
        this.humidity = humidity;
        this.windspeedKmph = windspeedKmph;
        this.uvIndex = uvIndex;
        this.weatherDesc = weatherDesc;
        this.city = city;
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Record(int temp_C, int humidity, int windspeedKmph, int uvIndex, String weatherDesc, String city) {
        this.temp_C = temp_C;
        this.humidity = humidity;
        this.windspeedKmph = windspeedKmph;
        this.uvIndex = uvIndex;
        this.weatherDesc = weatherDesc;
        this.city = city;
    }

    public int getTemp_C() {
        return temp_C;
    }

    public void setTemp_C(int temp_C) {
        this.temp_C = temp_C;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getWindspeedKmph() {
        return windspeedKmph;
    }

    public void setWindspeedKmph(int windspeedKmph) {
        this.windspeedKmph = windspeedKmph;
    }

    public int getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(int uvIndex) {
        this.uvIndex = uvIndex;
    }

    public String getWeatherDesc() {
        return weatherDesc;
    }

    public void setWeatherDesc(String weatherDesc) {
        this.weatherDesc = weatherDesc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Record() {

    }
}
