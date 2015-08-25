package de.k11dev.sklaiber.popularmovies.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.ButterKnife;
import de.k11dev.sklaiber.popularmovies.R;
import de.k11dev.sklaiber.popularmovies.model.Movie;
import de.k11dev.sklaiber.popularmovies.model.MovieParcelable;
import de.k11dev.sklaiber.popularmovies.model.Result;
import de.k11dev.sklaiber.popularmovies.ui.fragment.DetailFragment;
import de.k11dev.sklaiber.popularmovies.ui.fragment.GridFragment;

public class MainActivity extends AppCompatActivity implements GridFragment.Callback {

    public final String LOG_TAG = MainActivity.class.getSimpleName();

    public final static String KEY_MOVIE_PARCELABLE = "movie";

    private static ArrayList<Result> mMovies = new ArrayList<>();

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mTwoPane = findViewById(R.id.detail_container) != null;

        GridFragment fragment = new GridFragment();

        getFragmentManager().beginTransaction()
                .replace(R.id.grid_fragment, fragment)
                .commit();
    }

    public void setList(ArrayList<Result> movies){
        mMovies.clear();
        mMovies.addAll(movies);
    }

    @Override
    public void onItemSelected(int position) {
        MovieParcelable movieParcelable = new MovieParcelable(
                mMovies.get(position).getId(),
                mMovies.get(position).getTitle(),
                mMovies.get(position).getReleaseDate(),
                mMovies.get(position).getVoteAverage(),
                mMovies.get(position).getOverview(),
                mMovies.get(position).getPosterPath(),
                mMovies.get(position).getBackdropPath());

        if (mTwoPane) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.detail_container, DetailFragment.newInstance(movieParcelable))
                    .commit();
        } else {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra(KEY_MOVIE_PARCELABLE, movieParcelable);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
