package de.k11dev.sklaiber.popularmovies.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.util.MutableBoolean;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Timer;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.k11dev.sklaiber.popularmovies.Config;
import de.k11dev.sklaiber.popularmovies.R;
import de.k11dev.sklaiber.popularmovies.Utility;
import de.k11dev.sklaiber.popularmovies.model.MovieParcelable;
import de.k11dev.sklaiber.popularmovies.ui.activity.MainActivity;
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

        ArrayList<String> strings = Utility.getMovies(getActivity());
        for (int i = 0; i < strings.size(); i++) {
            if (mMovieParcelable.getIdParc().equals(strings.get(i))) {
                mFAB.setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
            }
        }

        mTitleTv.setText(mMovieParcelable.getTitleParc());
        mDescriptionTv.setText(mMovieParcelable.getOverview());
        mReleaseDateTv.setText(mMovieParcelable.getReleaseYear());

        mRatingBar.setRating(Float.valueOf(mMovieParcelable.getRating()));

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
        ArrayList<String> strings = Utility.getMovies(getActivity());
        if (strings.size() != 0) {
            for (int i = 0; i < strings.size(); i++) {
                if (mMovieParcelable.getIdParc().equals(strings.get(i))) {
                    Utility.removeMovie(getActivity(), mMovieParcelable.getIdParc());
                    mFAB.setColorFilter(null);
                    Toast.makeText(getActivity(), getString(R.string.remove_from_favorite_list) + mMovieParcelable.getTitleParc(), Toast.LENGTH_LONG).show();
                } else {
                    Utility.addMovieId(getActivity(), mMovieParcelable.getIdParc());
                    Toast.makeText(getActivity(), getString(R.string.add_to_favorite_list) + mMovieParcelable.getTitleParc(), Toast.LENGTH_LONG).show();
                    mFAB.setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
                }
            }
        } else {
            Utility.addMovieId(getActivity(), mMovieParcelable.getIdParc());
            Toast.makeText(getActivity(), getString(R.string.add_to_favorite_list), Toast.LENGTH_LONG).show();
            mFAB.setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
        }
    }


}
