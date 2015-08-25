package de.k11dev.sklaiber.popularmovies.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by sklaiber on 24.08.15.
 */
public class Movie {

    private String backdrop_path;
    private String id;
    private String original_title;
    private String overview;
    private String releaseDate;
    private String title;
    private String voteAverage;
    private String posterPath;

    final String OWM_POSTER_PATH = "poster_path";
    final String OWM_BACKDROP_PATH = "backdrop_path";
    final String OWM_ID = "id";
    final String OWN_ORIGINAL_TITLE = "original_title";
    final String OWM_OVERVIEW = "overview";
    final String OWM_RELEASE_DATE = "release_date";
    final String OWM_TITLE = "title";
    final String OWM_VOTE_AVERAGE = "vote_average";

    public Movie(JSONObject object) {
        try {
            this.backdrop_path = object.getString(OWM_BACKDROP_PATH);
            this.id = String.valueOf(object.getInt(OWM_ID));
            this.original_title = object.getString(OWN_ORIGINAL_TITLE);
            this.overview = object.getString(OWM_OVERVIEW);
            this.releaseDate = object.getString(OWM_RELEASE_DATE);
            this.title = object.getString(OWM_TITLE);
            this.voteAverage = String.valueOf(object.getDouble(OWM_VOTE_AVERAGE));
            this.posterPath = object.getString(OWM_POSTER_PATH);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Movie> fromJson(JSONArray jsonObjects) {
        ArrayList<Movie> movies = new ArrayList<>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                movies.add(new Movie(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return movies;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getBackdropPath() {
        return backdrop_path;
    }

    public String getId() {
        return id;
    }

    public String getOrginialTitle() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }
}
