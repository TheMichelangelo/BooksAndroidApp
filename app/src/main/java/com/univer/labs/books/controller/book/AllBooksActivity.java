package com.univer.labs.books.controller.book;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.univer.labs.books.R;
import com.univer.labs.books.controller.author.AuthorsListAdapter;
import com.univer.labs.books.controller.author.EditAuthorActivity;
import com.univer.labs.books.model.Author;
import com.univer.labs.books.model.Book;
import com.univer.labs.books.service.AuthorService;
import com.univer.labs.books.service.BookService;

import java.util.List;

public class AllBooksActivity extends AppCompatActivity {
    private BookService bookService;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<Book> list;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);
        bookService = new BookService(this);
        list = bookService.getAllBooks();
        recyclerView = findViewById(R.id.allBooksView);
        // specify an adapter
        mAdapter = new BookListAdapter(this, list);
        context=this;
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
            if(ItemTouchHelper.LEFT ==direction)
            {
                Book bookToDelete = list.get(viewHolder.getLayoutPosition());
                bookService.deleteBook(bookToDelete);
                list.remove(viewHolder.getLayoutPosition());
                mAdapter.notifyDataSetChanged();
            }
            else
            {
                Book bookToEdit = list.get(viewHolder.getLayoutPosition());
                Intent intent = new Intent(context, EditBookActivity.class);
                intent.putExtra("name", bookToEdit.getName());
                intent.putExtra("price", bookToEdit.getPrice());
                intent.putExtra("pages", bookToEdit.getPagesCount());
                intent.putExtra("date_publiched", bookToEdit.getDatePublished());
                intent.putExtra("bookId", bookToEdit.getBookId());
                intent.putExtra("authorId", bookToEdit.getAuthorId());
                context.startActivity(intent);
            }
        }
    };
}
