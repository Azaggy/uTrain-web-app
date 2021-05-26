package org.launchcode.uTrain.models;

import java.io.Serializable;
import java.math.BigDecimal;

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

    public CurrentWeather() {
    }

    public CurrentWeather(String description, BigDecimal humidity, BigDecimal temperature, BigDecimal feelsLike,
                          BigDecimal windSpeed, BigDecimal lat, BigDecimal lon, BigDecimal sunrise, BigDecimal sunset, BigDecimal temp_min, BigDecimal temp_max) {
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

    public void setLon(BigDecimal lon) { this.lon = lon;}

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
}
