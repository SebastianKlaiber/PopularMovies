package de.k11dev.sklaiber.popularmovies.ui;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import butterknife.ButterKnife;
import de.k11dev.sklaiber.popularmovies.Config;
import de.k11dev.sklaiber.popularmovies.FetchMovieData;
import de.k11dev.sklaiber.popularmovies.R;
import de.k11dev.sklaiber.popularmovies.model.Movie;
import de.k11dev.sklaiber.popularmovies.model.Result;
import de.k11dev.sklaiber.popularmovies.model.SearchResponse;
import de.k11dev.sklaiber.popularmovies.ui.adapter.ImageAdapter;

public class MainActivity extends AppCompatActivity implements GridFragment.Callback{

    public final String LOG_TAG = MainActivity.class.getSimpleName();

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

//        FetchMovieData.getMovieData(Config.MOST_POPULAR);

//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.replace(R.id.grid_container, new GridFragment());
//        ft.commit();

        if (findViewById(R.id.detail_container) != null) {
            mTwoPane = true;
        } else {
            mTwoPane = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String sort = sharedPreferences.getString(getString(R.string.pref_sort_key),
                getString(R.string.pref_sort_popularity));

        if (sort.equals(getString(R.string.pref_sort_popularity))) {
            FetchMovieData.getMovieData(Config.MOST_POPULAR);
            GridFragment.updateList();
        } else {
            FetchMovieData.getMovieData(Config.HIGHEST_RATED);
            GridFragment.updateList();
        }

    }

    @Override
    public void onItemSelected(int position) {
        if (mTwoPane) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.detail_container, DetailFragment.newInstance(FetchMovieData.getMovieParcable(position)))
                    .commit();
        } else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("movie", FetchMovieData.getMovieParcable(position));
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
