package com.example.appmobileclothes.Cart;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

public class CartData extends AsyncTask<String,String,String> {
    private Context context;
    private ListView listView;
    public static CartList data;

    public CartData(Context context, ListView listView) {
        this.context = context;
        this.listView = listView;
    }

    public static Cart getPhotoFromId(int id) {
        for (int i = 0; i < data.getArticles().size(); i++) {
            if (data.getArticles().get(i).getArticle_id() == id)
                return data.getArticles().get(i);
        }
        return null;
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try{
            URL url = new URL("https://raw.githubusercontent.com/thanhdnh/json/main/products.json");
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null){
                buffer.append(line+"\n");
            }
            return buffer.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                if (connection != null){
                    connection.disconnect();
                }
                if (reader != null){
                    reader.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Gson gson = new Gson();
        data = gson.fromJson(result, (Type) CartList.class);
        CartAdapter adapter = new CartAdapter(data.getArticles(), context);
        listView.setAdapter(adapter);
    }
}
