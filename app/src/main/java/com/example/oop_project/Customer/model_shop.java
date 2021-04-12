package com.example.oop_project.Customer;

public class model_shop {
    Integer latitude, longitude ,price, quantity;
    String rname;

    public model_shop() {
    }


    public model_shop(Integer latitude, Integer longitude, Integer price, Integer quantity, String rname) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.price = price;
        this.quantity = quantity;
        this.rname = rname;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public Integer getPrice() {
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
