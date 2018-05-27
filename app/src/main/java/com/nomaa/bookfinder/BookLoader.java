package com.nomaa.bookfinder;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by nomaa on 6/22/2017.
 */

public class BookLoader extends AsyncTaskLoader<List<Book>> {

    private static String mUrl;

    public BookLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {
        if(mUrl == null)
            return null;

        List<Book> books = QueryUtils.fetchBooks(mUrl);

        return books;
    }

    public static void setSearchQuery(String url) {
        mUrl = url;
    }
}
