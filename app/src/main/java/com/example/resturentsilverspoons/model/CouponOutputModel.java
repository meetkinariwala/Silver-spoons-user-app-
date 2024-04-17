package com.example.resturentsilverspoons.model;

import java.util.ArrayList;

public class CouponOutputModel {

    public  boolean  status;

    public String message;

    public ArrayList<CouponModel>  Coupon;

    public CouponOutputModel(boolean status, String message, ArrayList<CouponModel> coupon) {
        this.status = status;
        this.message = message;
        Coupon = coupon;
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

    public ArrayList<CouponModel> getCoupon() {
        return Coupon;
    }

    public void setCoupon(ArrayList<CouponModel> coupon) {
        Coupon = coupon;
    }
}

