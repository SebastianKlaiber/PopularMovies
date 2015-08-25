package de.k11dev.sklaiber.popularmovies.rest.service;


import de.k11dev.sklaiber.popularmovies.rest.model.ApiResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by sklaiber on 25.08.15.
 */
public interface MovieService {
    @GET("/3/discover/movie")
    public void getMovie(@Query("sort_by") String sort, @Query("api_key") String key, Callback<ApiResponse> callback);
}
