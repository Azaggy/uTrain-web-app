package org.launchcode.uTrain.models;

import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;


@Entity
public class Gym extends AbstractEntity {

//    @NotNull
    private String gymName;

    private String gymPhoneNumber;

    private String gymEmail;

    private String gymStreet;

    private String gymCity;

    private String gymState;

    private Integer gymZipCode;

    public Gym (){}

    public Gym(String gymName, String gymPhoneNumber, String gymEmail, String gymStreet, String gymCity, String gymState, Integer gymZipCode){
        this.gymName = gymName;
        this.gymPhoneNumber = gymPhoneNumber;
        this.gymEmail=gymEmail;
        this.gymStreet= gymStreet;
        this.gymCity = gymCity;
        this.gymState = gymState;
        this.gymZipCode= gymZipCode;

    }

    public String getGymName() {
        return gymName;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public String getGymPhoneNumber() {
        return gymPhoneNumber;
    }

    public void setGymPhoneNumber(String gymPhoneNumber) {
        this.gymPhoneNumber = gymPhoneNumber;
    }

    public String getGymEmail() {
        return gymEmail;
    }

    public void setGymEmail(String gymEmail) {
        this.gymEmail = gymEmail;
    }

    public String getGymStreet() {
        return gymStreet;
    }

    public void setGymStreet(String gymStreet) {
        this.gymStreet = gymStreet;
    }

    public String getGymCity() {
        return gymCity;
    }

    public void setGymCity(String gymCity) {
        this.gymCity = gymCity;
    }

    public String getGymState() {
        return gymState;
    }

    public void setGymState(String gymState) {
        this.gymState = gymState;
    }

    public Integer getGymZipCode() {
        return gymZipCode;
    }

    public void setGymZipCode(Integer gymZipCode) {
        this.gymZipCode = gymZipCode;
    }
}
