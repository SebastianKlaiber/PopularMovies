package de.k11dev.sklaiber.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sklaiber on 18.08.15.
 */
public class MovieParcelable implements Parcelable {

    private String id;
    private String title;
    private String releaseYear;
    private String rating;
    private String overview;
    private String posterPath;
    private String backdropPath;

    public MovieParcelable(String id,
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

    public MovieParcelable(Parcel in){
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
        public MovieParcelable createFromParcel(Parcel in) {
            return new MovieParcelable(in);
        }

        public MovieParcelable[] newArray(int size) {
            return new MovieParcelable[size];
        }
    };

    public String getIdParc() {
        return id;
    }

    public String getTitleParc() {
        return title;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public String getRating() {
        return rating;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }
}