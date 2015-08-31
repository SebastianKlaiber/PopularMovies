package de.k11dev.sklaiber.popularmovies.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sklaiber on 30.08.15.
 */
public class ReviewResult {
    @SerializedName("id")
    private int id;

    @SerializedName("author")
    private String author;

    @SerializedName("content")
    private String content;

    @SerializedName("url")
    private String url;

    public int getId() {
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

}
