package de.k11dev.sklaiber.popularmovies.provider.movie;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import de.k11dev.sklaiber.popularmovies.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code movie} table.
 */
public class MovieContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return MovieColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable MovieSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable MovieSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public MovieContentValues putMovieId(int value) {
        mContentValues.put(MovieColumns.MOVIE_ID, value);
        return this;
    }


    public MovieContentValues putTitle(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("title must not be null");
        mContentValues.put(MovieColumns.TITLE, value);
        return this;
    }


    public MovieContentValues putOriginalTitle(@Nullable String value) {
        mContentValues.put(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public MovieContentValues putOriginalTitleNull() {
        mContentValues.putNull(MovieColumns.ORIGINAL_TITLE);
        return this;
    }

    public MovieContentValues putOverview(@Nullable String value) {
        mContentValues.put(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieContentValues putOverviewNull() {
        mContentValues.putNull(MovieColumns.OVERVIEW);
        return this;
    }

    public MovieContentValues putReleaseDate(@Nullable String value) {
        mContentValues.put(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieContentValues putReleaseDateNull() {
        mContentValues.putNull(MovieColumns.RELEASE_DATE);
        return this;
    }

    public MovieContentValues putPosterPath(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("posterPath must not be null");
        mContentValues.put(MovieColumns.POSTER_PATH, value);
        return this;
    }


    public MovieContentValues putBackdropPath(@Nullable String value) {
        mContentValues.put(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public MovieContentValues putBackdropPathNull() {
        mContentValues.putNull(MovieColumns.BACKDROP_PATH);
        return this;
    }

    public MovieContentValues putVoteAverage(float value) {
        mContentValues.put(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

}
