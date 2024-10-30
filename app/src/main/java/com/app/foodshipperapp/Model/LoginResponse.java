package com.app.foodshipperapp.Model;

public class LoginResponse {
    private String token;
    private Shipper shipper;

    public LoginResponse() {
    }

    public LoginResponse(String token, Shipper shipper) {
        this.token = token;
        this.shipper = shipper;
    }

    // Getter và Setter cho token
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    // Getter và Setter cho shipper
    public Shipper getShipper() {
        return shipper;
    }

    public void setShipper(Shipper shipper) {
        this.shipper = shipper;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "token='" + token + '\'' +
                ", shipper=" + shipper +
                '}';
    }
}
