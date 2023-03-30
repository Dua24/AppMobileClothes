package com.example.appmobileclothes.Category;

import java.util.ArrayList;

public class CategoryData {
    public static ArrayList<Category> generatePhotoData() {
        ArrayList<Category> categories = new ArrayList<Category>();
        categories.add(new Category(0, "https://cdn-icons-png.flaticon.com/512/4488/4488007.png", "Tops"));
        categories.add(new Category(1, "https://cdn-icons-png.flaticon.com/512/4488/4488090.png", "Jackets"));
        categories.add(new Category(2, "https://cdn-icons-png.flaticon.com/512/4488/4488032.png", "Bottoms"));
        categories.add(new Category(3, "https://cdn-icons-png.flaticon.com/512/4488/4488070.png", "Shoes"));
        categories.add(new Category(4, "https://cdn-icons-png.flaticon.com/512/4488/4488094.png", "Accessories"));

        return categories;
    }
    public static Category getPhotoFromId(int id){
        ArrayList<Category> categories = generatePhotoData();
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == id) {
                return categories.get(i);
            }
        }
        return null;
    }
}