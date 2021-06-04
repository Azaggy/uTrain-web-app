package org.launchcode.uTrain.models.workout;

import org.launchcode.uTrain.models.AbstractEntity;
import org.launchcode.uTrain.models.user.User;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    @Min(value = 0)
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

//    @NotNull
    private Double netCal;

    public Workout() {
    }

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

    public Double getNetCal() {
        if (consumedCal != null) {
            Double netCal2 = consumedCal.doubleValue() - Double.valueOf(burnedCal);
            return netCal2;
        }else {
            netCal = 0.0;
            return netCal;
        }

//        return consumedCal - burnedCal;

    }


    public void setNetCal(Double netCal) {
        this.netCal = netCal;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getCaloriesBurnedForFemale(int weight, int age) {
        double heartRate = this.heartRate;
        double duration = this.duration;
        double altWeight = weight;
        double altAge = age;

        double calc =
                ((-20.4022 + (0.4472 * heartRate) - (0.05741 * altWeight) + (0.2017 * altAge)) * duration) / 4.184;

        double calsBurned = Math.round(calc);

        return calsBurned;
    }

    public double getCaloriesBurnedForMale(int weight, int age) {
        double heartRate = this.heartRate;
        double duration = this.duration;
        double altWeight = (weight / 2.2);
        double altAge = age;

        double calc =
                ((-55.0969 + (0.6309 * heartRate) - (0.0904 * altWeight) + (0.2017 * altAge)) * duration) / 4.184;

        double calsBurned = Math.round(calc);

        return calsBurned;
    }

    // method to select correct MET numbed based on exercise type. Will probably refactor types to more
    // accurate exercise for a better estimate calories burned.
    public double metGetter(String exerciseType) {

        double result;
        switch (exerciseType) {
            case "Active Recovery":
                result = 3.3;
                break;
            case "Aerobic Exercise":
            case "Anaerobic Exercise":
                result = 8.0;
                break;
            case "Boot Camp":
                result = 10.0;
                break;
            case "Cross Training":
                result = 9.0;
                break;
            case "Everyday Moves":
                result = 1.5;
                break;
            case "Intervals":
                result = 8.5;
                break;
            case "Isometrics":
                result = 3.6;
                break;
            case "Muscle Training":
                result = 5.8;
                break;
            case "Repetitions":
                result = 4.0;
                break;
            case "Steady-Cardio":
                result = 7;
                break;

            default:
                result = 0;
                break;

        }
        return result;
    }

    // method for calculating calories burned based on MET and exercise type.
    public double getCaloriesBurnedUsingMet(int weight, double met) {

        double newMet = met;
        double newWeight = (weight / 2.2);
        double time = this.duration;

        double calc = ((newMet * 3.5 * newWeight) / 200) * time;

        return calc;

    }

    public String dateFormatter() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy hh:mm a");
    return (formatter.format(this.timeStamp));
    }


}

