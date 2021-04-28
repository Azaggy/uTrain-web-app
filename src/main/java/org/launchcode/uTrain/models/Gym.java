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
    @NotNull
    private String streetAddress;
    @NotNull
    private String city;
    @NotNull
    private Integer zipCode;

    public Gym (){}

    public Gym(String name, String phoneNumber, String email, String streetAddress, String city, Integer zipCode){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email=email;
        this.streetAddress= streetAddress;
        this.city = city;
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

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }
}
