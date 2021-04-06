package com.example.oop_project.Customer;

public class model
{
    String img,name;

    public model() {
    }

    public model(String img,String name) {
        this.img=img;
        this.name=name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
