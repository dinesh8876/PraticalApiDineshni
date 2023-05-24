package com.example.realtime.practicalapi.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient retrofitCLient;
    private Retrofit retroMain;

    public RetrofitClient() {


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        chain -> {
                            Request original = chain.request();

                            Request.Builder requestBuilder = original.newBuilder()
                                    .method(original.method(), original.body());

                            Request request = requestBuilder.build();
                            return chain.proceed(request);
                        }
                ).build();


        retroMain = new Retrofit.Builder()
                .baseUrl("http://165.22.216.111/Vigo/V1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();


    }

    public static synchronized RetrofitClient getInstance() {
        if (retrofitCLient == null ) {
            retrofitCLient = new RetrofitClient();
        }
        return retrofitCLient;
    }

    public RetrofitAPI getApi() {
        return retroMain.create(RetrofitAPI.class);
    }
}
