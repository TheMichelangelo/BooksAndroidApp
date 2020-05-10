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

import java.util.List;

public class AuthorsListQuerryAdapter2 extends RecyclerView.Adapter<AuthorsListAdapter.AuthorListHolder> {
    private List<Author> authorList;
    private Context context;

    @NonNull
    @Override
    public AuthorsListAdapter.AuthorListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_book_grouped_by_author, parent,false);
        return new AuthorsListAdapter.AuthorListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorsListAdapter.AuthorListHolder holder, final int position) {
        holder.text1.setText(authorList.get(position).getSurname());
        holder.text2.setText(authorList.get(position).getName());
        holder.text3.setText(authorList.get(position).getSecondName());
    }

    @Override
    public int getItemCount() {
        return authorList.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class AuthorListHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView text1,text2,text3,text4,text5,text6;
        ConstraintLayout layout;
        public AuthorListHolder(View v) {
            super(v);
            text1= v.findViewById(R.id.author_row_text_view1);
            text2= v.findViewById(R.id.author_row_text_view2);
            text3 = v.findViewById(R.id.author_row_text_view3);
            layout = v.findViewById(R.id.all_authors_books_row_layout);
        }
    }

    AuthorsListQuerryAdapter2(Context contex, List<Author> list) {
        authorList=list;
        this.context=contex;
    }


}
