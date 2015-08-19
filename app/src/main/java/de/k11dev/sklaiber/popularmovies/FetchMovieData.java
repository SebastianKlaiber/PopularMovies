package de.k11dev.sklaiber.popularmovies;


import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import de.k11dev.sklaiber.popularmovies.model.Movie;
import de.k11dev.sklaiber.popularmovies.model.Result;
import de.k11dev.sklaiber.popularmovies.model.SearchResponse;

/**
 * Created by sklaiber on 19.08.15.
 */
public class FetchMovieData {

    public static void getMovieData(String sort) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(Config.API_URL
                + sort
                + Config.API_KEY)
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    SearchResponse searchResponse = gson.fromJson(response.body().string(), SearchResponse.class);
                    Result.setResultList(searchResponse.results);

                }
            }
        });
    }
    
    public static Movie getMovieParcable(int position) {
        Movie movie = new Movie(
                String.valueOf(Result.getResultList().get(position).id)
                , Result.getResultList().get(position).title
                , Result.getResultList().get(position).releaseDate
                , String.valueOf(Result.getResultList().get(position).voteAverage)
                , Result.getResultList().get(position).overview
                , Result.getResultList().get(position).posterPath
                , Result.getResultList().get(position).backdropPath);
        return movie;
    }

}
