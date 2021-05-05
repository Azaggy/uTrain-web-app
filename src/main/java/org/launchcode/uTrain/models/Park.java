package org.launchcode.uTrain.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class  Park extends AbstractEntity {

    @NotNull
    @Size(min = 3, max = 30, message = "Park name must have a minimum of 3 characters and a maximum of 30 characters!")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public Park (){}

    public Park (String name, Address address){
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
