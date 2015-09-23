package de.k11dev.sklaiber.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sklaiber on 30.08.15.
 */
public class ReviewResult implements Parcelable {
    @SerializedName("id")
    private String id;

    @SerializedName("author")
    private String author;

    @SerializedName("content")
    private String content;

    @SerializedName("url")
    private String url;

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.author);
        dest.writeString(this.content);
        dest.writeString(this.url);
    }

//    public ReviewResult(String author, String content) {
//        this.author = author;
//        this.content = content;
//    }

    public ReviewResult() {
    }

    protected ReviewResult(Parcel in) {
        this.id = in.readString();
        this.author = in.readString();
        this.content = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<ReviewResult> CREATOR = new Parcelable.Creator<ReviewResult>() {
        public ReviewResult createFromParcel(Parcel source) {
            return new ReviewResult(source);
        }

        public ReviewResult[] newArray(int size) {
            return new ReviewResult[size];
        }
    };
}
