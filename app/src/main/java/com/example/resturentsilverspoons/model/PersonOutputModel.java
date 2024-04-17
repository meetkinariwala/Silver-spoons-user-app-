package com.example.resturentsilverspoons.model;

public class PersonOutputModel {

    public boolean status;
    public String message;
    public PersonModel person;

    public PersonOutputModel(boolean status, String message, PersonModel person) {
        this.status = status;
        this.message = message;
        this.person = person;
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

    public PersonModel getPerson() {
        return person;
    }

    public void setPerson(PersonModel person) {
        this.person=person;
    }
}
