package de.k11dev.sklaiber.popularmovies.ui;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import de.k11dev.sklaiber.popularmovies.R;
import de.k11dev.sklaiber.popularmovies.model.MovieParcelable;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        MovieParcelable movieParcelable = getIntent().getParcelableExtra(MainActivity.KEY_MOVIE_PARCELABLE);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.detail_container, DetailFragment.newInstance(movieParcelable));
        ft.commit();
    }
}
