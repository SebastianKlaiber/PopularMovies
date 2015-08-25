package de.k11dev.sklaiber.popularmovies.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import de.k11dev.sklaiber.popularmovies.model.Result;

/**
 * Created by sklaiber on 25.08.15.
 */
public class ApiResponse {

    @SerializedName("results")
    private ArrayList<Result> results;

    public ArrayList<Result> getResults() {
        return results;
    }
}
