package com.example.oop_project.Main.Retailer;

public class Orders_sub {
    String cost,ddate,dname,dnumber,pname,price,quantity,shop,status;


    public Orders_sub() {
    }

    public Orders_sub(String cost, String ddate, String dname, String dnumber, String pname,String price, String quantity,String shop, String status) {
        this.cost = cost;
        this.pname = pname;
        this.quantity=quantity;
        this.price=price;
        this.shop=shop;
        this.ddate=ddate;
        this.dname=dname;
        this.dnumber=dnumber;
        this.status=status;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDdate() {
        return ddate;
    }

    public void setDdate(String ddate) {
        this.ddate = ddate;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDnumber() {
        return dnumber;
    }

    public void setDnumber(String dnumber) {
        this.dnumber = dnumber;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }
}
