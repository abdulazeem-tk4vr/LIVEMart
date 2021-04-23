package com.example.oop_project.Main.Retailer;

public class TransModel_sub  {
    String cost,ddate,dname,dnumber,pname,quantity,status,username;


    public TransModel_sub() {
    }

    public TransModel_sub(String cost,String ddate,String dname,String dnumber,String pname,String quantity,String status,String username) {
        this.cost = cost;
        this.pname = pname;
        this.quantity=quantity;
        this.username=username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
