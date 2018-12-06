package com.example.devde.moviestore.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowsResponse {

    @SerializedName("page")
    private Integer page;
    @SerializedName("results")
    private List<TvShows> results;
    @SerializedName("total_results")
    private Integer totalResults;
    @SerializedName("total_pages")
    private Integer totalPages;

    public TvShowsResponse(Integer page, List<TvShows> results, Integer totalResults, Integer totalPages) {
        this.page = page;
        this.results = results;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<TvShows> getResults() {
        return results;
    }

    public void setResults(List<TvShows> results) {
        this.results = results;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
