package com.example.appmobileclothes.Home;

public class Category {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    private String source_photo;

    public String getSource_photo() {
        return source_photo;
    }

    public void setSource_photo(String newSource_photo) {
        this.source_photo = newSource_photo;
    }

    private String category_name;

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String newTitle_photo) {
        this.category_name = newTitle_photo;
    }

    public Category(int id, String source_photo, String category_name) {
        this.id = id;
        this.source_photo = source_photo;
        this.category_name = category_name;
    }
}
