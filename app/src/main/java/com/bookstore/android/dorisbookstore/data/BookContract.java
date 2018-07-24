package com.bookstore.android.dorisbookstore.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by dori on 28.06.2018.
 */

public final class BookContract {

    public static final String CONTENT_AUTHORITY = "com.bookstore.android.dorisbookstore";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_BOOKS = "books";

    //empty constructor for BookContract
    private BookContract() {
    }

    public static final class BookEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_BOOKS);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKS;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKS;

        public final static String TABLE_NAME = "books";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_BOOK_NAME = "product_name";
        public final static String COLUMN_BOOK_PRICE = "price";
        public final static String COLUMN_BOOK_QUANTITY = "quantity";
        public final static String COLUMN_BOOK_SUPPPLIER_NAME = "supplier_name";
        public final static String COLUMN_BOOK_SUPPLIER_PHONE = "supplier_phone";
        public final static String COLUMN_IN_STOCK = "in_stock";

        public static final int IN_STOCK = 1;
        public static final int NOT_IN_STOCK = 0;

        public static boolean isInStock(int in_stock) {
            if (in_stock == IN_STOCK || in_stock == NOT_IN_STOCK) {
                return true;
            }
            return false;
        }
    }
}
