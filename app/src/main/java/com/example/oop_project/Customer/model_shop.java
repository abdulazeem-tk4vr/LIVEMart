package com.example.oop_project.Customer;

public class model_shop {
    String latitude, longitude ;
    String quantity,price;
    String rname;

    public model_shop() {
    }


    public model_shop(String latitude, String longitude, String price, String quantity, String rname) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.price = price;
        this.quantity = quantity;
        this.rname = rname;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }
}
