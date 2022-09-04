package com.example.a4thyearproject;

public class RentModel {

    String image;
    String type;
    String location;
    String price;



    String details;



    RentModel(){

    }



    public RentModel(String type, String location, String price, String image,String details){
        this.location = location;
        this.type = type;
        this.price = price;
        this.image = image;
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public  String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    }
