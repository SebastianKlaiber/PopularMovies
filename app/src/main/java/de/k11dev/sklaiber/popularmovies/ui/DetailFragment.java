package de.k11dev.sklaiber.popularmovies.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.k11dev.sklaiber.popularmovies.Config;
import de.k11dev.sklaiber.popularmovies.R;
import de.k11dev.sklaiber.popularmovies.model.Movie;

/**
 * Created by sklaiber on 19.08.15.
 */
public class DetailFragment extends Fragment {

    private Movie mMovie;

    @Bind(R.id.title) TextView mTitleTv;
    @Bind(R.id.release_date) TextView  mReleaseDateTv;
    @Bind(R.id.vote_average) TextView  mVoteAverageTv;
    @Bind(R.id.description)  TextView  mDescriptionTv;
    @Bind(R.id.detail_image) ImageView mImageView;

    public static DetailFragment newInstance(Movie movie) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("movie", movie);
        detailFragment.setArguments(args);
        return detailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMovie = getArguments().getParcelable("movie");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        ButterKnife.bind(this, rootView);

        mTitleTv.setText(mMovie.getTitle());
        mDescriptionTv.setText(mMovie.getOverview());
        mVoteAverageTv.setText((this.getString(R.string.format_rating, mMovie.getRating())));
        mReleaseDateTv.setText(mMovie.getReleaseYear());

        Picasso.with(getActivity())
                .load(Config.IMAGE_URL_SIZE + mMovie.getPosterPath())
                .into(mImageView);
        return rootView;
    }
}
