package com.nomaa.bookfinder;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nomaa on 6/22/2017.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Activity context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.book_list_layout,
                    parent, false);
        }

        Book book = getItem(position);

        TextView title = (TextView) listItemView.findViewById(R.id.title);
        title.setText(book.getTitle());

        TextView author = (TextView) listItemView.findViewById(R.id.author);
        author.setText(book.getAuthor());

        TextView year = (TextView) listItemView.findViewById(R.id.year);
        year.setText(book.getYear());

        return listItemView;
    }


}
