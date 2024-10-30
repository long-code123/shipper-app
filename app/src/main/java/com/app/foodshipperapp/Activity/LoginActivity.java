package com.app.foodshipperapp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.foodshipperapp.API.LoginAPI;
import com.app.foodshipperapp.MainActivity;
import com.app.foodshipperapp.Model.LoginRequest;
import com.app.foodshipperapp.Model.LoginResponse;
import com.app.foodshipperapp.Model.Shipper;
import com.app.foodshipperapp.R;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class LoginActivity extends AppCompatActivity {
    EditText edtShipperName, edtPassword;
    Button btnLogin;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtShipperName = findViewById(R.id.edtShipperName);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginShipper();
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loginShipper() {
        String shipperName = edtShipperName.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (shipperName.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Please enter shipper name and password.", Toast.LENGTH_SHORT).show();
            return;
        }

        LoginRequest loginRequest = new LoginRequest(shipperName, password);

        LoginAPI.loginAPI.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();

                    if (loginResponse != null) {
                        // Save the token to SharedPreferences
                        Log.e("Login", "Token: " + loginResponse.getToken());
                        SharedPreferences sharedPreferences = getSharedPreferences("ShipperAppPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("jwt_token", loginResponse.getToken());

                        // Chuyển đổi thông tin shipper sang JSON string để lưu
                        Gson gson = new Gson();
                        String shipperJson = gson.toJson(loginResponse.getShipper());
                        editor.putString("shipper_info", shipperJson);

                        editor.apply();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Log.e("Login", "LoginResponse is null");
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid shipper name or password.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Login failed. Please check your network connection.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}