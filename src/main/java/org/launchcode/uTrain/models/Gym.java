package org.launchcode.uTrain.models;

import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;


@Entity
public class Gym extends AbstractEntity {

    @NotNull
    private String name;

    private String phoneNumber;

    private String email;

    private String street;

    private String city;

    private String state;

    private Integer zipCode;

    public Gym (){}

    public Gym(String name, String phoneNumber, String email, String street, String city, String state, Integer zipCode){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email=email;
        this.street= street;
        this.city = city;
        this.state = state;
        this.zipCode=zipCode;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }
}
