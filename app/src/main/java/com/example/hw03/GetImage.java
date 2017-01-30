package com.example.hw03;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class GetImage extends AsyncTask<String,Void,Bitmap> {
    IData activity;
    Context context;
    View view;

    public GetImage(Context context, View view) {
        this.context=context;
        this.view=view;
        this.activity= (IData)context;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {

            activity.sendData(bitmap);
            //ImageView img= (ImageView) findViewById


    }

    @Override
    protected Bitmap doInBackground(String... params) {
        InputStream in=null;
        try {
            URL url=new URL(params[0]);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            in=con.getInputStream();
            Bitmap image= BitmapFactory.decodeStream(in);
            return image;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(in!=null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
