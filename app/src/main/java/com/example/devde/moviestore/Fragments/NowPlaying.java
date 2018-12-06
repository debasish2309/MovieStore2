package com.example.devde.moviestore.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.devde.moviestore.Adapter.MoviesAdapter;
import com.example.devde.moviestore.MainActivity;
import com.example.devde.moviestore.Model.Movie;
import com.example.devde.moviestore.Model.MovieResponse;
import com.example.devde.moviestore.R;
import com.example.devde.moviestore.Rest.ApiClent;
import com.example.devde.moviestore.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlaying extends Fragment {

    private final static String API_KEY ="0ce7227febd49ffdf2da55d1c5aa607f";
    private final static String TAG = NowPlaying.class.getSimpleName();
    RecyclerView recyclerView;

    public NowPlaying() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_now_playing, container, false);
        if(API_KEY.isEmpty()){
            Toast.makeText(getContext(), "Please set the Api Key", Toast.LENGTH_SHORT).show();
        }
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getActivity(),2));
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        ApiInterface apiService = ApiClent.getClient().create(ApiInterface.class);
        Call<MovieResponse> call = apiService.getNowPlayingMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();
                recyclerView.setAdapter(new MoviesAdapter(movies,getActivity().getApplicationContext()));
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG,t.toString());

            }
        });

        return view;
    }

}
