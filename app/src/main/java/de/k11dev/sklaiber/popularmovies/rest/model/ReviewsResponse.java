package de.k11dev.sklaiber.popularmovies.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import de.k11dev.sklaiber.popularmovies.model.ReviewResult;

/**
 * Created by sklaiber on 30.08.15.
 */
public class ReviewsResponse {

    @SerializedName("results")
    private ArrayList<ReviewResult> reviewResults;

    public ArrayList<ReviewResult> getReviewResults() {
        return reviewResults;
    }
}
