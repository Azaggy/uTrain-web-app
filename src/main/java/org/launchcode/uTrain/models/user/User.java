package org.launchcode.uTrain.models.user;

import org.launchcode.uTrain.models.AbstractEntity;
import org.launchcode.uTrain.models.workout.Exercise;
import org.launchcode.uTrain.models.workout.Workout;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
public class User extends AbstractEntity {

    @NotNull
    private String username;

    @NotNull
    private String pwHash;


    @NotNull
    private String email;

    private String image;

    @OneToOne(cascade = CascadeType.ALL)
    private UserDetail userDetail;

    //This is linked to the workout class. Every instantiation of this class is tied to a certain user
    @OneToMany(mappedBy = "user")
    private List<Workout> workouts = new ArrayList<>();

    private List<String> friends = new ArrayList<>();

    //this attribute is used to direct user to either profile or add profile page depending on whether attribute is true
    private boolean isNew;


    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {}

    public User(String username, String password, String email) {
        this.username = username;
        this.pwHash = encoder.encode(password);
        this.email = email;
        this.isNew = true;
    }

    public User(String username, String password, String email, UserDetail userDetail) {
        this.username = username;
        this.pwHash = encoder.encode(password);
        this.email = email;
        this.userDetail = userDetail;
    }

    public User(String username, String password, String email, String image) {
        this.username = username;
        this.pwHash = encoder.encode(password);
        this.email = email;
        this.isNew = true;
        this.image = image;

    }

    public void setPwHash(String pwHash) {
        this.pwHash = pwHash;
    }

    public String getPwHash() {
        return pwHash;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }
}