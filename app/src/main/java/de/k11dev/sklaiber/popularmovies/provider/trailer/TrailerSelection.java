package de.k11dev.sklaiber.popularmovies.provider.trailer;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import de.k11dev.sklaiber.popularmovies.provider.base.AbstractSelection;
import de.k11dev.sklaiber.popularmovies.provider.movie.*;

/**
 * Selection for the {@code trailer} table.
 */
public class TrailerSelection extends AbstractSelection<TrailerSelection> {
    @Override
    protected Uri baseUri() {
        return TrailerColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code TrailerCursor} object, which is positioned before the first entry, or null.
     */
    public TrailerCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new TrailerCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public TrailerCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code TrailerCursor} object, which is positioned before the first entry, or null.
     */
    public TrailerCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new TrailerCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public TrailerCursor query(Context context) {
        return query(context, null);
    }


    public TrailerSelection id(long... value) {
        addEquals("trailer." + TrailerColumns._ID, toObjectArray(value));
        return this;
    }

    public TrailerSelection idNot(long... value) {
        addNotEquals("trailer." + TrailerColumns._ID, toObjectArray(value));
        return this;
    }

    public TrailerSelection orderById(boolean desc) {
        orderBy("trailer." + TrailerColumns._ID, desc);
        return this;
    }

    public TrailerSelection orderById() {
        return orderById(false);
    }

    public TrailerSelection movieId(int... value) {
        addEquals(TrailerColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public TrailerSelection movieIdNot(int... value) {
        addNotEquals(TrailerColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public TrailerSelection movieIdGt(int value) {
        addGreaterThan(TrailerColumns.MOVIE_ID, value);
        return this;
    }

    public TrailerSelection movieIdGtEq(int value) {
        addGreaterThanOrEquals(TrailerColumns.MOVIE_ID, value);
        return this;
    }

    public TrailerSelection movieIdLt(int value) {
        addLessThan(TrailerColumns.MOVIE_ID, value);
        return this;
    }

    public TrailerSelection movieIdLtEq(int value) {
        addLessThanOrEquals(TrailerColumns.MOVIE_ID, value);
        return this;
    }

    public TrailerSelection orderByMovieId(boolean desc) {
        orderBy(TrailerColumns.MOVIE_ID, desc);
        return this;
    }

    public TrailerSelection orderByMovieId() {
        orderBy(TrailerColumns.MOVIE_ID, false);
        return this;
    }

    public TrailerSelection movieMovieId(int... value) {
        addEquals(MovieColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public TrailerSelection movieMovieIdNot(int... value) {
        addNotEquals(MovieColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public TrailerSelection movieMovieIdGt(int value) {
        addGreaterThan(MovieColumns.MOVIE_ID, value);
        return this;
    }

    public TrailerSelection movieMovieIdGtEq(int value) {
        addGreaterThanOrEquals(MovieColumns.MOVIE_ID, value);
        return this;
    }

    public TrailerSelection movieMovieIdLt(int value) {
        addLessThan(MovieColumns.MOVIE_ID, value);
        return this;
    }

    public TrailerSelection movieMovieIdLtEq(int value) {
        addLessThanOrEquals(MovieColumns.MOVIE_ID, value);
        return this;
    }

    public TrailerSelection orderByMovieMovieId(boolean desc) {
        orderBy(MovieColumns.MOVIE_ID, desc);
        return this;
    }

    public TrailerSelection orderByMovieMovieId() {
        orderBy(MovieColumns.MOVIE_ID, false);
        return this;
    }

    public TrailerSelection movieTitle(String... value) {
        addEquals(MovieColumns.TITLE, value);
        return this;
    }

    public TrailerSelection movieTitleNot(String... value) {
        addNotEquals(MovieColumns.TITLE, value);
        return this;
    }

    public TrailerSelection movieTitleLike(String... value) {
        addLike(MovieColumns.TITLE, value);
        return this;
    }

    public TrailerSelection movieTitleContains(String... value) {
        addContains(MovieColumns.TITLE, value);
        return this;
    }

    public TrailerSelection movieTitleStartsWith(String... value) {
        addStartsWith(MovieColumns.TITLE, value);
        return this;
    }

    public TrailerSelection movieTitleEndsWith(String... value) {
        addEndsWith(MovieColumns.TITLE, value);
        return this;
    }

    public TrailerSelection orderByMovieTitle(boolean desc) {
        orderBy(MovieColumns.TITLE, desc);
        return this;
    }

    public TrailerSelection orderByMovieTitle() {
        orderBy(MovieColumns.TITLE, false);
        return this;
    }

    public TrailerSelection movieOriginalTitle(String... value) {
        addEquals(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public TrailerSelection movieOriginalTitleNot(String... value) {
        addNotEquals(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public TrailerSelection movieOriginalTitleLike(String... value) {
        addLike(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public TrailerSelection movieOriginalTitleContains(String... value) {
        addContains(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public TrailerSelection movieOriginalTitleStartsWith(String... value) {
        addStartsWith(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public TrailerSelection movieOriginalTitleEndsWith(String... value) {
        addEndsWith(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public TrailerSelection orderByMovieOriginalTitle(boolean desc) {
        orderBy(MovieColumns.ORIGINAL_TITLE, desc);
        return this;
    }

    public TrailerSelection orderByMovieOriginalTitle() {
        orderBy(MovieColumns.ORIGINAL_TITLE, false);
        return this;
    }

    public TrailerSelection movieOverview(String... value) {
        addEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public TrailerSelection movieOverviewNot(String... value) {
        addNotEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public TrailerSelection movieOverviewLike(String... value) {
        addLike(MovieColumns.OVERVIEW, value);
        return this;
    }

    public TrailerSelection movieOverviewContains(String... value) {
        addContains(MovieColumns.OVERVIEW, value);
        return this;
    }

    public TrailerSelection movieOverviewStartsWith(String... value) {
        addStartsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public TrailerSelection movieOverviewEndsWith(String... value) {
        addEndsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public TrailerSelection orderByMovieOverview(boolean desc) {
        orderBy(MovieColumns.OVERVIEW, desc);
        return this;
    }

    public TrailerSelection orderByMovieOverview() {
        orderBy(MovieColumns.OVERVIEW, false);
        return this;
    }

    public TrailerSelection movieReleaseDate(String... value) {
        addEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public TrailerSelection movieReleaseDateNot(String... value) {
        addNotEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public TrailerSelection movieReleaseDateLike(String... value) {
        addLike(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public TrailerSelection movieReleaseDateContains(String... value) {
        addContains(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public TrailerSelection movieReleaseDateStartsWith(String... value) {
        addStartsWith(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public TrailerSelection movieReleaseDateEndsWith(String... value) {
        addEndsWith(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public TrailerSelection orderByMovieReleaseDate(boolean desc) {
        orderBy(MovieColumns.RELEASE_DATE, desc);
        return this;
    }

    public TrailerSelection orderByMovieReleaseDate() {
        orderBy(MovieColumns.RELEASE_DATE, false);
        return this;
    }

    public TrailerSelection moviePosterPath(String... value) {
        addEquals(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public TrailerSelection moviePosterPathNot(String... value) {
        addNotEquals(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public TrailerSelection moviePosterPathLike(String... value) {
        addLike(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public TrailerSelection moviePosterPathContains(String... value) {
        addContains(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public TrailerSelection moviePosterPathStartsWith(String... value) {
        addStartsWith(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public TrailerSelection moviePosterPathEndsWith(String... value) {
        addEndsWith(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public TrailerSelection orderByMoviePosterPath(boolean desc) {
        orderBy(MovieColumns.POSTER_PATH, desc);
        return this;
    }

    public TrailerSelection orderByMoviePosterPath() {
        orderBy(MovieColumns.POSTER_PATH, false);
        return this;
    }

    public TrailerSelection movieBackdropPath(String... value) {
        addEquals(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public TrailerSelection movieBackdropPathNot(String... value) {
        addNotEquals(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public TrailerSelection movieBackdropPathLike(String... value) {
        addLike(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public TrailerSelection movieBackdropPathContains(String... value) {
        addContains(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public TrailerSelection movieBackdropPathStartsWith(String... value) {
        addStartsWith(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public TrailerSelection movieBackdropPathEndsWith(String... value) {
        addEndsWith(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public TrailerSelection orderByMovieBackdropPath(boolean desc) {
        orderBy(MovieColumns.BACKDROP_PATH, desc);
        return this;
    }

    public TrailerSelection orderByMovieBackdropPath() {
        orderBy(MovieColumns.BACKDROP_PATH, false);
        return this;
    }

    public TrailerSelection movieVoteAverage(float... value) {
        addEquals(MovieColumns.VOTE_AVERAGE, toObjectArray(value));
        return this;
    }

    public TrailerSelection movieVoteAverageNot(float... value) {
        addNotEquals(MovieColumns.VOTE_AVERAGE, toObjectArray(value));
        return this;
    }

    public TrailerSelection movieVoteAverageGt(float value) {
        addGreaterThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public TrailerSelection movieVoteAverageGtEq(float value) {
        addGreaterThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public TrailerSelection movieVoteAverageLt(float value) {
        addLessThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public TrailerSelection movieVoteAverageLtEq(float value) {
        addLessThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public TrailerSelection orderByMovieVoteAverage(boolean desc) {
        orderBy(MovieColumns.VOTE_AVERAGE, desc);
        return this;
    }

    public TrailerSelection orderByMovieVoteAverage() {
        orderBy(MovieColumns.VOTE_AVERAGE, false);
        return this;
    }

    public TrailerSelection key(String... value) {
        addEquals(TrailerColumns.KEY, value);
        return this;
    }

    public TrailerSelection keyNot(String... value) {
        addNotEquals(TrailerColumns.KEY, value);
        return this;
    }

    public TrailerSelection keyLike(String... value) {
        addLike(TrailerColumns.KEY, value);
        return this;
    }

    public TrailerSelection keyContains(String... value) {
        addContains(TrailerColumns.KEY, value);
        return this;
    }

    public TrailerSelection keyStartsWith(String... value) {
        addStartsWith(TrailerColumns.KEY, value);
        return this;
    }

    public TrailerSelection keyEndsWith(String... value) {
        addEndsWith(TrailerColumns.KEY, value);
        return this;
    }

    public TrailerSelection orderByKey(boolean desc) {
        orderBy(TrailerColumns.KEY, desc);
        return this;
    }

    public TrailerSelection orderByKey() {
        orderBy(TrailerColumns.KEY, false);
        return this;
    }

    public TrailerSelection name(String... value) {
        addEquals(TrailerColumns.NAME, value);
        return this;
    }

    public TrailerSelection nameNot(String... value) {
        addNotEquals(TrailerColumns.NAME, value);
        return this;
    }

    public TrailerSelection nameLike(String... value) {
        addLike(TrailerColumns.NAME, value);
        return this;
    }

    public TrailerSelection nameContains(String... value) {
        addContains(TrailerColumns.NAME, value);
        return this;
    }

    public TrailerSelection nameStartsWith(String... value) {
        addStartsWith(TrailerColumns.NAME, value);
        return this;
    }

    public TrailerSelection nameEndsWith(String... value) {
        addEndsWith(TrailerColumns.NAME, value);
        return this;
    }

    public TrailerSelection orderByName(boolean desc) {
        orderBy(TrailerColumns.NAME, desc);
        return this;
    }

    public TrailerSelection orderByName() {
        orderBy(TrailerColumns.NAME, false);
        return this;
    }

    public TrailerSelection site(String... value) {
        addEquals(TrailerColumns.SITE, value);
        return this;
    }

    public TrailerSelection siteNot(String... value) {
        addNotEquals(TrailerColumns.SITE, value);
        return this;
    }

    public TrailerSelection siteLike(String... value) {
        addLike(TrailerColumns.SITE, value);
        return this;
    }

    public TrailerSelection siteContains(String... value) {
        addContains(TrailerColumns.SITE, value);
        return this;
    }

    public TrailerSelection siteStartsWith(String... value) {
        addStartsWith(TrailerColumns.SITE, value);
        return this;
    }

    public TrailerSelection siteEndsWith(String... value) {
        addEndsWith(TrailerColumns.SITE, value);
        return this;
    }

    public TrailerSelection orderBySite(boolean desc) {
        orderBy(TrailerColumns.SITE, desc);
        return this;
    }

    public TrailerSelection orderBySite() {
        orderBy(TrailerColumns.SITE, false);
        return this;
    }

    public TrailerSelection size(Integer... value) {
        addEquals(TrailerColumns.SIZE, value);
        return this;
    }

    public TrailerSelection sizeNot(Integer... value) {
        addNotEquals(TrailerColumns.SIZE, value);
        return this;
    }

    public TrailerSelection sizeGt(int value) {
        addGreaterThan(TrailerColumns.SIZE, value);
        return this;
    }

    public TrailerSelection sizeGtEq(int value) {
        addGreaterThanOrEquals(TrailerColumns.SIZE, value);
        return this;
    }

    public TrailerSelection sizeLt(int value) {
        addLessThan(TrailerColumns.SIZE, value);
        return this;
    }

    public TrailerSelection sizeLtEq(int value) {
        addLessThanOrEquals(TrailerColumns.SIZE, value);
        return this;
    }

    public TrailerSelection orderBySize(boolean desc) {
        orderBy(TrailerColumns.SIZE, desc);
        return this;
    }

    public TrailerSelection orderBySize() {
        orderBy(TrailerColumns.SIZE, false);
        return this;
    }

    public TrailerSelection type(String... value) {
        addEquals(TrailerColumns.TYPE, value);
        return this;
    }

    public TrailerSelection typeNot(String... value) {
        addNotEquals(TrailerColumns.TYPE, value);
        return this;
    }

    public TrailerSelection typeLike(String... value) {
        addLike(TrailerColumns.TYPE, value);
        return this;
    }

    public TrailerSelection typeContains(String... value) {
        addContains(TrailerColumns.TYPE, value);
        return this;
    }

    public TrailerSelection typeStartsWith(String... value) {
        addStartsWith(TrailerColumns.TYPE, value);
        return this;
    }

    public TrailerSelection typeEndsWith(String... value) {
        addEndsWith(TrailerColumns.TYPE, value);
        return this;
    }

    public TrailerSelection orderByType(boolean desc) {
        orderBy(TrailerColumns.TYPE, desc);
        return this;
    }

    public TrailerSelection orderByType() {
        orderBy(TrailerColumns.TYPE, false);
        return this;
    }
}
