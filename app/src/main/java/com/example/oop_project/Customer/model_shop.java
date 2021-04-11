package com.example.oop_project.Customer;

public class model_shop {
    String price,quantity,rname;
    public model_shop() {
    }

    public model_shop(String price,String quantity,String rname) {
        this.rname = rname;
        this.price = price;
        this.quantity = quantity;
    }
    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
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
}
