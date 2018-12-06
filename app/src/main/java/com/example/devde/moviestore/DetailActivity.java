package com.example.devde.moviestore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.devde.moviestore.Model.Movie;
import com.example.devde.moviestore.Model.MovieResponse;
import com.example.devde.moviestore.Rest.ApiClent;
import com.example.devde.moviestore.Rest.ApiInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    int movie_id ;
    Movie movie;
    private RecyclerView recyclerView;

    String movieTitle,movieOverview;
    TextView title,overview;
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_layout);

       title = findViewById(R.id.title);
       overview = findViewById(R.id.overview);
       imageview = findViewById(R.id.image_view);


        Intent intentThatStartedThisActivity = getIntent();
        if(intentThatStartedThisActivity.hasExtra("movie")){
            movie =  getIntent().getParcelableExtra("movie");
            movieTitle =movie.getOriginalTitle();
            movieOverview = movie.getOverview();
            movie_id = movie.getId();

            String poster = "http://image.tmdb.org/t/p/w185/" + movie.getPosterPath();
            Picasso.get().load(poster).fit().into(imageview);

            title.setText(movieTitle);
            overview.setText(movieOverview);
        }else{
            Toast.makeText(this, "Api Missing", Toast.LENGTH_SHORT).show();
        }
    }
}
