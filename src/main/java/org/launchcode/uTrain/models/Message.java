package org.launchcode.uTrain.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
public class Message extends AbstractEntity{



    @NotNull
    @Size(max = 50)
    private String body;

    @NotNull
    private String recipient;

//    @OneToOne
    @NotNull
    private String sender;

    private Date date;

    public Message() {
    }

    public Message(@NotNull @Size(max = 50) String body, @NotNull String recipient, @NotNull String sender, Date date) {
        this.body = body;
        this.recipient = recipient;
        this.sender = sender;
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
