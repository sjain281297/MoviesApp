package com.example.shubhamjain.moviesapp2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SHUBHAM JAIN on 28-06-2016.
 */
public class ImageAdapter extends ArrayAdapter<ImageClass> {
    Context context;
    ArrayList<ImageClass> imgData;

    public ImageAdapter(Context context,int resource, ArrayList<ImageClass> imgData) {
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
