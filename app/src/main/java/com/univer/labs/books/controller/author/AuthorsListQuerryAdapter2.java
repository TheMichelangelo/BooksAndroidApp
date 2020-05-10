package com.univer.labs.books.controller.author;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.univer.labs.books.R;
import com.univer.labs.books.model.Author;
import com.univer.labs.books.model.GroupedBooks;
import com.univer.labs.books.service.AuthorService;

import java.util.List;

public class AuthorsListQuerryAdapter2 extends RecyclerView.Adapter<AuthorsListQuerryAdapter2.BooksListHolder> {
    private List<GroupedBooks> booksList;
    private AuthorService authorService;
    private Context context;

    @NonNull
    @Override
    public AuthorsListQuerryAdapter2.BooksListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_book_grouped_by_author, parent,false);
        return new AuthorsListQuerryAdapter2.BooksListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorsListQuerryAdapter2.BooksListHolder holder, final int position) {
        Author author = authorService.getAuthorById(booksList.get(position).getAuthorId());
        holder.text1.setText(author.getSurname());
        holder.text2.setText(author.getName());
        holder.text3.setText(author.getSecondName());
        holder.text4.setText(String.valueOf(booksList.get(position).getCount()));
        holder.text5.setText(String.valueOf(booksList.get(position).getMaxPrice()));
        holder.text6.setText(String.valueOf(booksList.get(position).getAvgPrice()));
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class BooksListHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView text1,text2,text3,text4,text5,text6;
        ConstraintLayout layout;
        public BooksListHolder(View v) {
            super(v);
            text1= v.findViewById(R.id.author_book_surname);
            text2= v.findViewById(R.id.author_book_name);
            text3 = v.findViewById(R.id.author_book_secondName);
            text4= v.findViewById(R.id.books_count_label);
            text5= v.findViewById(R.id.books_max_price_label);
            text6 = v.findViewById(R.id.books_avg_price_label);
            layout = v.findViewById(R.id.all_authors_books_row_layout);
        }
    }

    AuthorsListQuerryAdapter2(Context contex, List<GroupedBooks> list) {
        booksList=list;
        this.context=contex;
        authorService = new AuthorService(context);
    }


}
