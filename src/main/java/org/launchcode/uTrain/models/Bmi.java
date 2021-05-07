package org.launchcode.uTrain.models;

public class Bmi {

    private UserDetail userDetail;

    double height = userDetail.getHeight();

    double weight = userDetail.getWeight();

    double bmi = (weight)/(height*height);

    public Bmi() {}

    public Bmi(int height, int weight) {
        this.height = height;
        this.weight= weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }
}
