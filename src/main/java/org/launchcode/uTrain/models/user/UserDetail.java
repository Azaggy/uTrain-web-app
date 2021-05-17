package org.launchcode.uTrain.models.user;

// *1

import org.launchcode.uTrain.models.AbstractEntity;
import org.launchcode.uTrain.models.Address;
import org.launchcode.uTrain.models.Interests;
import org.launchcode.uTrain.models.workout.ExerciseType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@Entity
public class UserDetail extends AbstractEntity {

/*These attributes don't currently have any validations because they aren't populated during initial instantiation. Will
add necessary annotations later.

 */
    @DateTimeFormat(pattern = "MM/dd/yyyy")

    private LocalDate birthDay;

    private int age;

    private int height;

    private int weight;

    private String firstName;

    private String lastName;

    private UserSex userSex;

    //Both Interest & Address are optional attributes that can be added after registering.
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    //Both Interest & Address are optional attributes that can be added after registering.
    @OneToOne(cascade = CascadeType.ALL)
    private Interests interests;

    public UserDetail() {};

    public UserDetail(LocalDate birthDay, int height, int weight, String firstName, String lastName, UserSex userSex, Address address, Interests interests) {
        this.birthDay = birthDay;
        this.height = height;
        this.weight = weight;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userSex = userSex;
        this.address = address;
        this.interests = interests;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
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

    public UserSex getUserSex() {
        return userSex;
    }

    public void setUserSex(UserSex userSex) {
        this.userSex = userSex;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Interests getInterests() {
        return interests;
    }

    public void setInterests(Interests interests) {
        this.interests = interests;
    }

    // *Just add method to controller to be able to use in view*
    public double getBodyMassIndex(){
        double heightInMeters = (this.height / 39.37) ;
        double weightInKilos = (this.weight / 2.20);

        double bmi = Math.round(weightInKilos / (heightInMeters * heightInMeters));

        return bmi;
    }

    public double getBodyMassIndexStandard(){
        double weight = this.weight;
        double height = this.height;
        double num = weight / (height * height);
        double bmi = Math.round(num * 703);

        return bmi;
    }

    public void getAgeFromBirthDate(User user) {
        LocalDate today = LocalDate.now();
        Period computedAge = Period.between(user.getUserDetail().getBirthDay(), today);
        int age = computedAge.getYears();
        user.getUserDetail().setAge(age);
    }

}
