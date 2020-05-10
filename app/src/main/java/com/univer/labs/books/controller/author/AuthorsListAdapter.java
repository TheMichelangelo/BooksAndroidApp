package com.univer.labs.books.controller.author;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.univer.labs.books.R;
import com.univer.labs.books.model.Author;

import java.util.List;

public class AuthorsListAdapter extends RecyclerView.Adapter<AuthorsListAdapter.AuthorListHolder> {
    private List<Author> authorList;
    private Context context;

    @NonNull
    @Override
    public AuthorListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.autor_list_row, parent,false);
        return new AuthorListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorListHolder holder, final int position) {
        holder.text1.setText(authorList.get(position).getSurname());
        holder.text2.setText(authorList.get(position).getName());
        holder.text3.setText(authorList.get(position).getSecondName());

        holder.layout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AuthorDetailsActivity.class);
                intent.putExtra("name",authorList.get(position).getName());
                intent.putExtra("surname",authorList.get(position).getSurname());
                intent.putExtra("secondName",authorList.get(position).getSecondName());
                intent.putExtra("id",authorList.get(position).getAuthorId());
                context.startActivity(intent);
            }
        });
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
        TextView text1,text2,text3;
        ConstraintLayout layout;
        public AuthorListHolder(View v) {
            super(v);
            text1= v.findViewById(R.id.author_row_text_view1);
            text2= v.findViewById(R.id.author_row_text_view2);
            text3 = v.findViewById(R.id.author_row_text_view3);
            layout = v.findViewById(R.id.all_authors_row_layout);
        }
    }

    AuthorsListAdapter(Context contex, List<Author> list) {
        authorList=list;
        this.context=contex;
    }


}
