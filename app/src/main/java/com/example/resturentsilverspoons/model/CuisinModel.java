package com.example.resturentsilverspoons.model;

import java.security.PublicKey;

public class CuisinModel {

    public String id,cname,pic,created_at,updated_at;

    public CuisinModel(String id, String cname, String pic, String created_at, String updated_at) {
        this.id = id;
        this.cname = cname;
        this.pic = pic;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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
