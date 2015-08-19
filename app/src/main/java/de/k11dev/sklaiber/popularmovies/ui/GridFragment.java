package de.k11dev.sklaiber.popularmovies.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.k11dev.sklaiber.popularmovies.Config;
import de.k11dev.sklaiber.popularmovies.FetchMovieData;
import de.k11dev.sklaiber.popularmovies.R;
import de.k11dev.sklaiber.popularmovies.model.Movie;
import de.k11dev.sklaiber.popularmovies.model.Result;
import de.k11dev.sklaiber.popularmovies.ui.adapter.ImageAdapter;

/**
 * Created by sklaiber on 19.08.15.
 */
public class GridFragment extends Fragment implements GridView.OnItemClickListener{

    private static ImageAdapter mAdapter;

    @Bind(R.id.grid) GridView mGridView;

    public interface Callback {
        public void onItemSelected(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_grid, container, false);

        ButterKnife.bind(this, rootView);

        mAdapter = new ImageAdapter(getActivity());

        mGridView.setOnItemClickListener(this);
        mGridView.setAdapter(mAdapter);

        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((Callback) getActivity()).onItemSelected(position);
    }

    public static void updateList() {
        mAdapter.updateMovieList(Result.getResultList());
//        mAdapter.notifyDataSetChanged();
    }
}
