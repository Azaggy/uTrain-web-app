package org.launchcode.uTrain.models;

import com.sun.istack.NotNull;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Task {

private int Id;
private String date;
private String startTinme;
private String stopTime;

private String description;
@ManyToOne
@JoinColumn(name="USER_Email")
private User user;

    public Task(String date, String startTinme, String stopTime, String description, User user) {
        this.date = date;
        this.startTinme = startTinme;
        this.stopTime = stopTime;
        this.description = description;
        this.user = user;
    }

    public Task(String date, String startTinme, String stopTime, String description) {
        this.date = date;
        this.startTinme = startTinme;
        this.stopTime = stopTime;
        this.description = description;
    }
    public Task(){};

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTinme() {
        return startTinme;
    }

    public void setStartTinme(String startTinme) {
        this.startTinme = startTinme;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
