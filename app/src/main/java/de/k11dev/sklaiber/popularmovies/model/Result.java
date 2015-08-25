package de.k11dev.sklaiber.popularmovies.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sklaiber on 25.08.15.
 */
public class Result {

    @SerializedName("id")
    private int id;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("title")
    private String title;

    @SerializedName("vote_average")
    private float voteAverage;

    public String getId() {
        return String.valueOf(id);
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getTitle() {
        return title;
    }

    public String getVoteAverage() {
        return String.valueOf(voteAverage);
    }
}
