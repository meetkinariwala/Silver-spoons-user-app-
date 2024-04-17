package com.example.resturentsilverspoons.model;

public class CountOutputModel {
    public String message;
    public boolean status;

    public String count;

    public CountOutputModel(String message, boolean status, String count) {
        this.message = message;
        this.status = status;
        this.count = count;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
