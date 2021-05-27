package org.launchcode.uTrain.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import static java.util.Date.parse;

public class CurrentWeather implements Serializable {

    private String description;
    private BigDecimal temperature;
    private BigDecimal feelsLike;
    private BigDecimal windSpeed;
    private BigDecimal humidity;
    private BigDecimal lat;
    private BigDecimal lon;
    private String sunrise;
    private String sunset;
    private BigDecimal temp_min;
    private BigDecimal temp_max;
    private String timeZone;

//    private String getDateString(BigDecimal timeInMilliseconds) {
//        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy 'at' HH:mm:ss z");
//        normiesDate = formatter.format(timeInMilliseconds);
//        return normiesDate;

//    public TimeConvert(Integer convert) {
//        String converted = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (convert));
//    }
//    String sunrise2 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date (sunrise*1000));
//    String sunset2 = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (sunset*1000));
//    public CurrentWeather() {
//    }

    public CurrentWeather(String description, BigDecimal humidity, BigDecimal temperature, BigDecimal feelsLike,
                          BigDecimal windSpeed, BigDecimal lat, BigDecimal lon, String sunrise, String sunset,
                          BigDecimal temp_min, BigDecimal temp_max, String timeZone) {
        this.description = description;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.lat = lat;
        this.lon = lon;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.timeZone = timeZone;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public BigDecimal getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(BigDecimal feelsLike) {
        this.feelsLike = feelsLike;
    }

    public BigDecimal getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(BigDecimal windSpeed) {
        this.windSpeed = windSpeed;
    }

    public BigDecimal getHumidity() {
        return humidity;
    }

    public void setHumidity(BigDecimal humidity) {
        this.humidity = humidity;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public String getSunrise() {
        Integer integer = Integer.parseInt(sunrise);
        String sunrise2 = new SimpleDateFormat("MM/dd/yyyy 'at' HH:mm:ss").format(new Date(integer *1000L));
        return sunrise2;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        Integer integer = Integer.parseInt(sunset);
        String sunset2 = new SimpleDateFormat("MM/dd/yyyy 'at' HH:mm:ss").format(new Date (integer *1000L));
        return sunset2;
    }


    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public BigDecimal getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(BigDecimal temp_min) {
        this.temp_min = temp_min;
    }

    public BigDecimal getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(BigDecimal temp_max) {
        this.temp_max = temp_max;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
//    private String getDateString(BigDecimal timeInMilliseconds) {
//        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy 'at' HH:mm:ss z");
//        normiesDate = formatter.format(new Date(String.valueOf(timeInMilliseconds)));
//        return normiesDate;
//
//    }
//    private String getDateString(String timeInMilliseconds) {
//        Date date = new Date(timeInMilliseconds);
//        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy 'at' HH:mm:ss z");
//        String normiesDate = formatter.format(date);
//        System.out.println(normiesDate);
//        return normiesDate;
//    }

//    private String getDateString(BigDecimal timeInMilliseconds) {
//        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy 'at' HH:mm:ss z");
//        this.normiesDate = formatter.format(timeInMilliseconds);
//        return normiesDate;
//    }
}
