package com.app.foodshipperapp.Model;

public class RegisterRequest {
    private String shipperName;
    private String shipperImage;
    private String dateOfBirth;
    private String phoneNumber;
    private String email;
    private String address;
    private String password;

    public RegisterRequest() {
    }

    public RegisterRequest(String shipperName, String shipperImage, String dateOfBirth, String phoneNumber, String email, String address, String password) {
        this.shipperName = shipperName;
        this.shipperImage = shipperImage;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.password = password;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public String getShipperImage() {
        return shipperImage;
    }

    public void setShipperImage(String shipperImage) {
        this.shipperImage = shipperImage;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
