package com.example.shubhamjain.moviesapp2.Network;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.shubhamjain.moviesapp2.Activities.MainActivity;
import com.example.shubhamjain.moviesapp2.Models.Movie;

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
public class MovieBackgroundTask extends AsyncTask<String,Integer,Movie[]>{

    MainActivity activity;
     private ProgressDialog p;

    public MovieBackgroundTask(MainActivity activity) {
        this.activity = activity;
    }

    //    public interface MovieBackgroundTaskInterFace{
//        void processResults(Movie [] image);
//    }
//    MovieBackgroundTaskInterFace listener;
//
//
//    public MovieBackgroundTask(MovieBackgroundTaskInterFace listener) {
//        this.listener = listener;
//    }

    private Movie[] parseJson(String json){
        try {
            JSONObject obj=new JSONObject(json);
            JSONArray movies=obj.getJSONArray("results");
            Movie[] output=new Movie[movies.length()];
            for(int i=0;i<movies.length();i++){
                JSONObject moviesObject=movies.getJSONObject(i);
                String imagepath=moviesObject.getString("poster_path");
                int idnum=moviesObject.getInt("id");
                String img="http://image.tmdb.org/t/p/w342/"+imagepath;
                String overview=moviesObject.getString("overview");
                String bd=moviesObject.getString("backdrop_path");
                int votes=moviesObject.getInt("vote_count");
                output[i]=new Movie(img,idnum,overview,bd,votes);
            }
            return output;
        } catch (JSONException e) {
            return null;
        }
    }



    @Override
    protected Movie[] doInBackground(String... params) {
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
            int count=0;
            Scanner s=new Scanner(inputStream);
            while (s.hasNext()){
                String line=s.nextLine();
                count+=line.length();
                publishProgress(count);
                buffer.append(line);
            }
        } catch (MalformedURLException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
        return parseJson(buffer.toString());
    }



    @Override
    protected void onPreExecute() {


        p=new ProgressDialog(this.activity);
        p.setTitle("progress");
//        p.setMessage("Downloading");
        p.show();


    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        p.setMessage(values[0] +"bytes downloaded");
    }

    @Override
    protected void onPostExecute(Movie[] bitmaps) {
        p.dismiss();
        activity.processResults(bitmaps);

    }
}
