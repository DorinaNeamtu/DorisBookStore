package com.bookstore.android.dorisbookstore;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.app.LoaderManager.LoaderCallbacks;
import android.app.LoaderManager;

import com.bookstore.android.dorisbookstore.data.BookContract.BookEntry;
import com.bookstore.android.dorisbookstore.data.BookDbHelper;

import java.util.ArrayList;


public class CatalogActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String LOG_TAG = CatalogActivity.class.getSimpleName();

    private static final int BOOK_LOADER = 0;

    BookCursorAdapter mCursorAdapter;

    private BookDbHelper mDbHelper;

    private ArrayList<Book> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);


        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });


        // Find the ListView which will be populated with thebook data
        ListView bookListView = (ListView) findViewById(R.id.list);
        // Find and set empty view on the ListView, so that it only shows when the list has 0 items.
        View emptyView = findViewById(R.id.empty_view);
        bookListView.setEmptyView(emptyView);

        mCursorAdapter = new BookCursorAdapter(this, null);
        bookListView.setAdapter(mCursorAdapter);

        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                Uri currentBookUri = ContentUris.withAppendedId(BookEntry.CONTENT_URI, id);
                intent.setData(currentBookUri);
                startActivity(intent);
            }

        });

        getLoaderManager().initLoader(BOOK_LOADER, null, this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            case R.id.action_insert_dummy_data:
                insertAllBooks();
                return true;
            case R.id.action_delete_all_entries:
                deleteAllBooks();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void insertAllBooks() {

        ContentValues values = new ContentValues();

        ArrayList<Book> books = new ArrayList();
        books.add(new Book("Quo Vadis - Henryk Sienkiewicz", 100, 45, 1, "Alexandria", "40764563289"));
        books.add(new Book("Gai Jin - James Clavell", 80, 35, 1, "Aletheea", "40744563489"));
        books.add(new Book("War and Peace - Lev Tolstoi", 110, 0, 0, "Alexandria", "40764563289"));
        books.add(new Book("The Adventures of Sherlock Holmes - Arthur Conan Doyle", 86, 135, 1, "Aletheea", "40744563489"));

        Book myBook = new Book();

        for (int i = 0; i < books.size(); i++) {

            myBook = books.get(i);

            String currentBookName = myBook.getBookName();
            int currentBookPrice = myBook.getBookPrice();
            int currentBookQuantity = myBook.getBookQuantity();
            int currentinStock = myBook.getInStock();
            String currentSupplierName = myBook.getSupplierName();
            String currentSupplierPhone = myBook.getSupplierPhone();

            values.put(BookEntry.COLUMN_BOOK_NAME, currentBookName);
            values.put(BookEntry.COLUMN_BOOK_PRICE, currentBookPrice);
            values.put(BookEntry.COLUMN_BOOK_QUANTITY, currentBookQuantity);
            values.put(BookEntry.COLUMN_IN_STOCK, currentinStock);
            values.put(BookEntry.COLUMN_BOOK_SUPPPLIER_NAME, currentSupplierName);
            values.put(BookEntry.COLUMN_BOOK_SUPPLIER_PHONE, currentSupplierPhone);

            Uri newUri = getContentResolver().insert(BookEntry.CONTENT_URI, values);

        }
        Log.v("CatalogActivity", books.size() + " rows inserted into books database");

    }

    private void deleteAllBooks() {
        int rowsDeleted = getContentResolver().delete(BookEntry.CONTENT_URI, null, null);
        Log.v("CatalogActivity", rowsDeleted + " rows deleted from book database");
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                BookEntry._ID,
                BookEntry.COLUMN_BOOK_NAME,
                BookEntry.COLUMN_BOOK_PRICE,
                BookEntry.COLUMN_BOOK_QUANTITY,
                BookEntry.COLUMN_IN_STOCK,
                BookEntry.COLUMN_BOOK_SUPPPLIER_NAME,
                BookEntry.COLUMN_BOOK_SUPPLIER_PHONE};

        return new android.content.CursorLoader(this, BookEntry.CONTENT_URI, projection, null, null, null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }

}
