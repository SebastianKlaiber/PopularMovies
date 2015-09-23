package de.k11dev.sklaiber.popularmovies.provider.review;

import android.support.annotation.NonNull;

import de.k11dev.sklaiber.popularmovies.provider.base.BaseModel;

/**
 * Review Table
 */
public interface ReviewModel extends BaseModel {

    /**
     * Get the {@code movie_id} value.
     */
    int getMovieId();

    /**
     * Get the {@code author} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getAuthor();

    /**
     * Get the {@code content} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getContent();
}
