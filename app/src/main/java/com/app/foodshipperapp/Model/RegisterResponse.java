package com.app.foodshipperapp.Model;

public class RegisterResponse {
    private Shipper shipper;

    public RegisterResponse(Shipper user) {
        this.shipper = shipper;
    }

    public Shipper getUser() {
        return shipper;
    }

    public void setUser(Shipper user) {
        this.shipper = shipper;
    }
}
