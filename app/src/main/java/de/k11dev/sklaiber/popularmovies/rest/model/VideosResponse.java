package de.k11dev.sklaiber.popularmovies.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import de.k11dev.sklaiber.popularmovies.model.VideoResult;

/**
 * Created by sklaiber on 30.08.15.
 */
public class VideosResponse {

    @SerializedName("results")
    private ArrayList<VideoResult> videoResults;

    public ArrayList<VideoResult> getVideoResults() {
        return videoResults;
    }
}
