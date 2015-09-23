package de.k11dev.sklaiber.popularmovies.provider.trailer;

import de.k11dev.sklaiber.popularmovies.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Trailer Table
 */
public interface TrailerModel extends BaseModel {

    /**
     * Get the {@code movie_id} value.
     */
    int getMovieId();

    /**
     * Get the {@code key} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getKey();

    /**
     * Get the {@code name} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getName();

    /**
     * Get the {@code site} value.
     * Can be {@code null}.
     */
    @Nullable
    String getSite();

    /**
     * Get the {@code size} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getSize();

    /**
     * Get the {@code type} value.
     * Can be {@code null}.
     */
    @Nullable
    String getType();
}
