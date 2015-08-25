package de.k11dev.sklaiber.popularmovies.app;

import android.app.Application;

import de.k11dev.sklaiber.popularmovies.rest.RestClient;

/**
 * Created by sklaiber on 25.08.15.
 */
public class App extends Application {

    private static RestClient restClient;

    @Override
    public void onCreate() {
        super.onCreate();
        restClient = new RestClient();
    }

    public static RestClient getRestClient() {
        return restClient;
    }
}
