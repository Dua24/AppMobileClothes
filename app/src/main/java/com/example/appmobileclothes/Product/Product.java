package com.example.appmobileclothes.Product;

public class Product {
    private int id;
    private String name;
    private String gender;
    private int quantity;
    private int price;
    private String image;
    private int category;

    public Product() {
    }

    public Product(int id, String name, String gender, int quantity, int price, String image, int category) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
