package com.app.foodshipperapp.Model;

import java.sql.Timestamp;

public class Shipper {
    private int shipperId;
    private String shipperName;
    private String shipperImage;
    private String dateOfBirth;
    private String phoneNumber;
    private String email;
    private String address;
    private String password;
    private Timestamp created_at;
    private Timestamp updated_at;

    public Shipper() {
    }

    public Shipper(int shipperId, String shipperName, String shipperImage, String dateOfBirth, String phoneNumber, String email, String address, String password, Timestamp created_at, Timestamp updated_at) {
        this.shipperId = shipperId;
        this.shipperName = shipperName;
        this.shipperImage = shipperImage;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.password = password;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getShipperId() {
        return shipperId;
    }

    public void setShipperId(int shipperId) {
        this.shipperId = shipperId;
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

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}
