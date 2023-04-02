package com.example.appmobileclothes.Home;

import java.util.ArrayList;

public class BannerData {
    public static ArrayList<Banner> generatePhotoData() {
        ArrayList<Banner> banners = new ArrayList<Banner>();
//        banners.add(new Banner(0, "https://firebasestorage.googleapis.com/v0/b/mobileappclothes.appspot.com/o/banner-img%2Fbanner-1.png?alt=media&token=aa1d4366-ae13-408f-958f-b79e61017105"));
//        banners.add(new Banner(1, "https://firebasestorage.googleapis.com/v0/b/mobileappclothes.appspot.com/o/banner-img%2Fbanner-2.png?alt=media&token=71301e82-33ad-48a5-9b55-94f2d2c0d30c"));
//        banners.add(new Banner(2, "https://firebasestorage.googleapis.com/v0/b/mobileappclothes.appspot.com/o/banner-img%2Fbanner-3.png?alt=media&token=1dfff7f1-66dc-4894-ad54-2ca53eca2415"));
        banners.add(new Banner(0, "banner-1.png"));
        banners.add(new Banner(1, "banner-2.png"));
        banners.add(new Banner(2, "banner-3.png"));

        return banners;
    }


    public static Banner getPhotoFromId(int id) {
        ArrayList<Banner> banners = new ArrayList<Banner>();

        for (int i = 0; i < banners.size(); i++) {
            if (banners.get(i).getId() == id) {
                return banners.get(i);
            }
        }
        return null;
    }
}
