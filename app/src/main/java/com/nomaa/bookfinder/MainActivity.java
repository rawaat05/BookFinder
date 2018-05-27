package com.nomaa.bookfinder;


import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.support.annotation.ArrayRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    private BookAdapter mAdapter;

    private ListView listView;

    private String mUrlStart = "https://www.googleapis.com/books/v1/volumes?q=";
    private String mUrlEnd = "&maxResults=5";
    private String mFinalUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);

        mAdapter = new BookAdapter(this, new ArrayList<Book>());

        listView.setAdapter(mAdapter);

        getLoaderManager().initLoader(1,null, this);

    }

    public void search(View view){

        EditText bar = (EditText) findViewById(R.id.book_search);
        String searched = bar.getText().toString();

        mFinalUrl = mUrlStart + searched + mUrlEnd;

        BookLoader.setSearchQuery(mFinalUrl);

        getLoaderManager().restartLoader(1, null, this);
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        return new BookLoader(this, mFinalUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        // Clear the adapter of previous book data
        if(mAdapter.getCount() > 0){
            mAdapter.clear();
        }

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (data != null && !data.isEmpty()) {
            Log.e("Mr", data.get(1).getTitle() + "Here");
            mAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        mAdapter.clear();
    }
}
