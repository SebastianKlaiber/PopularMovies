package de.k11dev.sklaiber.popularmovies.provider.trailer;

import android.net.Uri;
import android.provider.BaseColumns;

import de.k11dev.sklaiber.popularmovies.provider.MovieProvider;
import de.k11dev.sklaiber.popularmovies.provider.movie.MovieColumns;

/**
 * Trailer Table
 */
public class TrailerColumns implements BaseColumns {
    public static final String TABLE_NAME = "trailer";
    public static final Uri CONTENT_URI = Uri.parse(MovieProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String MOVIE_ID = "movie_id";

    public static final String KEY = "key";

    public static final String NAME = "name";

    public static final String SITE = "site";

    public static final String SIZE = "size";

    public static final String TYPE = "type";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            MOVIE_ID,
            KEY,
            NAME,
            SITE,
            SIZE,
            TYPE
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(MOVIE_ID) || c.contains("." + MOVIE_ID)) return true;
            if (c.equals(KEY) || c.contains("." + KEY)) return true;
            if (c.equals(NAME) || c.contains("." + NAME)) return true;
            if (c.equals(SITE) || c.contains("." + SITE)) return true;
            if (c.equals(SIZE) || c.contains("." + SIZE)) return true;
            if (c.equals(TYPE) || c.contains("." + TYPE)) return true;
        }
        return false;
    }

    public static final String PREFIX_MOVIE = TABLE_NAME + "__" + MovieColumns.TABLE_NAME;
}
