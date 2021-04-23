package com.example.oop_project.Main.MyItems;

public class model_items_cat {
    String image, pname;


    public model_items_cat() {
    }

    public model_items_cat(String image, String pname) {
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
