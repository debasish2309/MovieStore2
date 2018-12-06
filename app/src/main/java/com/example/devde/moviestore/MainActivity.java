package com.example.devde.moviestore;

import android.app.SearchManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.devde.moviestore.Adapter.MoviesAdapter;
import com.example.devde.moviestore.Adapter.MoviesAdapter;
import com.example.devde.moviestore.Fragments.HomeFragment;
import com.example.devde.moviestore.Fragments.NowPlaying;
import com.example.devde.moviestore.Fragments.Popular;
import com.example.devde.moviestore.Fragments.TvAiringToday;
import com.example.devde.moviestore.Fragments.TvOnTheAir;
import com.example.devde.moviestore.Fragments.TvPopular;
import com.example.devde.moviestore.Fragments.TvTopRated;
import com.example.devde.moviestore.Fragments.Upcomming;
import com.example.devde.moviestore.Model.Movie;
import com.example.devde.moviestore.Model.MovieResponse;
import com.example.devde.moviestore.Rest.ApiClent;
import com.example.devde.moviestore.Rest.ApiInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String API_KEY = "0ce7227febd49ffdf2da55d1c5aa607f";
    MoviesAdapter moviesAdapter;
    private RecyclerView recyclerView;
    ArrayList<Movie> movie;
    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        retrofitAttachment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_contaner,new HomeFragment()).addToBackStack(null).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }



    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_button,menu);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                recyclerView = findViewById(R.id.recyclerview);
                ApiInterface apiInterface = ApiClent.getClient().create(ApiInterface.class);
                Call<MovieResponse> call = apiInterface.getMovieSearch(query,API_KEY);
                call.enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        List<Movie> movies = response.body().getResults();
                        recyclerView.setAdapter(new MoviesAdapter(movies,getApplicationContext()));
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {

                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }*/

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_button,menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchItem(searchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    public void searchItem(SearchView searchView){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                recyclerView = findViewById(R.id.recyclerview);
                ApiInterface apiservice = ApiClent.getClient().create(ApiInterface.class);
                Call<MovieResponse> calls = apiservice.getMovieSearch(query,API_KEY);
                calls.enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        List<Movie> movies = response.body().getResults();
                        recyclerView.setAdapter(new MoviesAdapter(movies,getApplicationContext()));
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {
                        Log.e(TAG,t.toString());

                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                moviesAdapter.getFilter().filter(newText);

                return true;
            }
        });
    }*/

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.get_now_playing:
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_contaner,new NowPlaying()).addToBackStack(null).commit();
                break;
            case R.id.get_popular:
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_contaner,new Popular()).addToBackStack(null).commit();
                break;
            case R.id.get_upcomming:
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_contaner,new Upcomming()).addToBackStack(null).commit();
                break;
            case R.id.get_tv_airing_today:
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_contaner,new TvAiringToday()).addToBackStack(null).commit();
                break;
            case R.id.get_tv_on_the_air:
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_contaner,new TvOnTheAir()).addToBackStack(null).commit();
                break;
            case R.id.get_tv_popular:
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_contaner,new TvPopular()).addToBackStack(null).commit();
                break;
            case R.id.get_tv_top_rated:
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_contaner,new TvTopRated()).addToBackStack(null).commit();
                break;

        }
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

   private void retrofitAttachment(){
        if(API_KEY.isEmpty()){
            Toast.makeText(this, "Please Enter Your Api Key", Toast.LENGTH_SHORT).show();
        }
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        ApiInterface apiservice = ApiClent.getClient().create(ApiInterface.class);
        Call<MovieResponse> call = apiservice.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
                         @Override
                         public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                             List<Movie> movies = response.body().getResults();
                             recyclerView.setAdapter(new MoviesAdapter(movies,getApplicationContext()));
                         }

                         @Override
                         public void onFailure(Call<MovieResponse> call, Throwable t) {
                             Log.e(TAG,t.toString());

                         }
        });

        return;
    }

}
