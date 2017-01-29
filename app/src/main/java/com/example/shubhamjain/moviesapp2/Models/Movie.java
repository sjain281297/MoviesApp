package com.example.shubhamjain.moviesapp2.Models;

import android.graphics.Bitmap;

/**
 * Created by SHUBHAM JAIN on 28-06-2016.
 */
public class Movie {
    String image;
    int id;
    String description;
    String BackDrop;
    int vote_count;

    public Movie(String image, int id, String description, String BackDrop, int vote_count) {
        this.image = image;
        this.id = id;
        this.description=description;
        this.BackDrop=BackDrop;
        this.vote_count=vote_count;

    }

    public String getBackDrop() {
        return BackDrop;
    }

    public void setBackDrop(String backDrop) {
        BackDrop = backDrop;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }
}
