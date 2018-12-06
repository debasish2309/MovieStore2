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

import com.example.devde.moviestore.Adapter.TvShowsAdapter;
import com.example.devde.moviestore.MainActivity;
import com.example.devde.moviestore.Model.TvShows;
import com.example.devde.moviestore.Model.TvShowsResponse;
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
public class TvAiringToday extends Fragment {
    private static final String API_KEY = "0ce7227febd49ffdf2da55d1c5aa607f";
    private static final String TAG = TvAiringToday.class.getSimpleName();
    RecyclerView recyclerView;


    public TvAiringToday() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tv_airing_today, container, false);
        if(API_KEY == null) {
            Toast.makeText(getContext(), "Please Enter the Required Api Key", Toast.LENGTH_SHORT).show();
        }
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
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

        Call<TvShowsResponse> call = apiService.getAiringToday(API_KEY);
        call.enqueue(new Callback<TvShowsResponse>() {
            @Override
            public void onResponse(Call<TvShowsResponse> call, Response<TvShowsResponse> response) {
                List<TvShows> shows = response.body().getResults();
                recyclerView.setAdapter(new TvShowsAdapter(shows,getActivity().getApplicationContext()));
            }

            @Override
            public void onFailure(Call<TvShowsResponse> call, Throwable t) {
                Log.e(TAG,t.toString());

            }
        });
        return view;
    }

}
