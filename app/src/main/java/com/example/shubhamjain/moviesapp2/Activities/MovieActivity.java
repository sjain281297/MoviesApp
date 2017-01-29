package com.example.shubhamjain.moviesapp2.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shubhamjain.moviesapp2.Network.DescBackgroundTask;
import com.example.shubhamjain.moviesapp2.Network.IdDownloadTask;
import com.example.shubhamjain.moviesapp2.R;
import com.example.shubhamjain.moviesapp2.Network.ReviewBackground;
import com.example.shubhamjain.moviesapp2.Adapters.TrailerAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieActivity extends AppCompatActivity {
    ImageView BackImage;
    TrailerAdapter adapter;
    ArrayList<String> Data;
    TextView tv;
    GridView trailerView;
    Button b;
    TextView t;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movieview);
        tv=(TextView) findViewById(R.id.Description);
        t=(TextView)findViewById(R.id.reviews);
        tv.setText(getIntent().getStringExtra("Overview"));
        BackImage=(ImageView)findViewById(R.id.MovieBackground);
        trailerView=(GridView)findViewById(R.id.trailers);
        Picasso.with(this).load("http://image.tmdb.org/t/p/w342/"+getIntent().getStringExtra("Backdrop")).into(BackImage);
        b=(Button) findViewById(R.id.IMDB);
        Data=new ArrayList<>();
        adapter=new TrailerAdapter(this,Data);
        trailerView.setAdapter(adapter);
        final int movie_id=getIntent().getIntExtra("image_id",-1);
        ReviewBackground r=new ReviewBackground(this);
        r.execute("https://api.themoviedb.org/3/movie/"+ movie_id +"/reviews?api_key=1e6ceb97eccd94afdac1523c418cc2fd");





        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IdDownloadTask task=new IdDownloadTask(MovieActivity.this);
                task.execute("http://api.themoviedb.org/3/movie/"+movie_id+"?api_key=1e6ceb97eccd94afdac1523c418cc2fd");
            }
        });


        if(movie_id!=-1) {
            DescBackgroundTask t = new DescBackgroundTask(this);
            t.execute("https://api.themoviedb.org/3/movie/"+movie_id+"/videos?api_key=1e6ceb97eccd94afdac1523c418cc2fd");


        }


    }


    public void processResults(String[] trailerImage) {
        if(trailerImage==null){
            return;
        }
        Data.clear();

        for(String b:trailerImage){
            Data.add(b);
        }

        adapter.notifyDataSetChanged();
    }

    public void onProcessResults2(String s) {
        Intent i=new Intent();
        i.setAction(Intent.ACTION_VIEW);
        i.setData(Uri.parse("http://www.imdb.com/title/"+s));
        startActivity(i);
    }

    public void processResults3(String[] strings) {

        if(strings==null){
            return;
        }

        t.clearComposingText();

        for(String s:strings){
            t.append(s);

        }
    }
}
