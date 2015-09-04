package de.k11dev.sklaiber.popularmovies.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
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
            holder = new ViewHolder(gridView);
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
        @Bind(R.id.grid_item_image) ImageView imageView;
//        public ImageView imageView;
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
