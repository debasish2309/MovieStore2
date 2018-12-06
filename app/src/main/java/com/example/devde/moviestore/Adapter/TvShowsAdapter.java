package com.example.devde.moviestore.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.devde.moviestore.DetailShowsActivity;
import com.example.devde.moviestore.Model.TvShows;
import com.example.devde.moviestore.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TvShowsAdapter extends RecyclerView.Adapter<TvShowsAdapter.TvShowViewHolder>{

    private List<TvShows> tvShows;
    private Context context;

    public TvShowsAdapter(List<TvShows> tvShows, Context context) {
        this.context = context;
        if(this.tvShows == null){this.tvShows = tvShows ;}
    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie,parent,false);
        return new TvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder holder, int position) {
        holder.text.setText(tvShows.get(position).getOriginalName());
        String poster = "http://image.tmdb.org/t/p/w185/" + tvShows.get(position).getPosterPath();
        Picasso.get().load(poster).fit().into(holder.image);
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    public class TvShowViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView text;
        //List<TvShows> tvShows;

        public TvShowViewHolder(View itemView) {
            super(itemView);
            //this.tvShows = tvShows;
            image = itemView.findViewById(R.id.image_view);
            text = itemView.findViewById(R.id.text_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        TvShows clickedDataItem = tvShows.get(pos);
                        Intent intent = new Intent(context, DetailShowsActivity.class);
                        intent.putExtra("tvshow",clickedDataItem);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked" + clickedDataItem.getOriginalName(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
