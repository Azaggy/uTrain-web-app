package org.launchcode.uTrain.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;


@Entity
public class User extends AbstractEntity {

    @NotNull
    private String username;

    @NotNull
    private String pwHash;


    private String firstName;


    private String lastName;


    private UserSex sex;

    @NotNull
    private String email;


    private int age;


    private int height;


    private int weight;


    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {}

    public User(String username, String password, String email) {
        this.username = username;
        this.pwHash = encoder.encode(password);
        this.email = email;
    }

//    public User (String username, String password, String firstName, String lastName,
//                 UserSex sex, String email, int age, int height, int weight) {
//        this.username = username;
//        this.pwHash = encoder.encode(password);
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.sex = sex;
//        this.email = email;
//        this.age = age;
//        this.height = height;
//        this.weight = weight;
//    }

    public void setPwHash(String pwHash) {
        this.pwHash = pwHash;
    }

    public String getUsername() {
        return username;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserSex getSex() {
        return sex;
    }

    public void setSex(UserSex sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
