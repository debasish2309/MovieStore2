package com.example.devde.moviestore.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.devde.moviestore.DetailActivity;
import com.example.devde.moviestore.Model.Movie;
//import com.example.devde.moviestore.R;
import com.example.devde.moviestore.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> /* implements Filterable*/{

    private List<Movie> movies;
    private Context context;

    public MoviesAdapter(List<Movie> movies, Context context) {
        this.context = context;
        if(this.movies == null){ this.movies = movies; }

    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.text.setText(movies.get(position).getTitle());
        String poster = "http://image.tmdb.org/t/p/w185/" +movies.get(position).getPosterPath();
        Picasso.get().load(poster).fit().into(holder.image);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    /*public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if(charString.isEmpty()) {
                }else {
                    List<Movie> filterdlist = new ArrayList<>();
                    for(Movie movie : movies) {
                        if(movie.getOriginalTitle().toLowerCase().contains(constraint)){
                            filterdlist.add(movie);
                        }

                    }
                    movies = filterdlist;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = movies;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                movies = (List<Movie>) results.values;
                notifyDataSetChanged();

            }
        };
    }*/


    public class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView text;
        //List<Movie> movies;
       // Context context;

        public MovieViewHolder(View itemView) {
            super(itemView);
            //this.movies = movies;

            image = itemView.findViewById(R.id.image_view);
            text = itemView.findViewById(R.id.text_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION) {
                       Movie clickedDataItem = movies.get(pos);
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("movie", clickedDataItem);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                       context.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked" + clickedDataItem.getOriginalTitle(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

}
