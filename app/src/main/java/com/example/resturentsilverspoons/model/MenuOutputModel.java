package com.example.resturentsilverspoons.model;

import java.util.ArrayList;

public class MenuOutputModel {

    public  boolean status;

    public  String message;

    public ArrayList<MenuModel> menu;

    public MenuOutputModel(boolean status, String message, ArrayList<MenuModel> menu) {
        this.status = status;
        this.message = message;
        this.menu = menu;
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

    public ArrayList<MenuModel> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<MenuModel> menu) {
        this.menu = menu;
    }
}
