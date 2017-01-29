package com.example.shubhamjain.moviesapp2.Network;

import android.os.AsyncTask;

import com.example.shubhamjain.moviesapp2.Activities.MovieActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by SHUBHAM JAIN on 28-06-2016.
 */
public class DescBackgroundTask extends AsyncTask<String,Void,String[]> {




    MovieActivity Mactivity;

    public DescBackgroundTask(MovieActivity activity) {
        Mactivity=activity;
    }

    private String[] parseJson(String json){
        try {
            JSONObject obj1=new JSONObject(json);
            JSONArray trailers=obj1.getJSONArray("results");
            String[] imgoutput=new String[trailers.length()];
            for(int i=0;i<trailers.length();i++){
                JSONObject trailerObject=trailers.getJSONObject(i);
                String key=trailerObject.getString("key");
                String urlString="http://img.youtube.com/vi/"+key+"/hqdefault.jpg";
                imgoutput[i]=urlString;
            }
            return imgoutput;
        } catch (JSONException e) {
            return null;
        }
    }





    @Override
    protected String[] doInBackground(String... params) {
        if(params.length==0){
            return null;
        }
        StringBuffer buffer=new StringBuffer();
        try {
            URL url=new URL(params[0]);
            HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            if(inputStream==null){
                return null;
            }
            Scanner s=new Scanner(inputStream);
            while (s.hasNext()){
                buffer.append(s.nextLine());
            }
        } catch (MalformedURLException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
        return parseJson(buffer.toString());
    }

    @Override
    protected void onPostExecute(String[] urls) {
        Mactivity.processResults(urls);
    }

}
