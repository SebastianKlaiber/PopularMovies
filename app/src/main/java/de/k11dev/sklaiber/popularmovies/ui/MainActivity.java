package de.k11dev.sklaiber.popularmovies.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.k11dev.sklaiber.popularmovies.Config;
import de.k11dev.sklaiber.popularmovies.R;
import de.k11dev.sklaiber.popularmovies.model.Movie;
import de.k11dev.sklaiber.popularmovies.model.Result;
import de.k11dev.sklaiber.popularmovies.model.SearchResponse;
import de.k11dev.sklaiber.popularmovies.ui.adapter.ImageAdapter;

public class MainActivity extends AppCompatActivity implements GridView.OnItemClickListener {

    public final String LOG_TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.grid) GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mGridView.setOnItemClickListener(this);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(Config.API_URL
                + Config.MOST_POPULAR
                + Config.API_KEY)
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    SearchResponse searchResponse = gson.fromJson(response.body().string(), SearchResponse.class);
                    Result.setResultList(searchResponse.results);

                }
            }
        });

        mGridView.setAdapter(new ImageAdapter(getApplicationContext()));
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(MainActivity.this, MovieDetail.class);

        Movie movie = new Movie(String.valueOf(Result.getResultList().get(position).id)
                , Result.getResultList().get(position).title
                , Result.getResultList().get(position).releaseDate
                , String.valueOf(Result.getResultList().get(position).voteAverage)
                , Result.getResultList().get(position).overview
                , Result.getResultList().get(position).posterPath
                , Result.getResultList().get(position).backdropPath);

        intent.putExtra("movie", movie);
        startActivity(intent);
    }
}
