package com.example.appmobileclothes.Category;

import java.util.ArrayList;

public class CategoryData {
    public static ArrayList<Category> generatePhotoData() {
        ArrayList<Category> categories = new ArrayList<Category>();
        categories.add(new Category(0, "https://cdn-icons-png.flaticon.com/512/4488/4488007.png", "T-shirts"));
        categories.add(new Category(1, "https://cdn-icons-png.flaticon.com/512/4488/4488016.png", "Shirts"));
        categories.add(new Category(2, "https://cdn-icons-png.flaticon.com/512/4488/4488014.png", "Outwears"));
        categories.add(new Category(3, "https://cdn-icons-png.flaticon.com/512/4488/4488032.png", "Pants"));
        categories.add(new Category(4, "https://cdn-icons-png.flaticon.com/512/4488/4488027.png", "Shorts"));
        categories.add(new Category(5, "https://cdn-icons-png.flaticon.com/512/4488/4488070.png", "Shoes"));
        categories.add(new Category(6, "https://cdn-icons-png.flaticon.com/512/4488/4488094.png", "Accessories"));

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