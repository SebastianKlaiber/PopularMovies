package de.k11dev.sklaiber.popularmovies.ui.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.k11dev.sklaiber.popularmovies.Config;
import de.k11dev.sklaiber.popularmovies.R;
import de.k11dev.sklaiber.popularmovies.model.MovieParcelable;
import de.k11dev.sklaiber.popularmovies.ui.activity.MainActivity;

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

        mTitleTv.setText(mMovieParcelable.getTitleParc());
        mDescriptionTv.setText(mMovieParcelable.getOverview());
        mReleaseDateTv.setText(mMovieParcelable.getReleaseYear());

        mRatingBar.setRating(Float.valueOf(mMovieParcelable.getRating()));

        LayerDrawable stars = (LayerDrawable) mRatingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        Picasso.with(getActivity())
                .load(Config.IMAGE_URL + Config.IMAGE_SIZE_W342 + mMovieParcelable.getBackdropPath())
                .into(mTitleImage);

        Picasso.with(getActivity())
                .load(Config.IMAGE_URL + Config.IMAGE_SIZE_W185 + mMovieParcelable.getPosterPath())
                .into(mImageView);
        return rootView;
    }
}
