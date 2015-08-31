package de.k11dev.sklaiber.popularmovies.rest.service;


import de.k11dev.sklaiber.popularmovies.rest.model.ApiResponse;
import de.k11dev.sklaiber.popularmovies.rest.model.ReviewsResponse;
import de.k11dev.sklaiber.popularmovies.rest.model.VideosResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by sklaiber on 25.08.15.
 */
public interface MovieService {
    @GET("/3/discover/movie")
    public void getMovie(@Query("sort_by") String sort, @Query("api_key") String key, Callback<ApiResponse> callback);

    @GET("/3/movie/{id}/videos")
    public void getVideos(@Path("id") int movieId, @Query("api_key") String key, Callback<VideosResponse> callback);

    @GET("/3/movie/{id}/reviews")
    public void getReviews(@Path("id") int movieId, @Query("api_key") String key, Callback<ReviewsResponse> callback);
}
