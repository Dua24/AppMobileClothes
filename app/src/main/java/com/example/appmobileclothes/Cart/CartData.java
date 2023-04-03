//package com.example.appmobileclothes.Cart;
//
//import android.content.Context;
//import android.os.AsyncTask;
//import android.view.View;
//import android.widget.GridView;
//import android.widget.ListView;
//
//import androidx.annotation.NonNull;
//
//import com.example.appmobileclothes.Home.Banner;
//import com.example.appmobileclothes.Home.BannerAdapter;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.gson.Gson;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.lang.reflect.Type;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//
//public class CartData {
//    private Context context;
//    private ListView listView;
//    public static CartList data;
//
//    public CartData(Context context, ListView listView) {
//        this.context = context;
//        this.listView = listView;
//    }
//
//    public static Cart getPhotoFromId(int id) {
//        for (int i = 0; i < data.getArticles().size(); i++) {
//            if (data.getArticles().get(i).getArticle_id() == id)
//                return data.getArticles().get(i);
//        }
//        return null;
//    }
//
//    public static FirebaseDatabase database;
//    public static DatabaseReference dbRef;
//    public static ArrayList<Cart> banners;
//
//    public static void getData(CartAdapter cartAdapter){
//        database = FirebaseDatabase.getInstance();
//        dbRef = database.getReference("Product");
//        banners = new ArrayList<>();
//
//        dbRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    Cart banner = dataSnapshot.getValue(Cart.class);
//                    banners.add(banner);
//                }
//                cartAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
//
////    @Override
////    protected String doInBackground(String... strings) {
////        HttpURLConnection connection = null;
////        BufferedReader reader = null
////        try{
////            URL url = new URL("https://raw.githubusercontent.com/thanhdnh/json/main/products.json");
////            connection = (HttpURLConnection) url.openConnection();
////            connection.connect();
////            InputStream stream = connection.getInputStream();
////            reader = new BufferedReader(new InputStreamReader(stream));
////            StringBuffer buffer = new StringBuffer();
////            String line = "";
////            while ((line = reader.readLine()) != null){
////                buffer.append(line+"\n");
////            }
////            return buffer.toString();
////        } catch (IOException e) {
////            throw new RuntimeException(e);
////        } finally {
////            try{
////                if (connection != null){
////                    connection.disconnect();
////                }
////                if (reader != null){
////                    reader.close();
////                }
////            } catch (IOException e) {
////                throw new RuntimeException(e);
////            }
////        }
////    }
//
//    @Override
//    protected void onPostExecute(String result) {
////        super.onPostExecute(result);
////        Gson gson = new Gson();
////        data = gson.fromJson(result, (Type) CartList.class);
//
//        getData();
//        CartAdapter adapter = new CartAdapter(data.getArticles(), context);
//        listView.setAdapter(adapter);
//    }
//
//
//}
