package com.example.realtime.practicalapi.api;

import com.example.realtime.practicalapi.model.Categary;
import com.example.realtime.practicalapi.model.MainList;
import com.example.realtime.practicalapi.model.video.MainVideoList;
import com.example.realtime.practicalapi.model.video.VideoList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitAPI {

    @POST("prac-category.php")
    Call<MainList> createPost();

    @POST("prac-video.php")
    @FormUrlEncoded
    Call<MainVideoList> getVideo(@Field("Pge") int Pge,@Field("Cat") int Cat);
}
