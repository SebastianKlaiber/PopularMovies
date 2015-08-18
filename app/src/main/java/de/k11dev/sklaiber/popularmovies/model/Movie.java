package de.k11dev.sklaiber.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sklaiber on 18.08.15.
 */
public class Movie implements Parcelable {

    private static List<Movie> movieList = new ArrayList<>();

    private String id;

    private String title;

    private String releaseYear;

    private String rating;

    private String overview;

    public String posterPath;

    private String backdropPath;

    //Constructor
    public Movie(String id,
                 String title,
                 String releaseYear,
                 String rating,
                 String overview,
                 String posterPath,
                 String backdropPath)
    {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.overview = overview;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
    }

    //Getter and Setter Methods

    //Parcelling Section
    public Movie(Parcel in){
        String[] movieData = new String[7];

        in.readStringArray(movieData);
        this.id = movieData[0];
        this.title = movieData[1];
        this.releaseYear = movieData[2];
        this.rating = movieData[3];
        this.overview = movieData[4];
        this.posterPath = movieData[5];
        this.backdropPath = movieData[6];
    }

    public static void setMovieList(List<Movie> list) {
        movieList = list;
    }

    public static List<Movie> getMovieList() {
        return movieList;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {
                this.id,
                this.title,
                this.releaseYear,
                this.rating,
                this.overview,
                this.posterPath,
                this.backdropPath
        });
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseYear() {
        String[] parts = releaseYear.split("-");
        return parts[0];
    }

    public String getRating() {
        return rating + "/10";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
}