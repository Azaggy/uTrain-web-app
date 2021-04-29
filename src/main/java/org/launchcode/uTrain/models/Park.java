package org.launchcode.uTrain.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Park extends AbstractEntity {
//    @NotNull
    private String parkName;

//    @NotNull
//    @Size(min=5, max=5)
    private Integer parkZipCode;

    public Park (){}

    public Park (String parkName, Integer parkZipCode){
        this.parkName = parkName;
        this.parkZipCode = parkZipCode;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public Integer getParkZipCode() {
        return parkZipCode;
    }

    public void setParkZipCode(Integer parkZipCode) {
        this.parkZipCode = parkZipCode;
    }
}
