package de.k11dev.sklaiber.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sklaiber on 16.08.15.
 */
public class Result {

    private static List<Result> resultList = new ArrayList<>();

    @SerializedName("id")
    public int id;

    @SerializedName("backdrop_path")
    public String backdropPath;

    @SerializedName("original_title")
    public String originalTitle;

    @SerializedName("poster_path")
    public String posterPath;

    @SerializedName("overview")
    public String overview;

    @SerializedName("release_date")
    public String releaseDate;

    @SerializedName("title")
    public String title;

    @SerializedName("vote_average")
    public float voteAverage;

    public static void setResultList(List<Result> list) {
        resultList = list;
    }

    public static List<Result> getResultList() {
        return resultList;
    }
}
