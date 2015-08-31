package de.k11dev.sklaiber.popularmovies.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.k11dev.sklaiber.popularmovies.Config;
import de.k11dev.sklaiber.popularmovies.DividerItemDecoration;
import de.k11dev.sklaiber.popularmovies.R;
import de.k11dev.sklaiber.popularmovies.Utility;
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

    private ArrayList<VideoResult> mVideoResults;

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


    public void updateTrailers() {
        int id = Integer.valueOf(mMovieParcelable.getIdParc());

        App.getRestClient().getMovieService().getVideos(id, Config.API_KEY, new Callback<VideosResponse>() {
            @Override
            public void success(VideosResponse videosResponse, Response response) {
                if (!videosResponse.getVideoResults().isEmpty()) {
                    mVideoResults = videosResponse.getVideoResults();
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
