package de.k11dev.sklaiber.popularmovies.ui.fragment;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.k11dev.sklaiber.popularmovies.Config;
import de.k11dev.sklaiber.popularmovies.R;
import de.k11dev.sklaiber.popularmovies.Utility;
import de.k11dev.sklaiber.popularmovies.app.App;
import de.k11dev.sklaiber.popularmovies.model.Result;
import de.k11dev.sklaiber.popularmovies.provider.movie.MovieCursor;
import de.k11dev.sklaiber.popularmovies.provider.movie.MovieSelection;
import de.k11dev.sklaiber.popularmovies.rest.model.ApiResponse;
import de.k11dev.sklaiber.popularmovies.ui.activity.MainActivity;
import de.k11dev.sklaiber.popularmovies.ui.adapter.ImageArrayAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

/**
 * Created by sklaiber on 19.08.15.
 */
public class GridFragment extends Fragment implements GridView.OnItemClickListener{

    private ArrayAdapter<Result> mMovieAdapter;

    private final static String MOVIES_KEY = "movies_key";
    private final static String KEY_SORT = "sort_key";

    private String sortingOrder = "";

    private ArrayList<Result> mResultList = new ArrayList<>();

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

        if (savedInstanceState != null) {

            sortingOrder = savedInstanceState.getString(KEY_SORT);
            mResultList = savedInstanceState.getParcelableArrayList(MOVIES_KEY);

            int index = mGridView.getFirstVisiblePosition();
            mGridView.smoothScrollToPosition(index);
            mGridView.setSelection(index);

            if (mResultList != null) {
                mMovieAdapter.addAll(mResultList);
            }
        } else {
            updateMovies();
        }

        mGridView.setOnItemClickListener(this);
        mGridView.setAdapter(mMovieAdapter);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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

                    mResultList.clear();
                    mResultList.addAll(apiResponse.getResults());

                    ((MainActivity) getActivity()).setList(apiResponse.getResults());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Timber.d(error.getMessage());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        if (!Utility.isNetworkAvailable(getActivity())) {
            Timber.d("Network is not available");
            sortingOrder = Utility.getPreferredSortingOrder(getActivity());
            if (sortingOrder.equals("favorite")) {
                loadFavoriteList();
            }

            CharSequence text = getString(R.string.network_not_available_message);
            Toast toast = Toast.makeText(getActivity(), text, Toast.LENGTH_LONG);
            toast.show();
        } else {
            String newSortingOrder = Utility.getPreferredSortingOrder(getActivity());
            if (newSortingOrder != null && !newSortingOrder.equals(sortingOrder)) {
                Timber.d("updating movies via API call");
                sortingOrder = newSortingOrder;

                if (sortingOrder.equals("favorite")){
                    loadFavoriteList();
                } else {
                    updateMovies();
                }
            }

        }
    }

    public void loadFavoriteList() {
        mProgressBar.setVisibility(View.GONE);
        mResultList.clear();
        mMovieAdapter.clear();

        MovieSelection movieSelection = new MovieSelection();
        MovieCursor c = movieSelection.query(getActivity().getContentResolver());

        while (c.moveToNext()) {
            Result result = new Result();
            result.setId(c.getMovieId());
            result.setTitle(c.getTitle());
            result.setReleaseDate(c.getReleaseDate());
            result.setVoteAverage(c.getVoteAverage());
            result.setOverview(c.getOverview());
            result.setPosterPath(c.getPosterPath());
            result.setBackdropPath(c.getBackdropPath());

            mResultList.add(result);
        }
        mMovieAdapter.addAll(mResultList);
        ((MainActivity) getActivity()).setList(mResultList);

        c.close();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(MOVIES_KEY, mResultList);
        outState.putString(KEY_SORT, sortingOrder);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((Callback) getActivity()).onItemSelected(position);
    }

    public void doSomething(ArrayList<Result> movies) {
        sortingOrder = Utility.getPreferredSortingOrder(getActivity());
        if (sortingOrder.equals("favorite")){
            loadFavoriteList();
        }
    }
}
