package com.univer.labs.books.controller.author;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.univer.labs.books.R;
import com.univer.labs.books.service.AuthorService;

public class EditAuthorActivity extends AppCompatActivity {
    AuthorService authorService;
    TextView nameTextView, surnameTextView, secondNameTextView;
    String name, surname, secondName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_author);
        authorService = new AuthorService(this);
        nameTextView = findViewById(R.id.edit_author_name);
        surnameTextView = findViewById(R.id.edit_author_surname);
        secondNameTextView = findViewById(R.id.edit_author_secondname);
        getData();
        setData();
    }

    private void getData() {
        if (getIntent().hasExtra("name") && getIntent().hasExtra("surname")
                && getIntent().hasExtra("secondName")) {
            name = getIntent().getStringExtra("name");
            surname = getIntent().getStringExtra("surname");
            secondName = getIntent().getStringExtra("secondName");
        } else {
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }
    }

    private void setData()
    {
        nameTextView.setText(name);
        surnameTextView.setText(surname);
        secondNameTextView.setText(secondName);
    }


    public void updateAuthor(View v) {

    }
}
