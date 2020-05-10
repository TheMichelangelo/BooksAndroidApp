package com.univer.labs.books.controller.book;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.univer.labs.books.R;
import com.univer.labs.books.controller.author.AuthorDetailsActivity;
import com.univer.labs.books.controller.author.AuthorsListAdapter;
import com.univer.labs.books.model.Author;
import com.univer.labs.books.model.Book;
import com.univer.labs.books.service.AuthorService;

import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookListHolder> {
    private List<Book> bookList;
    private Context context;
    private AuthorService authorService;

    @NonNull
    @Override
    public BookListAdapter.BookListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_book_list, parent, false);
        return new BookListAdapter.BookListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookListAdapter.BookListHolder holder, final int position) {
        holder.text1.setText(bookList.get(position).getName());
        Author author = authorService.getAuthorById(bookList.get(position).getAuthorId());
        holder.text2.setText(author.toString());
        holder.text3.setText(bookList.get(position).getDatePublished());
        holder.text4.setText(String.valueOf(bookList.get(position).getPrice()));
        holder.text5.setText(String.valueOf(bookList.get(position).getPagesCount()));
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class BookListHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView text1, text2, text3, text4, text5;
        CardView layout;

        public BookListHolder(View v) {
            super(v);
            text1 = v.findViewById(R.id.row_book_name);
            text2 = v.findViewById(R.id.row_author);
            text3 = v.findViewById(R.id.textView9);
            text4 = v.findViewById(R.id.textView11);
            text5 = v.findViewById(R.id.textView13);
            layout = v.findViewById(R.id.book_list_item);
        }
    }

    BookListAdapter(Context contex, List<Book> list) {
        bookList = list;
        this.context = contex;
        authorService = new AuthorService(context);
    }

}

