package org.launchcode.uTrain.models;

import javax.persistence.*;

@Entity
@Table(name="instructional_workouts")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String vidName;
    private String vidType;

    @Lob
    private byte[] data;

    public Video(String vidName, String vidType, byte[] data) {
        this.vidName = vidName;
        this.vidType = vidType;
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getVidName() {
        return vidName;
    }

    public void setVidName(String vidName) {
        this.vidName = vidName;
    }

    public String getVidType() {
        return vidType;
    }

    public void setVidType(String vidType) {
        this.vidType = vidType;
    }

    public Video() {};

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
