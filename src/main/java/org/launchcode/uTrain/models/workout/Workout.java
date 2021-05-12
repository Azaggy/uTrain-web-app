package org.launchcode.uTrain.models.workout;

import org.launchcode.uTrain.models.AbstractEntity;
import org.launchcode.uTrain.models.user.User;

import javax.persistence.*;
import javax.validation.constraints.*;
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

    @Max(value = 10000, message = "Woah there buddy, are you alive!?")
    @Min(value = 0)
    private Integer consumedCal;

    @Max(value = 10000, message = "We may need to call a doctor!!")
    @Min(value  = 0)
    private double burnedCal;

    @Max(value = 180, message = "Workout can not be longer than 3 hours!!")
    @Min(value = 0)
    private Integer duration;

    @Max(value = 250)
    @Min(value = 0)
    private Integer heartRate;

    @OneToOne(cascade = CascadeType.ALL)
    private Exercise exercise;

//    @OneToMany(mappedBy="workout")
//    private List<Exercise> activities = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Workout() {}

    public Workout(String description, Date timeStamp, Integer consumedCal, Integer burnedCal,
                   Integer duration, Integer heartRate, Exercise exercise) {
        this.description = description;
        this.timeStamp = timeStamp;
        this.consumedCal = consumedCal;
        this.burnedCal = burnedCal;
        this.duration = duration;
        this.heartRate = heartRate;
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

    public double getBurnedCal() {
        return burnedCal;
    }

    public void setBurnedCal(double burnedCal) {
        this.burnedCal = burnedCal;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
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

    public double getCaloriesBurnedForFemale(int weight, int age){
        double heartRate = this.heartRate;
        double duration = this.duration;
        double altWeight = weight;
        double altAge = age;

        double calc =
                ((-20.4022 + (0.4472 * heartRate) - (0.05741 * altWeight) + (0.2017 * altAge)) * duration) / 4.184;

        double calsBurned = Math.round(calc);

        return calsBurned;
    }

    public double getCaloriesBurnedForMale(int weight, int age){
        double heartRate = this.heartRate;
        double duration = this.duration;
        double altWeight = (weight / 2.2);
        double altAge = age;

        double calc =
                ((-55.0969 + (0.6309 * heartRate) - (0.0904 * altWeight) + (0.2017 * altAge)) * duration) / 4.184;

        double calsBurned = Math.round(calc);

        return calsBurned;
    }
}

