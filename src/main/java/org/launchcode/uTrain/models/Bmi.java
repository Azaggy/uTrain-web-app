package org.launchcode.uTrain.models;

public class Bmi {

    private UserDetail userDetail;

    int height = userDetail.getHeight();

    int weight = userDetail.getWeight();

    int bmi = (weight)/(height*height);

    public Bmi() {}

    public Bmi(int height, int weight) {
        this.height = height;
        this.weight= weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(int bmi) {
        this.bmi = bmi;
    }
}
