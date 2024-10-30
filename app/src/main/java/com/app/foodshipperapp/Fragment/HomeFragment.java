package com.app.foodshipperapp.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.foodshipperapp.API.OrderAPI;
import com.app.foodshipperapp.API.ShipperAPI;
import com.app.foodshipperapp.Adapter.OrderAdapter;
import com.app.foodshipperapp.Model.Order;
import com.app.foodshipperapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RecyclerView orderView;
    private OrderAdapter orderAdapter;
    private ImageView btnRefresh;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        orderView = view.findViewById(R.id.orderView);
        btnRefresh = view.findViewById(R.id.btnRefresh); // Kết nối với ImageView refresh

        // Thiết lập LayoutManager cho RecyclerView
        orderView.setLayoutManager(new LinearLayoutManager(getContext()));
        orderView.setHasFixedSize(true);

        loadOrders(); // Gọi API để tải danh sách đơn hàng

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadOrders();
            }
        });

        return view;
    }

    private void loadOrders() {
        OrderAPI.orderAPI.getOrderForShipper().enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Order> orders = response.body();
                    orderAdapter = new OrderAdapter(getContext(), orders);
                    orderView.setAdapter(orderAdapter);
                } else {
                    Toast.makeText(getContext(), "Failed to load orders.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}