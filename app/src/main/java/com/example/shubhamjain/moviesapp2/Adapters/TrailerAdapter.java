package com.example.shubhamjain.moviesapp2.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by SHUBHAM JAIN on 29-06-2016.
 */
public class TrailerAdapter extends BaseAdapter {

    Context context;
    ArrayList<String > mData;

    public TrailerAdapter(Context context,ArrayList<String> mData ) {
        this.context = context;
        this.mData=mData;
    }

    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView tv;
        if(convertView==null){
            tv=new ImageView(context);
            tv.setLayoutParams(new GridView.LayoutParams(190,190));
        }else{
            tv=(ImageView) convertView;
        }
        Picasso.with(context).load(mData.get(position)).into(tv);

        return tv;

    }
}
