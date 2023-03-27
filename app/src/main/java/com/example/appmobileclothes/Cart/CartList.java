package com.example.appmobileclothes.Cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CartList {
    @SerializedName("articles")
    @Expose
    private ArrayList<Cart> articles;

    public ArrayList<Cart> getArticles() {
        return articles;
    }

    public CartList(ArrayList<Cart> articles) {
        this.setArticles(articles);
    }

    private void setArticles(ArrayList<Cart> articles) {
        this.articles = articles;
    }
}
