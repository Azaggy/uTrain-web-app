package org.launchcode.uTrain.models;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Trainer extends AbstractEntity{

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;


    private String contactNumber;

    @NotBlank(message = "Email is required")
    @Email
    private String contactEmail;

    public Trainer(String name, String contactNumber, String contactEmail){
        this.name = name;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
    }

    public Trainer(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @Override
    public String toString() {
        return "name";
    }
}

