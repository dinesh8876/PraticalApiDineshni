package com.example.realtime.practicalapi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Categary {

    @SerializedName("catid")
    @Expose
    private String catid;
    @SerializedName("catname")
    @Expose
    private String catname;
    @SerializedName("catimg")
    @Expose
    private String catimg;

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public String getCatimg() {
        return catimg;
    }

    public void setCatimg(String catimg) {
        this.catimg = catimg;
    }

}
