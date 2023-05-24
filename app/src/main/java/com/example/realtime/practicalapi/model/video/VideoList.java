package com.example.realtime.practicalapi.model.video;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoList {


    @SerializedName("ID")
    @Expose
    private String id;
    @SerializedName("EffectName")
    @Expose
    private String effectName;
    @SerializedName("EffectView")
    @Expose
    private String effectView;
    @SerializedName("EffectUsed")
    @Expose
    private String effectUsed;
    @SerializedName("EffectEdit")
    @Expose
    private String effectEdit;
    @SerializedName("EffectThumb")
    @Expose
    private String effectThumb;
    @SerializedName("EffectSample")
    @Expose
    private String effectSample;
    @SerializedName("EffectZip")
    @Expose
    private String effectZip;
    @SerializedName("VideoW")
    @Expose
    private String videoW;
    @SerializedName("VideoH")
    @Expose
    private String videoH;
    @SerializedName("VideoD")
    @Expose
    private String videoD;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("isActive")
    @Expose
    private Boolean isActive;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEffectName() {
        return effectName;
    }

    public void setEffectName(String effectName) {
        this.effectName = effectName;
    }

    public String getEffectView() {
        return effectView;
    }

    public void setEffectView(String effectView) {
        this.effectView = effectView;
    }

    public String getEffectUsed() {
        return effectUsed;
    }

    public void setEffectUsed(String effectUsed) {
        this.effectUsed = effectUsed;
    }

    public String getEffectEdit() {
        return effectEdit;
    }

    public void setEffectEdit(String effectEdit) {
        this.effectEdit = effectEdit;
    }

    public String getEffectThumb() {
        return effectThumb;
    }

    public void setEffectThumb(String effectThumb) {
        this.effectThumb = effectThumb;
    }

    public String getEffectSample() {
        return effectSample;
    }

    public void setEffectSample(String effectSample) {
        this.effectSample = effectSample;
    }

    public String getEffectZip() {
        return effectZip;
    }

    public void setEffectZip(String effectZip) {
        this.effectZip = effectZip;
    }

    public String getVideoW() {
        return videoW;
    }

    public void setVideoW(String videoW) {
        this.videoW = videoW;
    }

    public String getVideoH() {
        return videoH;
    }

    public void setVideoH(String videoH) {
        this.videoH = videoH;
    }

    public String getVideoD() {
        return videoD;
    }

    public void setVideoD(String videoD) {
        this.videoD = videoD;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}