package com.univer.labs.books;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class AllAuthorsActivity extends AppCompatActivity {
    private String[] s1,s2;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_authors);
        s1=getResources().getStringArray(R.array.languages);
        s2=getResources().getStringArray(R.array.description);

        recyclerView = (RecyclerView) findViewById(R.id.allAuthorsView);

        // specify an adapter (see also next example)
        mAdapter = new AuthorsListAdapter(this,s1,s2);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
