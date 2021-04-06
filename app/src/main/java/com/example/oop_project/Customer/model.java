package com.example.oop_project.Customer;

public class model
{
    String image, pname;

    public model() {
    }

    public model(String image, String pname) {
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
