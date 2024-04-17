package com.example.resturentsilverspoons.model;

import java.io.Serializable;

public class PersonModel implements Serializable {
    public String id;
    public String username;
    public String email;
    public String pass;

    public String phno;

    public String pic;

    public PersonModel(String id, String username, String email, String pass, String phno, String pic) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.pass = pass;
        this.phno = phno;
        this.pic = pic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}