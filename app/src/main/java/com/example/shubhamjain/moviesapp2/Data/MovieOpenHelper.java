package com.example.shubhamjain.moviesapp2.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SHUBHAM JAIN on 03-07-2016.
 */
public class MovieOpenHelper extends SQLiteOpenHelper {
    public static final String MOVIE_TABLE="movies";
    public static final String MOVIE_DES="movie_des";
    public static final String MOVIE_BACK="movie_back";
    public static final String MOVIE_IMAGE="image_path";
    public static final String MOVIE_ID="_ID";


    public MovieOpenHelper(Context context) {
        super(context,MOVIE_TABLE,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +  MOVIE_TABLE + " ( " + MOVIE_ID + " INTEGER PRIMARY KEY ," +
                MOVIE_IMAGE + " TEXT, " + MOVIE_BACK + " TEXT, " + MOVIE_DES + " TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
