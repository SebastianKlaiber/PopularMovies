package de.k11dev.sklaiber.popularmovies.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.k11dev.sklaiber.popularmovies.Config;
import de.k11dev.sklaiber.popularmovies.R;
import de.k11dev.sklaiber.popularmovies.model.Movie;
import de.k11dev.sklaiber.popularmovies.model.Result;

/**
 * Created by sklaiber on 18.08.15.
 */
public class ImageAdapter extends BaseAdapter {

    private Context mContext;

    public ImageAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return Result.getResultList().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {
            gridView = inflater.inflate(R.layout.item, null);

            ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);

            Picasso.with(mContext)
                .load(Config.IMAGE_URL_SIZE + Result.getResultList().get(position).posterPath)
                .into(imageView);

        } else {
            gridView = convertView;
        }

        return gridView;
    }

    public void updateMovieList(List<Result> results) {
        Result.getResultList().clear();
        Result.setResultList(results);
        this.notifyDataSetChanged();
    }
}
