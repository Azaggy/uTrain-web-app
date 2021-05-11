package org.launchcode.uTrain.models;

import javax.persistence.Entity;

@Entity
public class Amenities extends AbstractEntity {

    private boolean swimmingPool=false;
    private boolean tennisCourt= false;
    private boolean bikePath = false;
    private boolean walkingPath = false;
    private boolean golfCourse = false;
    private boolean drivingRange = false;
    private boolean baseballField = false;
    private boolean basketballCourt= false;

    public Amenities(){}

    public Amenities(boolean swimmingPool, boolean tennisCourt, boolean bikePath, boolean walkingPath,
                     boolean golfCourse, boolean drivingRange, boolean baseballField, boolean basketballCourt){
        this.swimmingPool=swimmingPool;
        this.tennisCourt=tennisCourt;
        this.bikePath=bikePath;
        this.walkingPath=walkingPath;
        this.golfCourse=golfCourse;
        this.drivingRange=drivingRange;
        this.baseballField=baseballField;
        this.basketballCourt=basketballCourt;
    }

    public boolean isSwimmingPool() {
        return swimmingPool;
    }

    public void setSwimmingPool(boolean swimmingPool) {
        this.swimmingPool = swimmingPool;
    }

    public boolean isTennisCourt() {
        return tennisCourt;
    }

    public void setTennisCourt(boolean tennisCourt) {
        this.tennisCourt = tennisCourt;
    }

    public boolean isBikePath() {
        return bikePath;
    }

    public void setBikePath(boolean bikePath) {
        this.bikePath = bikePath;
    }

    public boolean isWalkingPath() {
        return walkingPath;
    }

    public void setWalkingPath(boolean walkingPath) {
        this.walkingPath = walkingPath;
    }

    public boolean isGolfCourse() {
        return golfCourse;
    }

    public void setGolfCourse(boolean golfCourse) {
        this.golfCourse = golfCourse;
    }

    public boolean isDrivingRange() {
        return drivingRange;
    }

    public void setDrivingRange(boolean drivingRange) {
        this.drivingRange = drivingRange;
    }

    public boolean isBaseballField() {
        return baseballField;
    }

    public void setBaseballField(boolean baseballField) {
        this.baseballField = baseballField;
    }

    public boolean isBasketballCourt() {
        return basketballCourt;
    }

    public void setBasketballCourt(boolean basketballCourt) {
        this.basketballCourt = basketballCourt;
    }
}
