package com.example.resturentsilverspoons.model;

public class bannermodel {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBtitle() {
        return btitle;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public bannermodel(String id, String btitle, String pic, String created_at, String updated_at, String status) {
        this.id = id;
        this.btitle = btitle;
        this.pic = pic;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.status = status;
    }

    public  String id,btitle,pic,created_at,updated_at,status;
}
