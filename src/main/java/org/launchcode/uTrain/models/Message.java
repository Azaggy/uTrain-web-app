package org.launchcode.uTrain.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;


@Entity
public class Message extends AbstractEntity{


    @NotNull
    @Size(max = 500, message = "Message has more than 500 characters")
    private String body;

    @NotNull
    @Size(max = 30, message = "Too many characters")
    private String recipient;


    private String sender;

    private Date date;

    public Message() {
    }

    public Message(String body, String recipient, String sender, Date date) {
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

    public String senderExtraction(String username){
        return username;
    }

    public String recipientExtraction(String username) {
        return username;
    }

    public String dateFormatter() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy hh:mm a");
        return (formatter.format(this.date));
    }
}
