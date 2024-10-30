package com.app.foodshipperapp.Model;

public class ReviewShipper {
    private Double rating;
    private String comment;
    private int userId;
    private int shipperId;

    public ReviewShipper() {
    }

    public ReviewShipper(Double rating, String comment, int userId, int shipperId) {
        this.rating = rating;
        this.comment = comment;
        this.userId = userId;
        this.shipperId = shipperId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShipperId() {
        return shipperId;
    }

    public void setShipperId(int shipperId) {
        this.shipperId = shipperId;
    }

    @Override
    public String toString() {
        return "ReviewShipper{" +
                "rating=" + rating +
                ", comment='" + comment + '\'' +
                ", userId=" + userId +
                ", shipperId=" + shipperId +
                '}';
    }
}
