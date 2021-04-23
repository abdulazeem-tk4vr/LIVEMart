package com.example.oop_project.Main.Customer;

public class model_subcategory
{
    String image, pname,status;

    public model_subcategory() {
    }

    public model_subcategory(String image, String pname, String status) {
        this.image = image;
        this.pname = pname;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
