package com.app.foodshipperapp.API;

import com.app.foodshipperapp.Config.Constants;
import com.app.foodshipperapp.Model.Order;
import com.app.foodshipperapp.Model.RegisterRequest;
import com.app.foodshipperapp.Model.RegisterResponse;
import com.app.foodshipperapp.Model.ReviewShipper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ShipperAPI {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ShipperAPI shipperAPI = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ShipperAPI.class);

    // API để lấy đánh giá của shipper
    @GET("/api/v1/shipper/{id}/reviews")
    Call<List<ReviewShipper>> getReviewsByShipper(@Path("id") int shipperId);

    // API để lấy đơn hàng đang giao của shipper
    @GET("/api/v1/shipper/{id}/orders/shipping")
    Call<List<Order>> getOrderByShipperShipping(@Path("id") int shipperId);

    // API để lấy đơn hàng đã hoàn thành của shipper
    @GET("/api/v1/shipper/{id}/orders/done")
    Call<List<Order>> getOrderByShipperDone(@Path("id") int shipperId);
}
