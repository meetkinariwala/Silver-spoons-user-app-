package com.example.resturentsilverspoons.model;

public class CouponModel {

    public  String  id,couponcode,coupontitle,coupondesc,coupondiscount,pic,created_at,updated_at;

    public CouponModel(String id, String couponcode, String coupontitle, String coupondesc, String coupondiscount, String pic, String created_at, String updated_at) {
        this.id = id;
        this.couponcode = couponcode;
        this.coupontitle = coupontitle;
        this.coupondesc = coupondesc;
        this.coupondiscount = coupondiscount;
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

    public String getCouponcode() {
        return couponcode;
    }

    public void setCouponcode(String couponcode) {
        this.couponcode = couponcode;
    }

    public String getCoupontitle() {
        return coupontitle;
    }

    public void setCoupontitle(String coupontitle) {
        this.coupontitle = coupontitle;
    }

    public String getCoupondesc() {
        return coupondesc;
    }

    public void setCoupondesc(String coupondesc) {
        this.coupondesc = coupondesc;
    }

    public String getCoupondiscount() {
        return coupondiscount;
    }

    public void setCoupondiscount(String coupondiscount) {
        this.coupondiscount = coupondiscount;
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
