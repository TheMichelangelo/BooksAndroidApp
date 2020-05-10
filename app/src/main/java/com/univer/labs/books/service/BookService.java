package com.univer.labs.books.service;

//create table book (book_id INTEGER PRIMARY KEY AUTOINCREMENT, book_name varchar(20), date_published varchar(20),pages_count INTEGER,price DOUBLE,author_id integer DEFAULT 0)
//create table authors(author_id INTEGER PRIMARY KEY AUTOINCREMENT, author_name varchar(20), author_surname varchar(20), author_secondname varchar(20))

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.univer.labs.books.model.Book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookService extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "books.db";
    public static final String TABLE_NAME = "book";
    private static final String COL_1 = "book_id";
    private static final String COL_2 = "book_name";
    private static final String COL_3 = "date_published";
    private static final String COL_4 = "pages_count";
    private static final String COL_5 = "price";
    private static final String COL_6 = "author_id";

    public BookService(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_2+" varchar(20), "+COL_3+" varchar(20),"+COL_4+" INTEGER,"+COL_5+" DOUBLE,"+COL_6+" integer default 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean insertBook(String name, String date_published, int pages_count, double price, long author_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, date_published);
        contentValues.put(COL_4, pages_count);
        contentValues.put(COL_5, price);
        contentValues.put(COL_6, author_id);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public boolean updateBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, book.getName());
        contentValues.put(COL_3, book.getDatePublished());
        contentValues.put(COL_4, book.getPagesCount());
        contentValues.put(COL_5, book.getPrice());
        contentValues.put(COL_6, book.getAuthorId());
        long result = db.update(TABLE_NAME, contentValues, "book_id = ?", new String[]{String.valueOf(book.getBookId())});
        return result != -1;
    }

    private List<Serializable> getAllBooks() {
        List<Serializable> itemsList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        if (cursor.getCount() == 0)
            return itemsList;

        while (cursor.moveToNext()) {
            Book book = new Book();
            book.setBookId(cursor.getInt(0));
            book.setName(cursor.getString(1));
            book.setDatePublished(cursor.getString(2));
            book.setPagesCount(cursor.getInt(3));
            book.setPrice(cursor.getDouble(4));
            book.setAuthorId(cursor.getInt(5));
            itemsList.add(book);
        }
        cursor.close();
        return itemsList;
    }

    public boolean deleteBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "book_id = ?", new String[]{String.valueOf(book.getBookId())}) > 0;
    }

    //custom querries
    public Cursor getAllBooksByAuthors() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT author_id, COUNT(*), SUM(price), AVG(price) FROM "+ TABLE_NAME +
                " GROUP BY author_id having author_id!=0", null);
    }

    public List<Serializable> getAllBooksUnderPrice(double price) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE price <= " + price, null);
        List<Serializable> itemsList = new ArrayList<>();
        if (cursor.getCount() == 0)
            return itemsList;
        while (cursor.moveToNext())
        {
            Book book = new Book();
            book.setBookId(cursor.getInt(0));
            book.setName(cursor.getString(1));
            book.setDatePublished(cursor.getString(2));
            book.setPagesCount(cursor.getInt(3));
            book.setPrice(cursor.getDouble(4));
            book.setAuthorId(cursor.getInt(5));
            itemsList.add(book);
        }
        cursor.close();
        return itemsList;
    }

    public List<Serializable> getAllDuplications() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME+
                " WHERE book_name IN (SELECT book_name FROM "+TABLE_NAME+" GROUP BY book_name HAVING COUNT(*) > 1) " +
                "AND author_id IN (SELECT author_id FROM "+TABLE_NAME+" GROUP BY author_id HAVING COUNT(*) > 1) " +
                "ORDER BY book_name", null);
        List<Serializable> itemsList = new ArrayList<>();
        if (cursor.getCount() == 0)
            return itemsList;
        while (cursor.moveToNext())
        {
            Book book = new Book();
            book.setBookId(cursor.getInt(0));
            book.setName(cursor.getString(1));
            book.setDatePublished(cursor.getString(2));
            book.setPagesCount(cursor.getInt(3));
            book.setPrice(cursor.getDouble(4));
            book.setAuthorId(cursor.getInt(5));
            itemsList.add(book);
        }
        cursor.close();
        return itemsList;
    }

}
