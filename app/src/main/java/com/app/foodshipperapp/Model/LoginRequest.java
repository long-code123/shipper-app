package com.app.foodshipperapp.Model;

public class LoginRequest {
    private String shipperName;
    private String password;

    public LoginRequest(String shipperName, String password) {
        this.shipperName = shipperName;
        this.password = password;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
