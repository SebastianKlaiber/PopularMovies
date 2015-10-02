package de.k11dev.sklaiber.popularmovies.ui.fragment;

import android.app.Fragment;
import android.content.ContentUris;
import android.content.ContentValues;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Vector;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.k11dev.sklaiber.popularmovies.Config;
import de.k11dev.sklaiber.popularmovies.R;
import de.k11dev.sklaiber.popularmovies.app.App;
import de.k11dev.sklaiber.popularmovies.model.MovieParcelable;
import de.k11dev.sklaiber.popularmovies.model.ReviewResult;
import de.k11dev.sklaiber.popularmovies.model.VideoResult;
import de.k11dev.sklaiber.popularmovies.provider.movie.MovieContentValues;
import de.k11dev.sklaiber.popularmovies.provider.movie.MovieCursor;
import de.k11dev.sklaiber.popularmovies.provider.movie.MovieSelection;
import de.k11dev.sklaiber.popularmovies.provider.review.ReviewColumns;
import de.k11dev.sklaiber.popularmovies.provider.review.ReviewSelection;
import de.k11dev.sklaiber.popularmovies.provider.trailer.TrailerColumns;
import de.k11dev.sklaiber.popularmovies.provider.trailer.TrailerSelection;
import de.k11dev.sklaiber.popularmovies.rest.model.ReviewsResponse;
import de.k11dev.sklaiber.popularmovies.rest.model.VideosResponse;
import de.k11dev.sklaiber.popularmovies.ui.activity.MainActivity;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

/**
 * Created by sklaiber on 19.08.15.
 */
public class DetailFragment extends Fragment {

    private MovieParcelable mMovieParcelable;

    @Bind(R.id.title) TextView mTitleTv;
    @Bind(R.id.release_date) TextView  mReleaseDateTv;
    @Bind(R.id.description)  TextView  mDescriptionTv;
    @Bind(R.id.detail_image) ImageView mImageView;
    @Bind(R.id.title_image) ImageView mTitleImage;
    @Bind(R.id.rating_bar)  RatingBar mRatingBar;
    @Bind(R.id.fab_normal)  FloatingActionButton mFAB;

    public static DetailFragment newInstance(MovieParcelable movie) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(MainActivity.KEY_MOVIE_PARCELABLE, movie);
        detailFragment.setArguments(args);
        return detailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMovieParcelable = getArguments().getParcelable(MainActivity.KEY_MOVIE_PARCELABLE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        ButterKnife.bind(this, rootView);

        MovieSelection movieSelection = new MovieSelection();
        MovieCursor c = movieSelection.query(getActivity().getContentResolver());

        while (c.moveToNext()) {
            if (mMovieParcelable.getId() == c.getMovieId()) {
                mFAB.setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
            }
        }

        c.close();


        mTitleTv.setText(mMovieParcelable.getTitle());
        mDescriptionTv.setText(mMovieParcelable.getOverview());
        mReleaseDateTv.setText(mMovieParcelable.getReleaseYear());

        mRatingBar.setRating(mMovieParcelable.getRating());

        final LayerDrawable stars = (LayerDrawable) mRatingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        Picasso.with(getActivity())
                .load(Config.IMAGE_URL + Config.IMAGE_SIZE_W342 + mMovieParcelable.getBackdropPath())
                .into(mTitleImage);

        Picasso.with(getActivity())
                .load(Config.IMAGE_URL + Config.IMAGE_SIZE_W185 + mMovieParcelable.getPosterPath())
                .into(mImageView);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.fab_normal)
    public void onClick(View v){

        MovieSelection movieSelection = new MovieSelection();
        MovieCursor c = movieSelection.query(getActivity().getContentResolver());

        if (c.getCount()!= 0) {
            while (c.moveToNext()) {
                if (mMovieParcelable.getId() == c.getMovieId()) {
                    removeMovieFromFavorite();
                    break;
                } else {
                    addMovieToFavorite();
                }
            }
        } else {
            addMovieToFavorite();
        }

        c.close();
    }

    public void addMovieToFavorite() {
        StringBuilder sb = new StringBuilder();

        sb.append(getString(R.string.add_to_favorite_list))
            .append("\n")
            .append(mMovieParcelable.getTitle());

        Toast.makeText(getActivity(), sb, Toast.LENGTH_LONG).show();
        insertMovie(mMovieParcelable);
        mFAB.setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
        updateReviews();
        updateTrailers();
    }

