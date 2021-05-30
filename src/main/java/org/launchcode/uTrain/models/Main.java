package org.launchcode.uTrain.models;

public class Main {
    private double temp;
    private double feelsLike;
    private double tempMin;
    private double tempMax;
    private long pressure;
    private long seaLevel;
    private long grndLevel;
    private long humidity;
    private long tempKf;

    public double getTemp() { return temp; }
    public void setTemp(double value) { this.temp = value; }

    public double getFeelsLike() { return feelsLike; }
    public void setFeelsLike(double value) { this.feelsLike = value; }

    public double getTempMin() { return tempMin; }
    public void setTempMin(double value) { this.tempMin = value; }

    public double getTempMax() { return tempMax; }
    public void setTempMax(double value) { this.tempMax = value; }

    public long getPressure() { return pressure; }
    public void setPressure(long value) { this.pressure = value; }

    public long getSeaLevel() { return seaLevel; }
    public void setSeaLevel(long value) { this.seaLevel = value; }

    public long getGrndLevel() { return grndLevel; }
    public void setGrndLevel(long value) { this.grndLevel = value; }

    public long getHumidity() { return humidity; }
    public void setHumidity(long value) { this.humidity = value; }

    public long getTempKf() { return tempKf; }
    public void setTempKf(long value) { this.tempKf = value; }
}
