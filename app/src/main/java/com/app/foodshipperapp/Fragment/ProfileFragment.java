package com.app.foodshipperapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.foodshipperapp.API.LoginAPI;
import com.app.foodshipperapp.Activity.LoginActivity;
import com.app.foodshipperapp.Model.Shipper;
import com.app.foodshipperapp.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    ImageView imageProfile;
    TextView textViewNameProf, textViewGmailProf, textViewDob, textViewPhone, textViewAddress;
    Button btnLogout;
    private LoginAPI apiService;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageProfile = view.findViewById(R.id.imageProfile);
        textViewNameProf = view.findViewById(R.id.textViewNameProf);
        textViewGmailProf = view.findViewById(R.id.textViewGmailProf);
        textViewDob = view.findViewById(R.id.textViewDob);
        textViewPhone = view.findViewById(R.id.textViewPhone);
        textViewAddress = view.findViewById(R.id.textViewAddress);
        btnLogout = view.findViewById(R.id.btnLogout);
        apiService = LoginAPI.loginAPI;

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xử lý đăng xuất
                logoutUser();
            }
        });

        // Lấy thông tin shipper từ SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("ShipperAppPrefs", Context.MODE_PRIVATE);
        String shipperJson = sharedPreferences.getString("shipper_info", null);

        if (shipperJson != null) {
            // Chuyển chuỗi JSON thành đối tượng Shipper sử dụng Gson
            Gson gson = new Gson();
            Shipper shipper = gson.fromJson(shipperJson, Shipper.class);

            // Hiển thị thông tin shipper lên UI
            Picasso.get().load(shipper.getShipperImage()).into(imageProfile);
            textViewNameProf.setText(shipper.getShipperName());
            textViewGmailProf.setText(shipper.getEmail());
            textViewDob.setText(shipper.getDateOfBirth());
            textViewPhone.setText(shipper.getPhoneNumber());
            textViewAddress.setText(shipper.getAddress());
        } else {
            Toast.makeText(getActivity(), "Failed to load user information", Toast.LENGTH_SHORT).show();
        }
    }

    private void logoutUser() {
        // Xóa token đã lưu
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyAppPrefs", getContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("jwt_token");
        editor.apply();

        // Chuyển người dùng về màn hình đăng nhập
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        getActivity().finish();
    }
}
