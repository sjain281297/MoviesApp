package com.example.shubhamjain.moviesapp2;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by SHUBHAM JAIN on 29-06-2016.
 */
public class ImageAdapter2 extends ArrayAdapter<Bitmap> {

    Context context;
    ArrayList<Bitmap> imgData;

    public ImageAdapter2(Context context,int resource, ArrayList<Bitmap> imgData) {
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
        imageViewTrail.setImageBitmap(imgData.get(position));


        return v;
    }
}
