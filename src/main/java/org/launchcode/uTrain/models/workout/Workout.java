package org.launchcode.uTrain.models.workout;

import org.launchcode.uTrain.models.AbstractEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class Workout extends AbstractEntity {

    @NotNull
    @NotBlank
    private String description;

    private Date timeStamp;

    private Integer consumedCal;

    private Integer burnedCal;

    private List<Exercise> activities;

    public Workout() {}

    public Workout(String description, Date timeStamp, Integer consumedCal, Integer burnedCal, List<Exercise> activities) {
        this.description = description;
        this.timeStamp = timeStamp;
        this.consumedCal = consumedCal;
        this.burnedCal = burnedCal;
        this.activities = activities;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getConsumedCal() {
        return consumedCal;
    }

    public void setConsumedCal(Integer consumedCal) {
        this.consumedCal = consumedCal;
    }

    public Integer getBurnedCal() {
        return burnedCal;
    }

    public void setBurnedCal(Integer burnedCal) {
        this.burnedCal = burnedCal;
    }

    public List<Exercise> getActivities() {
        return activities;
    }

    public void setActivities(List<Exercise> activities) {
        this.activities = activities;
    }
}
