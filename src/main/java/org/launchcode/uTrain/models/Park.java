package org.launchcode.uTrain.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Park extends AbstractEntity {
    @NotNull
    private String name;

    @NotNull
    @Size(min=5, max=5)
    private Integer zipCode;

    public Park (){}

    public Park (String name, Integer zipCode){
        this.name = name;
        this.zipCode = zipCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }
}
