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

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.k11dev.sklaiber.popularmovies.Config;
import de.k11dev.sklaiber.popularmovies.DividerItemDecoration;
import de.k11dev.sklaiber.popularmovies.R;
import de.k11dev.sklaiber.popularmovies.app.App;
import de.k11dev.sklaiber.popularmovies.model.MovieParcelable;
import de.k11dev.sklaiber.popularmovies.model.ReviewResult;
import de.k11dev.sklaiber.popularmovies.model.VideoResult;
import de.k11dev.sklaiber.popularmovies.rest.model.ReviewsResponse;
import de.k11dev.sklaiber.popularmovies.rest.model.VideosResponse;
import de.k11dev.sklaiber.popularmovies.ui.activity.MainActivity;
import de.k11dev.sklaiber.popularmovies.ui.adapter.ReviewsViewAdapter;
import de.k11dev.sklaiber.popularmovies.ui.adapter.VideosViewAdapter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by sklaiber on 30.08.15.
 */
public class ReviewFragment extends Fragment {

    private final String LOG_TAG = ReviewFragment.class.getSimpleName();

    private final static String REVIEW_KEY = "review_key";

    private MovieParcelable mMovieParcelable;

    private ArrayList<ReviewResult> mReviewResults;

    @Bind(R.id.recycler_view) RecyclerView mRecyclerView;

    public static ReviewFragment newInstance(MovieParcelable movie) {
        ReviewFragment reviewFragment = new ReviewFragment();
        Bundle args = new Bundle();
        args.putParcelable(MainActivity.KEY_MOVIE_PARCELABLE, movie);
        reviewFragment.setArguments(args);
        return reviewFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMovieParcelable = getArguments().getParcelable(MainActivity.KEY_MOVIE_PARCELABLE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_review, container, false);

        ButterKnife.bind(this, rootView);

        if (savedInstanceState != null) {
            mReviewResults = savedInstanceState.getParcelableArrayList(REVIEW_KEY);

            if (mReviewResults != null) {
                mRecyclerView.setAdapter(new ReviewsViewAdapter(getActivity(), mReviewResults));
            }
        } else {
            updateReviews();
        }

//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        return rootView;
    }

    public void updateReviews() {
        int id = Integer.valueOf(mMovieParcelable.getIdParc());

        App.getRestClient().getMovieService().getReviews(id, Config.API_KEY, new Callback<ReviewsResponse>() {
            @Override
            public void success(ReviewsResponse reviewsResponse, Response response) {
                if (!reviewsResponse.getReviewResults().isEmpty()) {
                    mReviewResults = reviewsResponse.getReviewResults();
                    mRecyclerView.setAdapter(new ReviewsViewAdapter(getActivity(), reviewsResponse.getReviewResults()));
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(LOG_TAG, error.getMessage());
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(REVIEW_KEY, mReviewResults);
    }
}
