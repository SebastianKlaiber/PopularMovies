package de.k11dev.sklaiber.popularmovies.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.k11dev.sklaiber.popularmovies.Config;
import de.k11dev.sklaiber.popularmovies.R;
import de.k11dev.sklaiber.popularmovies.model.Movie;
import de.k11dev.sklaiber.popularmovies.model.Result;

public class MovieDetail extends AppCompatActivity {

    @Bind(R.id.title) TextView mTitleTv;
    @Bind(R.id.release_date) TextView  mReleaseDateTv;
    @Bind(R.id.vote_average) TextView  mVoteAverageTv;
    @Bind(R.id.description)  TextView  mDescriptionTv;
    @Bind(R.id.detail_image) ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ButterKnife.bind(this);

        Movie movie = getIntent().getParcelableExtra("movie");

        mTitleTv.setText(movie.getTitle());
        mDescriptionTv.setText(movie.getOverview());
        mVoteAverageTv.setText(movie.getRating());
        mReleaseDateTv.setText(movie.getReleaseYear());

        Picasso.with(this)
                .load(Config.IMAGE_URL_SIZE + movie.getPosterPath())
                .into(mImageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_detail, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
