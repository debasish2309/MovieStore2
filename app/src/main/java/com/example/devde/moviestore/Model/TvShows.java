package com.example.devde.moviestore.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

    public class TvShows implements Parcelable{

    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("popularity")
    private Number popularity;
    @SerializedName("id")
    private Integer id;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("vote_average")
    private Number voteAverage;
    @SerializedName("overview")
    private String overview;
    @SerializedName("first_air_date")
    private String firstAirDate;
    @SerializedName("origin_country")
    private List<String> originCountry;
    @SerializedName("genre_ids")
    private List<Integer> genreIds;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("vote_count")
    private Integer voteCount;
    @SerializedName("name")
    private String name;
    @SerializedName("original_name")
    private String originalName;

    public TvShows(String posterPath, Number popularity, Integer id, String backdropPath, Number voteAverage, String overview, String firstAirDate, List<String> originCountry, List<Integer> genreIds, String originalLanguage, Integer voteCount, String name, String originalName) {
        this.posterPath = posterPath;
        this.popularity = popularity;
        this.id = id;
        this.backdropPath = backdropPath;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.firstAirDate = firstAirDate;
        this.originCountry = originCountry;
        this.genreIds = genreIds;
        this.originalLanguage = originalLanguage;
        this.voteCount = voteCount;
        this.name = name;
        this.originalName = originalName;

    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Number getPopularity() {
        return popularity;
    }

    public void setPopularity(Number popularity) {
        this.popularity = popularity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Number getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Number voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.posterPath);
        dest.writeValue(this.popularity);
        dest.writeValue(this.id);
        dest.writeString(this.backdropPath);
        dest.writeValue(this.voteAverage);
        dest.writeString(this.overview);
        dest.writeString(this.firstAirDate);
        dest.writeValue(this.originCountry);
        dest.writeValue(this.genreIds);
        dest.writeString(this.originalLanguage);
        dest.writeValue(this.voteCount);
        dest.writeString(this.originalName);
        dest.writeString(this.name);

    }
    private TvShows(Parcel in){
        this.posterPath = in.readString();
        this.popularity = in.readInt();
        this.id = in.readInt();
        this.backdropPath = in.readString();
        this.voteAverage = in.readInt();
        this.overview = in.readString();
        this.firstAirDate = in.readString();
     //   this.originCountry = new ArrayList<String>();
     //   in.readList(this.originCountry, String.class.getClassLoader());
     //   this.genreIds = new ArrayList<Integer>();
     //   in.readList(this.genreIds, Integer.class.getClassLoader());
        this.voteCount = in.readInt();
        this.originalName = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<TvShows> CREATOR = new Parcelable.Creator<TvShows>() {

        @Override
        public TvShows createFromParcel(Parcel source) {
            return new TvShows(source);
        }

        @Override
        public TvShows[] newArray(int size) {
            return new TvShows[size];
        }
    };

}
