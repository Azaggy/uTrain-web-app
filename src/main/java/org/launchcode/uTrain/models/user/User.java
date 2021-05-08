package org.launchcode.uTrain.models.user;

import org.launchcode.uTrain.models.AbstractEntity;
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


    @NotNull
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    private UserDetail userDetail;

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


}