    public void removeMovieFromFavorite() {
        mFAB.setColorFilter(null);

        MovieSelection movieSelection = new MovieSelection();
        movieSelection.movieId(mMovieParcelable.getId());
        movieSelection.delete(getActivity().getContentResolver());

        ReviewSelection reviewSelection = new ReviewSelection();
        reviewSelection.movieId(mMovieParcelable.getId());
        reviewSelection.delete(getActivity().getContentResolver());

        TrailerSelection trailerSelection = new TrailerSelection();
        trailerSelection.movieId(mMovieParcelable.getId());
        trailerSelection.delete(getActivity().getContentResolver());

        StringBuilder sb = new StringBuilder();

        sb.append(getString(R.string.remove_from_favorite_list))
                .append("\n")
                .append(mMovieParcelable.getTitle());

        Toast.makeText(getActivity(), sb, Toast.LENGTH_LONG).show();
    }

    public void updateTrailers() {
        App.getRestClient().getMovieService().getVideos(mMovieParcelable.getId(), Config.API_KEY, new Callback<VideosResponse>() {
            @Override
            public void success(VideosResponse videosResponse, Response response) {
                if(!videosResponse.getVideoResults().isEmpty()) {
                    insertTrailers(videosResponse.getVideoResults());

                }
            }

            @Override
            public void failure(RetrofitError error) {
                Timber.d(error.getMessage());
            }
        });
    }

    private void insertTrailers(ArrayList<VideoResult> videoResults) {
        Vector<ContentValues> cVVector = new Vector<>(videoResults.size());

        for (int i = 0; i < videoResults.size(); i++) {

            ContentValues values = new ContentValues();
            values.put(TrailerColumns.MOVIE_ID, mMovieParcelable.getId());
            values.put(TrailerColumns.KEY, videoResults.get(i).getKey());
            values.put(TrailerColumns.NAME, videoResults.get(i).getName());
            values.put(TrailerColumns.SITE, videoResults.get(i).getSite());
            values.put(TrailerColumns.SIZE, videoResults.get(i).getSize());
            values.put(TrailerColumns.TYPE, videoResults.get(i).getType());
            cVVector.add(values);
        }

        if (cVVector.size() > 0) {
            ContentValues[] cvArray = new ContentValues[cVVector.size()];
            cVVector.toArray(cvArray);
            getActivity().getContentResolver().bulkInsert(TrailerColumns.CONTENT_URI, cvArray);
        }
    }

    public void updateReviews() {
        App.getRestClient().getMovieService().getReviews(mMovieParcelable.getId(), Config.API_KEY, new Callback<ReviewsResponse>() {
            @Override
            public void success(ReviewsResponse reviewsResponse, Response response) {
                if (!reviewsResponse.getReviewResults().isEmpty()) {
                    insertReview(reviewsResponse.getReviewResults());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Timber.d(error.getMessage());
            }
        });
    }

    private void insertReview(ArrayList<ReviewResult> reviewResults) {

        Vector<ContentValues> cVVector = new Vector<>(reviewResults.size());

        for (int i = 0; i < reviewResults.size(); i++) {

            ContentValues values = new ContentValues();
            values.put(ReviewColumns.MOVIE_ID, mMovieParcelable.getId());
            values.put(ReviewColumns.AUTHOR, reviewResults.get(i).getAuthor());
            values.put(ReviewColumns.CONTENT, reviewResults.get(i).getContent());
            cVVector.add(values);
        }

        if (cVVector.size() > 0) {
                ContentValues[] cvArray = new ContentValues[cVVector.size()];
                cVVector.toArray(cvArray);
                getActivity().getContentResolver().bulkInsert(ReviewColumns.CONTENT_URI, cvArray);
        }
    }

    private long insertMovie(MovieParcelable movies){
        MovieContentValues values = new MovieContentValues();

        values.putMovieId(movies.getId());
        values.putTitle(movies.getTitle());
        values.putBackdropPath(movies.getBackdropPath());
        values.putOverview(movies.getOverview());
        values.putPosterPath(movies.getPosterPath());
        values.putReleaseDate(movies.getReleaseYear());
        values.putVoteAverage(movies.getRating());

        Uri uri = values.insert(getActivity().getContentResolver());
        return ContentUris.parseId(uri);
    }

}
