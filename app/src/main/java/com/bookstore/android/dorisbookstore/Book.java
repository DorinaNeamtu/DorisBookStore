package com.bookstore.android.dorisbookstore;

/**
 * Created by dori on 29.06.2018.
 */


public class Book {

    private String mBookName;
    private int mBookPrice;
    private int mBookQuantity;
    private int mInStock;
    private String mSupplierName;
    private String mSupplierPhone;

    public Book(String bookName, int bookPrice, int bookQuantity, int inStock, String supplierName, String supplierPhone) {
        mBookName = bookName;
        mBookPrice = bookPrice;
        mBookQuantity = bookQuantity;
        mInStock = inStock;
        mSupplierName = supplierName;
        mSupplierPhone = supplierPhone;
    }

    public Book() {
    }

    public String getBookName() {
        return mBookName;
    }

    public int getBookPrice() {
        return mBookPrice;
    }

    public int getBookQuantity() {
        return mBookQuantity;
    }

    public int getInStock() {
        return mInStock;
    }

    public String getSupplierName() {
        return mSupplierName;
    }

    public String getSupplierPhone() {
        return mSupplierPhone;
    }
}
