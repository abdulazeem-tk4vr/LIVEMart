package com.example.oop_project.Main.Retailer;

public class TransModel {
    TransModel_sub t;
    public TransModel() {
    }

    public TransModel(TransModel_sub t) {
        this.t=t;
    }

    public TransModel_sub getT() {
        return t;
    }

    public void setT(TransModel_sub t) {
        this.t = t;
    }
}
