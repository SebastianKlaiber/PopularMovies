package de.k11dev.sklaiber.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.k11dev.sklaiber.popularmovies.model.Result;
import de.k11dev.sklaiber.popularmovies.model.SearchResponse;

public class MainActivity extends AppCompatActivity {

    public final String LOG_TAG = MainActivity.class.getSimpleName();

    private List<String> mMovieList = new ArrayList<String>();

//    @Bind(R.id.image)    ImageView mImageView;
//    @Bind(R.id.gridview) GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

//        mGridView.setAdapter(new ImageAdapter(this));

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
                    Log.d(LOG_TAG, searchResponse.getList().toString());
//                    List<Result> results = searchResponse.results;
//
//                    for (Result result : results) {
//                        mMovieList.add(result.posterPath);
//                    }
                }
            }
        });

//        Picasso.with(this)
//                .load(Config.IMAGE_URL_SIZE + "/7SGGUiTE6oc2fh9MjIk5M00dsQd.jpg")
//                .into(mImageView);
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
}
