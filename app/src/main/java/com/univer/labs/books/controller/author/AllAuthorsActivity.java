package com.univer.labs.books.controller.author;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.univer.labs.books.R;
import com.univer.labs.books.model.Author;
import com.univer.labs.books.service.AuthorService;

import java.util.List;

public class AllAuthorsActivity extends AppCompatActivity {
    private AuthorService authorService;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<Author> list;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_authors);
        authorService = new AuthorService(this);
        list = authorService.getAllAuthors();
        recyclerView = findViewById(R.id.allAuthorsView);
        // specify an adapter
        mAdapter = new AuthorsListAdapter(this, list);
        context = this;
        recyclerView.setAdapter(mAdapter);
        new ItemTouchHelper(itouchHelperCallBack).attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    ItemTouchHelper.SimpleCallback itouchHelperCallBack = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            if (ItemTouchHelper.LEFT == direction) {
                Author authorToDelete = list.get(viewHolder.getLayoutPosition());
                authorService.deleteAuthor(authorToDelete);
                list.remove(viewHolder.getLayoutPosition());
                mAdapter.notifyDataSetChanged();

            } else {
                Author authorToEdit = list.get(viewHolder.getLayoutPosition());
                Intent intent = new Intent(context, EditAuthorActivity.class);
                intent.putExtra("name", authorToEdit.getName());
                intent.putExtra("surname", authorToEdit.getSurname());
                intent.putExtra("secondName", authorToEdit.getSecondName());
                intent.putExtra("id", authorToEdit.getAuthorId());
                context.startActivity(intent);
            }
        }
    };
}
