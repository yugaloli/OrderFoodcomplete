package com.brillicaservices.orderfood.Model;

/**
 * Created by user on 7/5/2018.
 */

public class Category {
    private String Name;
    private String Image;
    private String Price;
    private String Description;
    private String ID;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Category(String name, String image, String price, String description) {
        Name = name;
        Image = image;
        Price = price;
        Description = description;
    }

    public Category(){}

    public Category(String name, String image, String price) {
        Name = name;
        Image = image;
        Price = price;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
