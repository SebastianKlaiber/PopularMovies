package de.k11dev.sklaiber.popularmovies;

/**
 * Created by sklaiber on 16.08.15.
 */
public class Config {

    public static final String API_URL = "http://api.themoviedb.org/3/discover/movie?sort_by=";

    public static final String HIGHEST_RATED = "vote_average.desc";
    public static final String MOST_POPULAR = "popularity.desc";

    // Todo: Remove API-KEY before commit code
    public static final String API_KEY = "&api_key=";

    public static final String IMAGE_URL = "http://image.tmdb.org/t/p/";
    public static final String IMAGE_URL_SIZE = IMAGE_URL + "w185";
}
