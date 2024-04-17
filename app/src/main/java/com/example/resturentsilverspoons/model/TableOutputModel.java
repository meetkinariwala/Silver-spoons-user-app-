package com.example.resturentsilverspoons.model;

import java.util.ArrayList;

public class TableOutputModel {

    public  boolean status;

    public  String message;

    public ArrayList<TableModel> table;

    public TableOutputModel(boolean status, String message, ArrayList<TableModel> table) {
        this.status = status;
        this.message = message;
        this.table = table;
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

    public ArrayList<TableModel> getTable() {
        return table;
    }

    public void setTable(ArrayList<TableModel> table) {
        this.table = table;
    }
}
