package org.launchcode.uTrain.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserPhoto extends AbstractEntity {

    @OneToMany
    private final List<User> users = new ArrayList<>();

    private String imgOne;



    private String imgTwo;

    private String imgThree;

    private String imgFour;

    private String imgFive;

    private String imgSix;

    private String imgSeven;

    private String imgEight;

    private String imgNine;

    private String imgTen;

    public UserPhoto() {}

    public String getImgOne() {
       this.imgOne = "https://i.ibb.co/YjXPG54/IMG-1552.png";
        return imgOne;
    }

    public String getImgTwo() {
        return imgTwo;
    }

    public String getImgThree() {
        return imgThree;
    }

    public String getImgFour() {
        return imgFour;
    }

    public String getImgFive() {
        return imgFive;
    }

    public String getImgSix() {
        return imgSix;
    }

    public String getImgSeven() {
        return imgSeven;
    }

    public String getImgEight() {
        return imgEight;
    }

    public String getImgNine() {
        return imgNine;
    }

    public String getImgTen() {
        return imgTen;
    }

}
