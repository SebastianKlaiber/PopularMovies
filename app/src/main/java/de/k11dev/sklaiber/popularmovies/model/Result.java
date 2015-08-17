package de.k11dev.sklaiber.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sklaiber on 16.08.15.
 */
public class Result {

    @SerializedName("original_title")
    public String originalTitle;

    @SerializedName("poster_path")
    public String posterPath;


    public List<Result> getResultList() {
        List<Result> resultList = new ArrayList<>();
        return resultList;
    }

}
