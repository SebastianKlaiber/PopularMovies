package de.k11dev.sklaiber.popularmovies.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.k11dev.sklaiber.popularmovies.Config;
import de.k11dev.sklaiber.popularmovies.R;
import de.k11dev.sklaiber.popularmovies.model.Result;

/**
 * Created by sklaiber on 24.08.15.
 */
public class ImageArrayAdapter extends ArrayAdapter<Result> {

    private ArrayList<Result> mList;

    public ImageArrayAdapter(Context context, ArrayList<Result> list) {
        super(context, 0, list);
        mList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View gridView;
        ViewHolder holder;

        if (convertView == null) {
            gridView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);

            holder = new ViewHolder();
            holder.imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);

            gridView.setTag(holder);
        } else {
            gridView = convertView;
            holder = (ViewHolder) gridView.getTag();
        }

        Uri builtUri  = Uri.parse(Config.IMAGE_URL).buildUpon()
                .appendEncodedPath(Config.IMAGE_SIZE_W185)
                .appendEncodedPath(mList.get(position).getPosterPath())
                .build();

        Picasso.with(getContext())
                .load(builtUri)
                .into(holder.imageView);

        return gridView;
    }

    class ViewHolder {
        public ImageView imageView;
    }
}
