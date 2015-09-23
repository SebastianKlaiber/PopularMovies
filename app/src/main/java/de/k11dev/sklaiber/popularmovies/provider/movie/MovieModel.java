package de.k11dev.sklaiber.popularmovies.provider.movie;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import de.k11dev.sklaiber.popularmovies.provider.base.BaseModel;

/**
 * Movie Table
 */
public interface MovieModel extends BaseModel {

    /**
     * Get the {@code movie_id} value.
     */
    int getMovieId();

    /**
     * Get the {@code title} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getTitle();

    /**
     * Get the {@code original_title} value.
     * Can be {@code null}.
     */
    @Nullable
    String getOriginalTitle();

    /**
     * Get the {@code overview} value.
     * Can be {@code null}.
     */
    @Nullable
    String getOverview();

    /**
     * Get the {@code release_date} value.
     * Can be {@code null}.
     */
    @Nullable
    String getReleaseDate();

    /**
     * Get the {@code poster_path} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getPosterPath();

    /**
     * Get the {@code backdrop_path} value.
     * Can be {@code null}.
     */
    @Nullable
    String getBackdropPath();

    /**
     * Get the {@code vote_average} value.
     */
    float getVoteAverage();
}
