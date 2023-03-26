package com.example.appmobileclothes.Product;

public class Product {
    private String product_id;
    private String product_name;
    private String product_type;
    private String product_quantity;
    private double product_price;

    public Product(String product_id, String product_name, String product_type, String product_quantity, double product_price) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_type = product_type;
        this.product_quantity = product_quantity;
        this.product_price = product_price;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(String product_quantity) {
        this.product_quantity = product_quantity;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }
}
