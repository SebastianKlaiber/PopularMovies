package de.k11dev.sklaiber.popularmovies.provider.review;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import de.k11dev.sklaiber.popularmovies.provider.base.AbstractSelection;
import de.k11dev.sklaiber.popularmovies.provider.movie.MovieColumns;

/**
 * Selection for the {@code review} table.
 */
public class ReviewSelection extends AbstractSelection<ReviewSelection> {
    @Override
    protected Uri baseUri() {
        return ReviewColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code ReviewCursor} object, which is positioned before the first entry, or null.
     */
    public ReviewCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new ReviewCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public ReviewCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code ReviewCursor} object, which is positioned before the first entry, or null.
     */
    public ReviewCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new ReviewCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public ReviewCursor query(Context context) {
        return query(context, null);
    }


    public ReviewSelection id(long... value) {
        addEquals("review." + ReviewColumns._ID, toObjectArray(value));
        return this;
    }

    public ReviewSelection idNot(long... value) {
        addNotEquals("review." + ReviewColumns._ID, toObjectArray(value));
        return this;
    }

    public ReviewSelection orderById(boolean desc) {
        orderBy("review." + ReviewColumns._ID, desc);
        return this;
    }

    public ReviewSelection orderById() {
        return orderById(false);
    }

    public ReviewSelection movieId(int... value) {
        addEquals(ReviewColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public ReviewSelection movieIdNot(int... value) {
        addNotEquals(ReviewColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public ReviewSelection movieIdGt(int value) {
        addGreaterThan(ReviewColumns.MOVIE_ID, value);
        return this;
    }

    public ReviewSelection movieIdGtEq(int value) {
        addGreaterThanOrEquals(ReviewColumns.MOVIE_ID, value);
        return this;
    }

    public ReviewSelection movieIdLt(int value) {
        addLessThan(ReviewColumns.MOVIE_ID, value);
        return this;
    }

    public ReviewSelection movieIdLtEq(int value) {
        addLessThanOrEquals(ReviewColumns.MOVIE_ID, value);
        return this;
    }

    public ReviewSelection orderByMovieId(boolean desc) {
        orderBy(ReviewColumns.MOVIE_ID, desc);
        return this;
    }

    public ReviewSelection orderByMovieId() {
        orderBy(ReviewColumns.MOVIE_ID, false);
        return this;
    }

    public ReviewSelection movieMovieId(int... value) {
        addEquals(MovieColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public ReviewSelection movieMovieIdNot(int... value) {
        addNotEquals(MovieColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public ReviewSelection movieMovieIdGt(int value) {
        addGreaterThan(MovieColumns.MOVIE_ID, value);
        return this;
    }

    public ReviewSelection movieMovieIdGtEq(int value) {
        addGreaterThanOrEquals(MovieColumns.MOVIE_ID, value);
        return this;
    }

    public ReviewSelection movieMovieIdLt(int value) {
        addLessThan(MovieColumns.MOVIE_ID, value);
        return this;
    }

    public ReviewSelection movieMovieIdLtEq(int value) {
        addLessThanOrEquals(MovieColumns.MOVIE_ID, value);
        return this;
    }

    public ReviewSelection orderByMovieMovieId(boolean desc) {
        orderBy(MovieColumns.MOVIE_ID, desc);
        return this;
    }

    public ReviewSelection orderByMovieMovieId() {
        orderBy(MovieColumns.MOVIE_ID, false);
        return this;
    }

    public ReviewSelection movieTitle(String... value) {
        addEquals(MovieColumns.TITLE, value);
        return this;
    }

    public ReviewSelection movieTitleNot(String... value) {
        addNotEquals(MovieColumns.TITLE, value);
        return this;
    }

    public ReviewSelection movieTitleLike(String... value) {
        addLike(MovieColumns.TITLE, value);
        return this;
    }

    public ReviewSelection movieTitleContains(String... value) {
        addContains(MovieColumns.TITLE, value);
        return this;
    }

    public ReviewSelection movieTitleStartsWith(String... value) {
        addStartsWith(MovieColumns.TITLE, value);
        return this;
    }

    public ReviewSelection movieTitleEndsWith(String... value) {
        addEndsWith(MovieColumns.TITLE, value);
        return this;
    }

    public ReviewSelection orderByMovieTitle(boolean desc) {
        orderBy(MovieColumns.TITLE, desc);
        return this;
    }

    public ReviewSelection orderByMovieTitle() {
        orderBy(MovieColumns.TITLE, false);
        return this;
    }

    public ReviewSelection movieOriginalTitle(String... value) {
        addEquals(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public ReviewSelection movieOriginalTitleNot(String... value) {
        addNotEquals(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public ReviewSelection movieOriginalTitleLike(String... value) {
        addLike(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public ReviewSelection movieOriginalTitleContains(String... value) {
        addContains(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public ReviewSelection movieOriginalTitleStartsWith(String... value) {
        addStartsWith(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public ReviewSelection movieOriginalTitleEndsWith(String... value) {
        addEndsWith(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public ReviewSelection orderByMovieOriginalTitle(boolean desc) {
        orderBy(MovieColumns.ORIGINAL_TITLE, desc);
        return this;
    }

    public ReviewSelection orderByMovieOriginalTitle() {
        orderBy(MovieColumns.ORIGINAL_TITLE, false);
        return this;
    }

    public ReviewSelection movieOverview(String... value) {
        addEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public ReviewSelection movieOverviewNot(String... value) {
        addNotEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public ReviewSelection movieOverviewLike(String... value) {
        addLike(MovieColumns.OVERVIEW, value);
        return this;
    }

    public ReviewSelection movieOverviewContains(String... value) {
        addContains(MovieColumns.OVERVIEW, value);
        return this;
    }

    public ReviewSelection movieOverviewStartsWith(String... value) {
        addStartsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public ReviewSelection movieOverviewEndsWith(String... value) {
        addEndsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public ReviewSelection orderByMovieOverview(boolean desc) {
        orderBy(MovieColumns.OVERVIEW, desc);
        return this;
    }

    public ReviewSelection orderByMovieOverview() {
        orderBy(MovieColumns.OVERVIEW, false);
        return this;
    }

    public ReviewSelection movieReleaseDate(String... value) {
        addEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public ReviewSelection movieReleaseDateNot(String... value) {
        addNotEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public ReviewSelection movieReleaseDateLike(String... value) {
        addLike(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public ReviewSelection movieReleaseDateContains(String... value) {
        addContains(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public ReviewSelection movieReleaseDateStartsWith(String... value) {
        addStartsWith(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public ReviewSelection movieReleaseDateEndsWith(String... value) {
        addEndsWith(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public ReviewSelection orderByMovieReleaseDate(boolean desc) {
        orderBy(MovieColumns.RELEASE_DATE, desc);
        return this;
    }

    public ReviewSelection orderByMovieReleaseDate() {
        orderBy(MovieColumns.RELEASE_DATE, false);
        return this;
    }

    public ReviewSelection moviePosterPath(String... value) {
        addEquals(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public ReviewSelection moviePosterPathNot(String... value) {
        addNotEquals(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public ReviewSelection moviePosterPathLike(String... value) {
        addLike(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public ReviewSelection moviePosterPathContains(String... value) {
        addContains(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public ReviewSelection moviePosterPathStartsWith(String... value) {
        addStartsWith(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public ReviewSelection moviePosterPathEndsWith(String... value) {
        addEndsWith(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public ReviewSelection orderByMoviePosterPath(boolean desc) {
        orderBy(MovieColumns.POSTER_PATH, desc);
        return this;
    }

    public ReviewSelection orderByMoviePosterPath() {
        orderBy(MovieColumns.POSTER_PATH, false);
        return this;
    }

    public ReviewSelection movieBackdropPath(String... value) {
        addEquals(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public ReviewSelection movieBackdropPathNot(String... value) {
        addNotEquals(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public ReviewSelection movieBackdropPathLike(String... value) {
        addLike(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public ReviewSelection movieBackdropPathContains(String... value) {
        addContains(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public ReviewSelection movieBackdropPathStartsWith(String... value) {
        addStartsWith(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public ReviewSelection movieBackdropPathEndsWith(String... value) {
        addEndsWith(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public ReviewSelection orderByMovieBackdropPath(boolean desc) {
        orderBy(MovieColumns.BACKDROP_PATH, desc);
        return this;
    }

    public ReviewSelection orderByMovieBackdropPath() {
        orderBy(MovieColumns.BACKDROP_PATH, false);
        return this;
    }

    public ReviewSelection movieVoteAverage(float... value) {
        addEquals(MovieColumns.VOTE_AVERAGE, toObjectArray(value));
        return this;
    }

    public ReviewSelection movieVoteAverageNot(float... value) {
        addNotEquals(MovieColumns.VOTE_AVERAGE, toObjectArray(value));
        return this;
    }

    public ReviewSelection movieVoteAverageGt(float value) {
        addGreaterThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public ReviewSelection movieVoteAverageGtEq(float value) {
        addGreaterThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public ReviewSelection movieVoteAverageLt(float value) {
        addLessThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public ReviewSelection movieVoteAverageLtEq(float value) {
        addLessThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public ReviewSelection orderByMovieVoteAverage(boolean desc) {
        orderBy(MovieColumns.VOTE_AVERAGE, desc);
        return this;
    }

    public ReviewSelection orderByMovieVoteAverage() {
        orderBy(MovieColumns.VOTE_AVERAGE, false);
        return this;
    }

    public ReviewSelection author(String... value) {
        addEquals(ReviewColumns.AUTHOR, value);
        return this;
    }

    public ReviewSelection authorNot(String... value) {
        addNotEquals(ReviewColumns.AUTHOR, value);
        return this;
    }

    public ReviewSelection authorLike(String... value) {
        addLike(ReviewColumns.AUTHOR, value);
        return this;
    }

    public ReviewSelection authorContains(String... value) {
        addContains(ReviewColumns.AUTHOR, value);
        return this;
    }

    public ReviewSelection authorStartsWith(String... value) {
        addStartsWith(ReviewColumns.AUTHOR, value);
        return this;
    }

    public ReviewSelection authorEndsWith(String... value) {
        addEndsWith(ReviewColumns.AUTHOR, value);
        return this;
    }

    public ReviewSelection orderByAuthor(boolean desc) {
        orderBy(ReviewColumns.AUTHOR, desc);
        return this;
    }

    public ReviewSelection orderByAuthor() {
        orderBy(ReviewColumns.AUTHOR, false);
        return this;
    }

    public ReviewSelection content(String... value) {
        addEquals(ReviewColumns.CONTENT, value);
        return this;
    }

    public ReviewSelection contentNot(String... value) {
        addNotEquals(ReviewColumns.CONTENT, value);
        return this;
    }

    public ReviewSelection contentLike(String... value) {
        addLike(ReviewColumns.CONTENT, value);
        return this;
    }

    public ReviewSelection contentContains(String... value) {
        addContains(ReviewColumns.CONTENT, value);
        return this;
    }

    public ReviewSelection contentStartsWith(String... value) {
        addStartsWith(ReviewColumns.CONTENT, value);
        return this;
    }

    public ReviewSelection contentEndsWith(String... value) {
        addEndsWith(ReviewColumns.CONTENT, value);
        return this;
    }

    public ReviewSelection orderByContent(boolean desc) {
        orderBy(ReviewColumns.CONTENT, desc);
        return this;
    }

    public ReviewSelection orderByContent() {
        orderBy(ReviewColumns.CONTENT, false);
        return this;
    }
}
