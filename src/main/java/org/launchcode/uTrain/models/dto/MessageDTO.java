package org.launchcode.uTrain.models.dto;



import org.launchcode.uTrain.models.Message;
import org.launchcode.uTrain.models.User;

import javax.validation.constraints.NotNull;

public class MessageDTO {

    @NotNull
    private User user;

    @NotNull
    private Message message;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
