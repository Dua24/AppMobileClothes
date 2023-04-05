package com.example.appmobileclothes.Models;

import java.util.Date;

public class Order {
    private String id;
    private Date date;
    private String user_id;
    private int total;

    public Order() {
    }

    public Order(String id, Date date, String user_id, int total) {
        this.id = id;
        this.date = date;
        this.user_id = user_id;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

