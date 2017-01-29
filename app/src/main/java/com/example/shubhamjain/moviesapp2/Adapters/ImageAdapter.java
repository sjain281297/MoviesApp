package com.example.shubhamjain.moviesapp2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.shubhamjain.moviesapp2.Models.Movie;
import com.example.shubhamjain.moviesapp2.R;

import java.util.ArrayList;

/**
 * Created by SHUBHAM JAIN on 28-06-2016.
 */
public class ImageAdapter extends ArrayAdapter<Movie> {
    Context context;
    ArrayList<Movie> imgData;

    public ImageAdapter(Context context,int resource, ArrayList<Movie> imgData) {
        super(context,resource,imgData);
        this.imgData=imgData;
        this.context = context;

    }

    @Override
    public int getCount() {
        return imgData.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;
        if(v==null){
            v= LayoutInflater.from(this.context).inflate(R.layout.imageview,parent,false);
        }

        ImageView imageViewTrail=(ImageView)v.findViewById(R.id.trailerImageView);



        return v;
    }
}
