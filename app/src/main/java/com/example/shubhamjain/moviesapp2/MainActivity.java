package com.example.shubhamjain.moviesapp2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity  {
    ArrayList<ImageClass> data;
    MovieAdapter adapter;
    GridView mGrid;
    ListView lv;
    ImageAdapter adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGrid=(GridView) findViewById(R.id.gridView);
        data=new ArrayList<>();
        String urlString="http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=1e6ceb97eccd94afdac1523c418cc2fd";
        MovieBackgroundTask task=new MovieBackgroundTask(this);
        task.execute(urlString);








//        MovieOpenHelper helper=new MovieOpenHelper(this);
//        SQLiteDatabase db=helper.getReadableDatabase();
//        String[] columns={MovieOpenHelper.MOVIE_ID,MovieOpenHelper.MOVIE_DES,MovieOpenHelper.MOVIE_BACK
//        ,MovieOpenHelper.MOVIE_IMAGE};
//        Cursor c=db.query(MovieOpenHelper.MOVIE_TABLE,columns,null,null,null,null,null);
//        while(c.moveToNext()){
//            String imagepath=c.getString(c.getColumnIndex(MovieOpenHelper.MOVIE_IMAGE));
//            int id=c.getInt(c.getColumnIndex(MovieOpenHelper.MOVIE_ID));
//            String descr=c.getString(c.getColumnIndex(MovieOpenHelper.MOVIE_DES));
//            String background=c.getString(c.getColumnIndex(MovieOpenHelper.MOVIE_BACK));
//            ImageClass m=new ImageClass(imagepath,id,descr,background);
//            data.add(m);
//        }





        adapter=new MovieAdapter(this,data);
        mGrid.setAdapter(adapter);

        mGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent();
                i.setClass(MainActivity.this,MovieActivity.class);
                ImageClass ClickedImage=(ImageClass)parent.getAdapter().getItem(position);
                i.putExtra("image_id",ClickedImage.getId());
                i.putExtra("Overview",ClickedImage.getDescription());
                i.putExtra("Backdrop",ClickedImage.getBackDrop());
                startActivity(i);

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.releaseID){
            String urlString="http://api.themoviedb.org/3/discover/movie?sort_by="+ "release_date" +".desc&api_key=1e6ceb97eccd94afdac1523c418cc2fd";
            MovieBackgroundTask task=new MovieBackgroundTask(this);
            task.execute(urlString);

        }
        if(item.getItemId()==R.id.voteID){
//            String urlString="http://api.themoviedb.org/3/discover/movie?sort_by="+ "vote_count" +".desc&api_key=1e6ceb97eccd94afdac1523c418cc2fd";
//            MovieBackgroundTask task=new MovieBackgroundTask(this);
//            task.execute(urlString);
            sort(data);
            adapter.notifyDataSetChanged();

        }

        return true;
    }


    public static void sort(ArrayList<ImageClass> a){
        for(int i=0;i<a.size();i++){
            for(int j=0;j<a.size()-i-1;j++){
                if(a.get(j).getVote_count()>a.get(j+1).getVote_count()){
                    ImageClass temp=a.get(j);
                    a.set(j,a.get(j+1));
                    a.set(j+1,temp);
                }
            }
        }
    }


    public void processResults(ImageClass[] image) {

        data.clear();

        MovieOpenHelper helper=new MovieOpenHelper(this);
        SQLiteDatabase db=helper.getWritableDatabase();
        db.delete(MovieOpenHelper.MOVIE_TABLE,null,null);
        for(ImageClass c:image){
            ContentValues cv=new ContentValues();
            cv.put(MovieOpenHelper.MOVIE_BACK,c.getBackDrop());
            cv.put(MovieOpenHelper.MOVIE_ID,c.getId());
            cv.put(MovieOpenHelper.MOVIE_DES,c.getDescription());
            cv.put(MovieOpenHelper.MOVIE_IMAGE,c.getImage());
            db.insert(MovieOpenHelper.MOVIE_TABLE,null,cv);



            data.add(c);
        }
        adapter.notifyDataSetChanged();
    }

}
