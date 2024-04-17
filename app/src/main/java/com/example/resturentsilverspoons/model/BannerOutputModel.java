package com.example.resturentsilverspoons.model;

import java.util.ArrayList;

public class BannerOutputModel {

    public Boolean status;
    public String message;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<bannermodel> getBaneers() {
        return baneers;
    }

    public void setBaneers(ArrayList<bannermodel> baneers) {
        this.baneers = baneers;
    }

    public BannerOutputModel(Boolean status, String message, ArrayList<bannermodel> baneers) {
        this.status = status;
        this.message = message;
        this.baneers = baneers;
    }

    public ArrayList<bannermodel> baneers;
}
