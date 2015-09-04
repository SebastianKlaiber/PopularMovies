package de.k11dev.sklaiber.popularmovies;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import timber.log.Timber;

/**
 * Created by sklaiber on 26.08.15.
 */
public class Utility {

    public static String getPreferredSortingOrder(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(context.getString(R.string.pref_sort_key),
                context.getString(R.string.pref_default_value));
    }

    public static void addMovieId(Context context, String movieId) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();

        Set<String> set = new HashSet<>();

        set.addAll(sharedPref.getStringSet("key", null));

        set.add(movieId);

        editor.putStringSet("key", set);
        editor.apply();
    }

    public static ArrayList<String> getMovies(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        Set<String> set = sharedPref.getStringSet("key", null);

        String[] strings = set.toArray(new String[set.size()]);

        return new ArrayList<>(Arrays.asList(strings));
    }

    public static void removeMovie(Context context, String movieId) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();

        Set<String> set = new HashSet<>();
        set.addAll(sharedPref.getStringSet("key", null));

        set.remove(movieId);
        editor.putStringSet("key", set);
        editor.commit();
    }

    static public boolean isNetworkAvailable(Context c) {
        ConnectivityManager cm =
                (ConnectivityManager)c.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
