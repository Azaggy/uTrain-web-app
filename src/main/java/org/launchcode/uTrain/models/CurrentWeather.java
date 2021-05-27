package org.launchcode.uTrain.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentWeather implements Serializable {

    private String description;
    private BigDecimal temperature;
    private BigDecimal feelsLike;
    private BigDecimal windSpeed;
    private BigDecimal humidity;
    private BigDecimal lat;
    private BigDecimal lon;
    private BigDecimal sunrise;
    private BigDecimal sunset;
    private BigDecimal temp_min;
    private BigDecimal temp_max;
    private String normiesDate;
    private String normiesDate2;

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
                          BigDecimal windSpeed, BigDecimal lat, BigDecimal lon, BigDecimal sunrise, BigDecimal sunset,
                          BigDecimal temp_min, BigDecimal temp_max) {
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

    public BigDecimal getSunrise() {
        return sunrise;
    }

    public void setSunrise(BigDecimal sunrise) {
        this.sunrise = sunrise;
    }

    public BigDecimal getSunset() {
        return sunset;
    }

    public void setSunset(BigDecimal sunset) {
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

    public void setNormiesDate(String normiesDate) {
        this.normiesDate = normiesDate;
    }

    public void setNormiesDate2(BigDecimal sunset) {
//        this.normiesDate2 = normiesDate2;
        this.normiesDate2 = getDateString(sunset);
    }

    public String getNormiesDate() {

        return normiesDate;
    }
    private String getDateString(BigDecimal timeInMilliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy 'at' HH:mm:ss z");
        normiesDate = formatter.format(new Date(String.valueOf(timeInMilliseconds)));
        return normiesDate;

    }

    public String getNormiesDate2(BigDecimal sunset) {
        normiesDate2 = getDateString(sunset);
        return normiesDate2;
    }
    public String getNormiesDate2() {
        return normiesDate2;
    }

//    private String getDateString(BigDecimal timeInMilliseconds) {
//        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy 'at' HH:mm:ss z");
//        this.normiesDate = formatter.format(timeInMilliseconds);
//        return normiesDate;
//    }
}
