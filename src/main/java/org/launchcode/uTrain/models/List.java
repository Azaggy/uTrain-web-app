package org.launchcode.uTrain.models;

import java.time.OffsetDateTime;

public class List {
    private long dt;
    private Main main;
    private Weather[] weather;
    private Clouds clouds;
    private Wind wind;
    private long visibility;
    private double pop;
    private Sys sys;
    private OffsetDateTime dtTxt;

    public long getDt() { return dt; }
    public void setDt(long value) { this.dt = value; }

    public Main getMain() { return main; }
    public void setMain(Main value) { this.main = value; }

    public Weather[] getWeather() { return weather; }
    public void setWeather(Weather[] value) { this.weather = value; }

    public Clouds getClouds() { return clouds; }
    public void setClouds(Clouds value) { this.clouds = value; }

    public Wind getWind() { return wind; }
    public void setWind(Wind value) { this.wind = value; }

    public long getVisibility() { return visibility; }
    public void setVisibility(long value) { this.visibility = value; }

    public double getPop() { return pop; }
    public void setPop(double value) { this.pop = value; }

    public Sys getSys() { return sys; }
    public void setSys(Sys value) { this.sys = value; }

    public OffsetDateTime getDtTxt() { return dtTxt; }
    public void setDtTxt(OffsetDateTime value) { this.dtTxt = value; }
}
