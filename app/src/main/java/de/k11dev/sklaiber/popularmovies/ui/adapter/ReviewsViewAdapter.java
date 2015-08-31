package de.k11dev.sklaiber.popularmovies.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import de.k11dev.sklaiber.popularmovies.R;
import de.k11dev.sklaiber.popularmovies.model.ReviewResult;

/**
 * Created by sklaiber on 31.08.15.
 */
public class ReviewsViewAdapter extends RecyclerView.Adapter<ReviewsViewAdapter.ViewHolder> {

    private ArrayList<ReviewResult> mReviewResults = new ArrayList<>();

    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView content;
        private TextView author;

        public ViewHolder(View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.content_tv);
            author = (TextView) itemView.findViewById(R.id.author_tv);
        }
    }

    public ReviewsViewAdapter(Context context, ArrayList<ReviewResult> reviewResults) {
        this.mContext = context;
        this.mReviewResults = reviewResults;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.author.setText(mReviewResults.get(position).getAuthor());
        holder.content.setText(mReviewResults.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return mReviewResults.size();
    }
}
