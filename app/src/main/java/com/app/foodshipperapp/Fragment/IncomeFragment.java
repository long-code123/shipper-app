package com.app.foodshipperapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foodshipperapp.API.ShipperAPI;
import com.app.foodshipperapp.Adapter.OrderDoneAdapter;
import com.app.foodshipperapp.Adapter.ReviewShipperAdapter;
import com.app.foodshipperapp.Model.Order;
import com.app.foodshipperapp.Model.ReviewShipper;
import com.app.foodshipperapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IncomeFragment extends Fragment {

    private RecyclerView orderDoneView;
    private RecyclerView shipperView;
    private OrderDoneAdapter orderDoneAdapter;
    private ReviewShipperAdapter reviewShipperAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income, container, false);

        orderDoneView = view.findViewById(R.id.orderDoneView);
        shipperView = view.findViewById(R.id.shipperView);

        // Thiết lập LayoutManager cho RecyclerView
        orderDoneView.setLayoutManager(new LinearLayoutManager(getContext()));
        shipperView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Lấy dữ liệu đơn hàng đã hoàn thành
        fetchOrderDoneData();
        // Lấy dữ liệu đánh giá của shipper
        fetchShipperReviews();

        return view;
    }

    private void fetchOrderDoneData() {
        // Giả sử bạn có ID của shipper
        int shipperId = 1; // Thay đổi cho phù hợp

        ShipperAPI.shipperAPI.getOrderByShipperDone(shipperId).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Order> orders = response.body();
                    orderDoneAdapter = new OrderDoneAdapter(getContext(), orders);
                    orderDoneView.setAdapter(orderDoneAdapter);
                } else {
                    Toast.makeText(getContext(), "Không có đơn hàng nào", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchShipperReviews() {
        // Giả sử bạn có ID của shipper
        int shipperId = 1; // Thay đổi cho phù hợp

        ShipperAPI.shipperAPI.getReviewsByShipper(shipperId).enqueue(new Callback<List<ReviewShipper>>() {
            @Override
            public void onResponse(Call<List<ReviewShipper>> call, Response<List<ReviewShipper>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ReviewShipper> reviews = response.body();
                    reviewShipperAdapter = new ReviewShipperAdapter(reviews, getContext());
                    shipperView.setAdapter(reviewShipperAdapter);
                } else {
                    Toast.makeText(getContext(), "Không có đánh giá nào", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ReviewShipper>> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
