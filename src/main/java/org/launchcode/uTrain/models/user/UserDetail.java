package org.launchcode.uTrain.models.user;

// *1

import org.launchcode.uTrain.models.AbstractEntity;
import org.launchcode.uTrain.models.Address;
import org.launchcode.uTrain.models.Interests;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class UserDetail extends AbstractEntity {

/*These attributes don't currently have any validations because they aren't populated during initial instantiation. Will
add necessary annotations later.

 */

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

    public UserDetail(int age, int height, int weight, String firstName, String lastName, UserSex userSex, Address address, Interests interests) {
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userSex = userSex;
        this.address = address;
        this.interests = interests;
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

    // *1 Just call this method in the view to display BMI
    public double getBodyMassIndex(){
        double heightInMeters = (this.height / 39.37) ;
        double weightInKilos = (this.weight / 2.20);

        double bmi = weightInKilos / (heightInMeters * heightInMeters);

        return bmi;
    }
}
