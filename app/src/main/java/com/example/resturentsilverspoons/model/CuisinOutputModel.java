package com.example.resturentsilverspoons.model;

import java.util.ArrayList;

public class CuisinOutputModel {

    public boolean status;

    public String message;

    public ArrayList<CuisinModel> cuisins;

    public CuisinOutputModel(boolean status, String message, ArrayList<CuisinModel> cuisins) {
        this.status = status;
        this.message = message;
        this.cuisins = cuisins;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<CuisinModel> getCuisins() {
        return cuisins;
    }

    public void setCuisins(ArrayList<CuisinModel> cuisins) {
        this.cuisins = cuisins;
    }
}
