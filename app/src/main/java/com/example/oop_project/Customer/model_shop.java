package com.example.oop_project.Customer;

public class model_shop {
    double latitude, longitude ;
    Integer quantity,price;
    String rname;

    public model_shop() {
    }


    public model_shop(double latitude, double longitude, int price, Integer quantity, String rname) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.price = price;
        this.quantity = quantity;
        this.rname = rname;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }
}
