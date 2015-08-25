package de.k11dev.sklaiber.popularmovies.ui.fragment;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.k11dev.sklaiber.popularmovies.Config;
import de.k11dev.sklaiber.popularmovies.R;
import de.k11dev.sklaiber.popularmovies.app.App;
import de.k11dev.sklaiber.popularmovies.model.Movie;
import de.k11dev.sklaiber.popularmovies.model.Result;
import de.k11dev.sklaiber.popularmovies.rest.model.ApiResponse;
import de.k11dev.sklaiber.popularmovies.ui.activity.MainActivity;
import de.k11dev.sklaiber.popularmovies.ui.adapter.ImageArrayAdapter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by sklaiber on 19.08.15.
 */
public class GridFragment extends Fragment implements GridView.OnItemClickListener{

    private ArrayAdapter<Result> mMovieAdapter;

    public static final String LOG_TAG = GridFragment.class.getSimpleName();

    @Bind(R.id.grid) GridView mGridView;
    @Bind(R.id.progress_bar) ProgressBar mProgressBar;

    public interface Callback {
        void onItemSelected(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mMovieAdapter = new ImageArrayAdapter(getActivity(), new ArrayList<Result>());

        View rootView = inflater.inflate(R.layout.fragment_grid, container, false);

        ButterKnife.bind(this, rootView);

        mGridView.setOnItemClickListener(this);
        mGridView.setAdapter(mMovieAdapter);

        return rootView;
    }

    public void updateMovies() {
        mProgressBar.setVisibility(View.VISIBLE);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String sort = preferences.getString(getString(R.string.pref_sort_key),
                getString(R.string.pref_default_value));

        App.getRestClient().getMovieService().getMovie(sort, Config.API_KEY, new retrofit.Callback<ApiResponse>() {
            @Override
            public void success(ApiResponse apiResponse, Response response) {
                if (!apiResponse.getResults().isEmpty()) {
                    mProgressBar.setVisibility(View.GONE);

                    mMovieAdapter.clear();
                    mMovieAdapter.addAll(apiResponse.getResults());

                    ((MainActivity) getActivity()).setList(apiResponse.getResults());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(LOG_TAG, error.getMessage());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        updateMovies();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((Callback) getActivity()).onItemSelected(position);
    }
}
