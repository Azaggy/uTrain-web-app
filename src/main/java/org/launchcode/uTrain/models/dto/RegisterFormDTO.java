package org.launchcode.uTrain.models.dto;

public class RegisterFormDTO extends LoginFormDTO {

    private String verifyPassword;

    private String email;


    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
