package de.k11dev.sklaiber.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sklaiber on 18.08.15.
 */
public class MovieParcelable implements Parcelable {

    private int id;
    private String title;
    private String releaseDate;
    private float voteAverage;
    private String overview;
    private String posterPath;
    private String backdropPath;

    public MovieParcelable(int id,
                           String title,
                           String releaseDate,
                           float voteAverage,
                           String overview,
                           String posterPath,
                           String backdropPath)
    {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
    }

    public MovieParcelable(Parcel in){
        this.id = in.readInt();
        this.backdropPath = in.readString();
        this.overview = in.readString();
        this.releaseDate = in.readString();
        this.posterPath = in.readString();
        this.title = in.readString();
        this.voteAverage = in.readFloat();
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.backdropPath);
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
        dest.writeString(this.posterPath);
        dest.writeString(this.title);
        dest.writeFloat(this.voteAverage);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public MovieParcelable createFromParcel(Parcel in) {
            return new MovieParcelable(in);
        }

        public MovieParcelable[] newArray(int size) {
            return new MovieParcelable[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseYear() {
        return releaseDate;
    }

    public float getRating() {
        return voteAverage;
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