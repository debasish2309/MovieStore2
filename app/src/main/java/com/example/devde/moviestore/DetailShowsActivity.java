package com.example.devde.moviestore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.devde.moviestore.Model.TvShows;
import com.squareup.picasso.Picasso;

public class DetailShowsActivity extends AppCompatActivity {

    int tvShow_id;
    TvShows tvShows;
    private RecyclerView recyclerView;

    String showTitle,showOverview;
    TextView title,overview;
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_shows);

        title = findViewById(R.id.title);
        overview = findViewById(R.id.overview);
        imageview = findViewById(R.id.image_view);

        Intent intentThatStartedThisActivity = getIntent();
        if(intentThatStartedThisActivity.hasExtra("tvshow")){
            tvShows = getIntent().getParcelableExtra("tvshow");
            showTitle = tvShows.getOriginalName();
            showOverview = tvShows.getOverview();
            tvShow_id = tvShows.getId();

            String posterPath = "http://image.tmdb.org/t/p/w185/" + tvShows.getPosterPath();
            Picasso.get().load(posterPath).fit().into(imageview);

            title.setText(showTitle);
            overview.setText(showOverview);
        } else {
            Toast.makeText(this, "Api Missing", Toast.LENGTH_SHORT).show();
        }

    }
}
