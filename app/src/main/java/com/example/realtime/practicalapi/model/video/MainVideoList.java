package com.example.realtime.practicalapi.model.video;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainVideoList {


    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("TotalPage")
    @Expose
    private Integer totalPage;
    @SerializedName("Rows")
    @Expose
    private List<VideoList> rows;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<VideoList> getRows() {
        return rows;
    }

    public void setRows(List<VideoList> rows) {
        this.rows = rows;
    }

}