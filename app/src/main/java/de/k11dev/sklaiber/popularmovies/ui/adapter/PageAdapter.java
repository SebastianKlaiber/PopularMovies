package de.k11dev.sklaiber.popularmovies.ui.adapter;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentStatePagerAdapter;

import de.k11dev.sklaiber.popularmovies.R;
import de.k11dev.sklaiber.popularmovies.model.MovieParcelable;
import de.k11dev.sklaiber.popularmovies.ui.fragment.DetailFragment;
import de.k11dev.sklaiber.popularmovies.ui.fragment.ReviewFragment;
import de.k11dev.sklaiber.popularmovies.ui.fragment.VideoFragment;

/**
 * Created by sklaiber on 30.08.15.
 */
public class PageAdapter extends FragmentStatePagerAdapter {

    private MovieParcelable mMovieParcelable;
    private Context mContext;

    public PageAdapter(Context context, FragmentManager fm, MovieParcelable movieParcelable) {
        super(fm);
        this.mContext = context;
        this.mMovieParcelable = movieParcelable;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return DetailFragment.newInstance(mMovieParcelable);
            case 1:
                return VideoFragment.newInstance(mMovieParcelable);
            case 2:
                return ReviewFragment.newInstance(mMovieParcelable);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.view_pager_overview);
            case 1:
                return mContext.getString(R.string.view_pager_trailer);
            case 2:
                return mContext.getString(R.string.view_pager_review);
            default:
                return null;
        }
    }
}
