package com.app.foodshipperapp.API;

import com.app.foodshipperapp.Config.Constants;
import com.app.foodshipperapp.Model.Order;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OrderAPI {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    OrderAPI orderAPI = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(OrderAPI.class);

    @PUT("/api/v1/shipper/orders/{id}/shipping")
    Call<Order> shippingOrder(@Path("id") String orderId, @Body Order order);

    @PUT("/api/v1/shipper/orders/{id}/done")
    Call<Order> doneOrder(@Path("id") String orderId, @Body Order order);

    @GET("/api/v1/shipper/orders")
    Call<List<Order>> getOrderForShipper();
}
