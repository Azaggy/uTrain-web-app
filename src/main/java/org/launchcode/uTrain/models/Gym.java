package org.launchcode.uTrain.models;

import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;


@Entity
public class Gym extends AbstractEntity {

//    @NotNull
    private String name;

    private String phoneNumber;

    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public Gym (){}

    public Gym(String name, String phoneNumber, String email, Address address){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email= email;
        this.address = address;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
