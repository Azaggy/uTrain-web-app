package org.launchcode.uTrain.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserPhoto extends AbstractEntity {

    @OneToMany
    private final List<User> users = new ArrayList<>();

    private String profilePic;


    public UserPhoto() {}

    public String getProfilePicImagePath() {
        if (profilePic == null) return null;

        return "/user-photo/" + profilePic;
    }
    public String getProfilePic() {
        return profilePic;
    }

    public String setProfilePic(String profilePic) {
        this.profilePic = profilePic;
        return profilePic;
    }

}
