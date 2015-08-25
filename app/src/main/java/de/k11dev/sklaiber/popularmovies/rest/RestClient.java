package de.k11dev.sklaiber.popularmovies.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.k11dev.sklaiber.popularmovies.rest.service.MovieService;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by sklaiber on 25.08.15.
 */
public class RestClient {

    private static final String BASE_URL = "http://api.themoviedb.org/";
    private MovieService apiService;

    public RestClient()
    {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new ItemTypeAdapterFactory())
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                .setConverter(new GsonConverter(gson))
                .build();

        apiService = restAdapter.create(MovieService.class);
    }

    public MovieService getMovieService()
    {
        return apiService;
    }
}
