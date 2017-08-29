package com.example.gameapp;

import java.io.Serializable;

/**
 * Created by HOCVIEN on 8/18/2017.
 */

public class Game implements Serializable {
    int ID;
    String name;
    String description;
    String image;
    float rating;
    String date;
    String platform;
    String dev;
    String trailer;
    String imgrv;
    String rv;

    public Game(int ID, String name, String description, String image, float rating, String date,
                String platform, String dev, String trailer, String imgrv, String rv) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.image = image;
        this.rating = rating;
        this.date = date;
        this.platform = platform;
        this.dev = dev;
        this.trailer = trailer;
        this.imgrv = imgrv;
        this.rv = rv;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDev() {
        return dev;
    }

    public void setDev(String dev) {
        this.dev = dev;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getImgrv() {
        return imgrv;
    }

    public void setImgrv(String imgrv) {
        this.imgrv = imgrv;
    }

    public String getRv() {
        return rv;
    }

    public void setRv(String rv) {
        this.rv = rv;
    }
}
