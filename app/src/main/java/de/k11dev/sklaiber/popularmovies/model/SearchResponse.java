package de.k11dev.sklaiber.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sklaiber on 16.08.15.
 */
public class SearchResponse {

    public List<Result> results;

    public List<Result> getList () {
        List<Result> movie = new ArrayList<>();

        for (Result result : results) {
//            results.add(result.posterPath);
            results.add(result);
        }

        return results;
    }

}
