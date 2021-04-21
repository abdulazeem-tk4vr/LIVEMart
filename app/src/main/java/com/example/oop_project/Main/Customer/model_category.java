package com.example.oop_project.Main.Customer;

public class model_category {
    String image, pname;


    public model_category() {
    }

    public model_category(String image, String pname) {
        this.image = image;
        this.pname = pname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

}
