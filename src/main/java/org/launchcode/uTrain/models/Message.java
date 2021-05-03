package org.launchcode.uTrain.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Optional;


@Entity
public class Message extends AbstractEntity{


    @NotNull
    @Size(max = 50)
    private String body;


    private Integer recipientId;

    private Integer senderId;

    private Date date;

    public Message() {
    }

    public Message(String body, Integer recipientId, Integer senderId, Date date) {
        this.body = body;
        this.recipientId = recipientId;
        this.senderId = senderId;
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Integer recipientId) {
        this.recipientId = recipientId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSender(Integer senderId) {
        this.senderId = senderId;
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
}
