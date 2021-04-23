package com.example.oop_project.Main.MyItems;

public class model_itemsub {
    String pname , price , quantity;

    public model_itemsub(String pname, String price, String quantity) {
        this.pname = pname;
        this.price = price;
        this.quantity = quantity;
    }

    public model_itemsub() {
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
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
