package com.univer.labs.books.controller.author;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.univer.labs.books.R;
import com.univer.labs.books.model.Author;
import com.univer.labs.books.model.GroupedBooks;
import com.univer.labs.books.service.AuthorService;
import com.univer.labs.books.service.BookService;

import java.util.ArrayList;
import java.util.List;

public class BooksByAuthorsPieChartActivity extends AppCompatActivity {
    AuthorService authorService;
    BookService bookService;

    List<Author> authors;
    List<GroupedBooks> bookinsInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_by_authors_pie_chart);
        authorService = new AuthorService(this);
        bookService = new BookService(this);
        authors = authorService.getAllAuthors();
        bookinsInfo = new ArrayList<>();
        PieChart chart = findViewById(R.id.authors_pie_chart_id);
        chart.setRotationEnabled(false);
        chart.setHoleRadius(25f);
        chart.setTransparentCircleAlpha(0);
        chart.setCenterText("Books by authors chart");
        chart.setCenterTextSize(10);
        chart.setDrawEntryLabels(true);
        addPieSet(chart);

        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int pos1 = 0;
                int count = (int)e.getY();

                while(bookinsInfo.get(pos1).getCount()!=count)
                    pos1++;
                String author = authors.get(pos1).toString();
                Toast.makeText(BooksByAuthorsPieChartActivity.this, "Autor " + author + "\n" + "Books count: " + count, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    private void addPieSet(PieChart chart) {
        ArrayList<PieEntry> yEntries = new ArrayList<>();
        ArrayList<String> xEntries = new ArrayList<>();
        for (int i = 0; i < authors.size(); i++) {
            GroupedBooks gb = bookService.getAllBooksByAuthor(authors.get(i));
            bookinsInfo.add(gb);
            yEntries.add(new PieEntry(gb.getCount(), i));
        }
        for (Author a : authors)
            xEntries.add(a.toString());

        PieDataSet pieDataSet = new PieDataSet(yEntries, "Books count");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.GRAY);
        colors.add(Color.CYAN);
        colors.add(Color.GREEN);
        colors.add(Color.MAGENTA);

        pieDataSet.setColors(colors);
        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        //legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        chart.setData(pieData);
        chart.invalidate();
    }
}
