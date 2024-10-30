package com.app.foodshipperapp.API;

import com.app.foodshipperapp.Config.Constants;
import com.app.foodshipperapp.Model.LoginRequest;
import com.app.foodshipperapp.Model.LoginResponse;
import com.app.foodshipperapp.Model.Shipper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LoginAPI {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    // Creating Retrofit instance
    LoginAPI loginAPI = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(LoginAPI.class);

    // API for shipper login
    @POST("/api/v1/shipper/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    // API for getting shipper details (me)
    @GET("/api/v1/shipper/me")
    Call<Shipper> getCurrentShipper(@Header("Authorization") String token);
}
