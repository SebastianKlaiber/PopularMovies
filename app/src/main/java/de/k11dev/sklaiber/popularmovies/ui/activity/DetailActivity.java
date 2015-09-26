package de.k11dev.sklaiber.popularmovies.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.k11dev.sklaiber.popularmovies.R;
import de.k11dev.sklaiber.popularmovies.model.MovieParcelable;
import de.k11dev.sklaiber.popularmovies.ui.adapter.PageAdapter;

public class DetailActivity extends AppCompatActivity{

    private MovieParcelable mMovieParcelable;

    @Bind(R.id.pager) ViewPager mViewPager;
    @Bind(R.id.tab_layout) TabLayout mTabLayout;
    @Bind(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mMovieParcelable = getIntent().getParcelableExtra(MainActivity.KEY_MOVIE_PARCELABLE);

        mViewPager.setAdapter(new PageAdapter(this, getFragmentManager(), mMovieParcelable));
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
