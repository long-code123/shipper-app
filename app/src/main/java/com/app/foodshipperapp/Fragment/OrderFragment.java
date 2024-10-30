package com.app.foodshipperapp.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foodshipperapp.API.ShipperAPI;
import com.app.foodshipperapp.Adapter.OrderShippingAdapter;
import com.app.foodshipperapp.Model.Order;
import com.app.foodshipperapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderFragment extends Fragment {

    private RecyclerView recyclerView;
    private OrderShippingAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewOrders);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadOrders();  // Gọi API để lấy danh sách đơn hàng

        return view;
    }

    private void loadOrders() {
        int shipperId = 1;
        ShipperAPI.shipperAPI.getOrderByShipperShipping(shipperId).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Order> orderList = response.body();
                    adapter = new OrderShippingAdapter(getContext(), orderList);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                // Xử lý khi có lỗi
            }
        });
    }
}
