package org.launchcode.uTrain.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class  Park extends BaseAddress {

    @NotNull
    @Size(min = 3, max = 30, message = "Park name must have a minimum of 3 characters and a maximum of 30 characters!")
    private String name;

//    @OneToMany(cascade = CascadeType.ALL)
//    private Address address;

    public Park (){}

    public Park (String name){
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
