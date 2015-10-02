package de.k11dev.sklaiber.popularmovies.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.k11dev.sklaiber.popularmovies.Config;
import de.k11dev.sklaiber.popularmovies.DividerItemDecoration;
import de.k11dev.sklaiber.popularmovies.R;
import de.k11dev.sklaiber.popularmovies.app.App;
import de.k11dev.sklaiber.popularmovies.model.MovieParcelable;
import de.k11dev.sklaiber.popularmovies.model.VideoResult;
import de.k11dev.sklaiber.popularmovies.rest.model.VideosResponse;
import de.k11dev.sklaiber.popularmovies.ui.activity.MainActivity;
import de.k11dev.sklaiber.popularmovies.ui.adapter.VideosViewAdapter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

/**
 * Created by sklaiber on 30.08.15.
 */
public class VideoFragment extends Fragment {

    private final static String TRAILER_KEY = "trailer_key";

    private ArrayList<VideoResult> mVideoResults = new ArrayList<>();

    private MovieParcelable mMovieParcelable;

    @Bind(R.id.recycler_view) RecyclerView mRecyclerView;

    public static VideoFragment newInstance(MovieParcelable movie) {
        VideoFragment videoFragment = new VideoFragment();
        Bundle args = new Bundle();
        args.putParcelable(MainActivity.KEY_MOVIE_PARCELABLE, movie);
        videoFragment.setArguments(args);
        return videoFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mMovieParcelable = getArguments().getParcelable(MainActivity.KEY_MOVIE_PARCELABLE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_videos, container, false);

        ButterKnife.bind(this, rootView);

        if (savedInstanceState != null) {
            mVideoResults = savedInstanceState.getParcelableArrayList(TRAILER_KEY);

            if (mVideoResults != null) {
                mRecyclerView.setAdapter(new VideosViewAdapter(getActivity(), mVideoResults));
            }
        } else {
            updateTrailers();
        }

        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_movie_detail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.action_share:
                createShareIntent();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createShareIntent() {

        if (mVideoResults.size() != 0) {
            Uri uri = Uri.parse(Config.YOUTUBE_URL + mVideoResults.get(0).getKey());

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            sendIntent.putExtra(Intent.EXTRA_TEXT, uri.toString());
            sendIntent.setType("text/plain");

            startActivity(sendIntent);
        } else {
            Toast.makeText(getActivity(), getString(R.string.trailer_empty), Toast.LENGTH_LONG).show();
        }
    }

    public void updateTrailers() {
        int id = mMovieParcelable.getId();

        App.getRestClient().getMovieService().getVideos(id, Config.API_KEY, new Callback<VideosResponse>() {
            @Override
            public void success(VideosResponse videosResponse, Response response) {
                if (!videosResponse.getVideoResults().isEmpty()) {
                    mVideoResults.addAll(videosResponse.getVideoResults());
                    mRecyclerView.setAdapter(new VideosViewAdapter(getActivity(), videosResponse.getVideoResults()));
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Timber.d(error.getMessage());
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(TRAILER_KEY, mVideoResults);
    }

}
