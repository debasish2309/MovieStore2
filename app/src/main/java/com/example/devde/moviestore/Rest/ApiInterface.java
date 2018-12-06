package com.example.devde.moviestore.Rest;

import com.example.devde.moviestore.Model.Movie;
import com.example.devde.moviestore.Model.MovieResponse;
import com.example.devde.moviestore.Model.TvShowsResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String api_key);

    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlayingMovies(@Query("api_key") String api_key);

    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String api_key);

    @GET("movie/upcoming")
    Call<MovieResponse> getUpcomingMovies(@Query("api_key") String api_key);

    @GET("search/movie/{query}")
    Call<MovieResponse> getMovieSearch( @Query("query") String query,@Query("api_key") String api_key);

    @GET("tv/airing_today")
    Call<TvShowsResponse> getAiringToday(@Query("api_key") String api_key);

    @GET("tv/on_the_air")
    Call<TvShowsResponse> getOnTheAir(@Query("api_key") String api_key);

    @GET("tv/popular")
    Call<TvShowsResponse> getPopularSeries(@Query("api_key") String api_key);

    @GET("tv/top_rated")
    Call<TvShowsResponse> getTopRatedSeries(@Query("api_key") String api_key);

    @GET("movie/{id}")
    Call<MovieResponse> getMovieDetails(@Path("id") int id,@Query("api_key") String api_key);
}
