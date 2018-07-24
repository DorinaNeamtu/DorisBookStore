package com.bookstore.android.dorisbookstore;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.bookstore.android.dorisbookstore.data.BookContract;

/**
 * Created by dori on 19.07.2018.
 */

public class BookCursorAdapter extends CursorAdapter {

    public BookCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

        @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameTextview = (TextView) view.findViewById(R.id.name);
        TextView priceTextView = (TextView) view.findViewById(R.id.price);
        TextView quantTextView=(TextView) view.findViewById(R.id.quantity);
        TextView instockTextView=(TextView)view.findViewById(R.id.instock);

        // Extract properties from cursor
        int nameColumnIndex=cursor.getColumnIndex(BookContract.BookEntry.COLUMN_BOOK_NAME);
        int priceColumnIndex=cursor.getColumnIndex(BookContract.BookEntry.COLUMN_BOOK_PRICE);
        int quantColumnIndex=cursor.getColumnIndex(BookContract.BookEntry.COLUMN_BOOK_QUANTITY);
        int stockColumnIndex=cursor.getColumnIndex(BookContract.BookEntry.COLUMN_IN_STOCK);

        String bookName = cursor.getString(nameColumnIndex);
        String bookPrice0=cursor.getString(priceColumnIndex);
        String bookQuant0=cursor.getString(quantColumnIndex);
        String inStock0=cursor.getString(stockColumnIndex);
        int quantity = Integer.parseInt(inStock0);

        String bookPrice= "Price: " +bookPrice0;
        String bookQuant="Quantity: "+bookQuant0;
        String inStock="IN STOCK: ";
        if (quantity>0){
            inStock=inStock+"Yes";
        }
        else {
            inStock=inStock+"NO";
        }

      // Populate fields with extracted properties
        nameTextview.setText(bookName);
        priceTextView.setText(bookPrice);
        quantTextView.setText(bookQuant);
        instockTextView.setText(inStock);
    }
}
