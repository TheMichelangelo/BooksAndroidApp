package com.univer.labs.books.controller.author;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.univer.labs.books.R;
import com.univer.labs.books.service.AuthorService;

public class AuthorDetailsActivity extends AppCompatActivity {
    TextView nameTextView, surnameTextView, secondNameTextView;
    String name, surname, secondName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_details);
        nameTextView = findViewById(R.id.details_author_name);
        surnameTextView = findViewById(R.id.details_author_surname);
        secondNameTextView = findViewById(R.id.details_author_secondname);
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
}
