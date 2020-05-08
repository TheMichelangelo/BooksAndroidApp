package com.univer.labs.books.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.univer.labs.books.model.Author;
import com.univer.labs.books.model.Book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AuthorService extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "books.db";
    public static final String TABLE_NAME = "authors_table";
    public static final String COL_1 = "author_id";
    public static final String COL_2 = "author_name";
    public static final String COL_3 = "author_surname";
    public static final String COL_4 = "author_secondname";

    public AuthorService(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_2+" varchar(20), "+COL_3+" varchar(20), "+COL_4+" varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertAuthor(String name, String surname, String secondName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, surname);
        contentValues.put(COL_4, secondName);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        return true;
    }

    public boolean updateBook(Author author) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, author.getName());
        contentValues.put(COL_3, author.getSurname());
        contentValues.put(COL_4, author.getSecondName());
        long result = db.update(TABLE_NAME, contentValues, "id = ?", new String[]{String.valueOf(author.getAuthorId())});
        if (result == -1)
            return false;
        return true;
    }

    private List<Serializable> getAllAuthors() {
        List<Serializable> itemsList = new ArrayList<Serializable>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        if (cursor.getCount() == 0)
            return itemsList;

        while (cursor.moveToNext())
        {
            Book book= new Book();
            book.setBookId(cursor.getInt(0));
            book.setName(cursor.getString(1));
            book.setDatePublished(cursor.getString(2));
            book.setPagesCount(cursor.getInt(3));
            book.setPrice(cursor.getDouble(4));
            book.setAuthorId(cursor.getInt(5));
            itemsList.add(book);
        }
        return itemsList;
    }

    public boolean deleteAuthor(Author author) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "id = ?", new String[]{String.valueOf(author.getAuthorId())}) > 0;
    }

    //custom querries
    public List<Serializable> getAllAuthorsWithoutBooks() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM authors_table " +
                "WHERE author_id NOT IN(SELECT DISTINCT author_id FROM books_table)", null);
        List<Serializable> itemsList = new ArrayList<Serializable>();
        if (cursor.getCount() == 0)
            return itemsList;
        while (cursor.moveToNext())
        {
            Author author = new Author();
            author.setAuthorId(cursor.getInt(0));
            author.setName(cursor.getString(1));
            author.setSurname(cursor.getString(2));
            author.setSecondName(cursor.getString(3));
            itemsList.add(author);
        }
        return itemsList;
    }

}
