package org.launchcode.uTrain.models.workout;

import org.launchcode.uTrain.models.AbstractEntity;
import org.launchcode.uTrain.models.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Workout extends AbstractEntity {

    @NotNull
    @NotBlank
    private String description;

    private Date timeStamp;

    private Integer consumedCal;

    private Integer burnedCal;

    @OneToOne(cascade = CascadeType.ALL)
    private Exercise exercise;

//    @OneToMany(mappedBy="workout")
//    private List<Exercise> activities = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Workout() {}

    public Workout(String description, Date timeStamp, Integer consumedCal, Integer burnedCal,
                   Exercise exercise) {
        this.description = description;
        this.timeStamp = timeStamp;
        this.consumedCal = consumedCal;
        this.burnedCal = burnedCal;
        this.exercise = exercise;
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

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

