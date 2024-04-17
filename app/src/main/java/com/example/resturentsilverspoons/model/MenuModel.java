package com.example.resturentsilverspoons.model;

import java.io.Serializable;

public class MenuModel implements Serializable {


    public String id,dishname,dishdescription,ingredient,price,pic1,ctype,fav,created_at,updated_at;

    public MenuModel(String id, String dishname, String dishdescription, String ingredient, String price, String pic1, String ctype, String fav, String created_at, String updated_at) {
        this.id = id;
        this.dishname = dishname;
        this.dishdescription = dishdescription;
        this.ingredient = ingredient;
        this.price = price;
        this.pic1 = pic1;
        this.ctype = ctype;
        this.fav = fav;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDishname() {
        return dishname;
    }

    public void setDishname(String dishname) {
        this.dishname = dishname;
    }

    public String getDishdescription() {
        return dishdescription;
    }

    public void setDishdescription(String dishdescription) {
        this.dishdescription = dishdescription;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getFav() {
        return fav;
    }

    public void setFav(String fav) {
        this.fav = fav;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
