package com.univer.labs.books.controller.book;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.univer.labs.books.R;
import com.univer.labs.books.model.Book;
import com.univer.labs.books.model.GroupedBooks;
import com.univer.labs.books.service.BookService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class BooksByYearChartActivity extends AppCompatActivity {
    BookService bookService;
    ArrayList<String> dates;
    ArrayList<BarEntry> barEntries;
    HorizontalBarChart barChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_by_year_chart);
        bookService = new BookService(this);
        barChart = findViewById(R.id.bargraph);
        setData();
    }

    private void setData()
    {
        ArrayList<BarEntry> entries= new ArrayList<>();
        List<GroupedBooks> groupedBooks = bookService.getAllBooksByDate();
        List<GroupedBooks> mergedList =recreateDatesList(groupedBooks);
        List<String> labels = new ArrayList<>();
        for(int i=0;i<mergedList.size();i++)
        {
            String date =mergedList.get(i).getDate();
            labels.add(date);
            entries.add(new BarEntry(i,mergedList.get(i).getCount()));
        }
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(labels.size());
        xAxis.setLabelRotationAngle(270);

        BarDataSet set = new BarDataSet(entries,"Count");
        BarData data = new BarData(set);
        Description description = new Description();
        description.setText("Books by years");
        barChart.setDescription(description);
        barChart.setData(data);
    }

    private List<GroupedBooks> recreateDatesList(List<GroupedBooks> origin)
    {
        List<GroupedBooks> newOne=new ArrayList<>();
        Set<String> dates = new TreeSet<>();
        for(GroupedBooks gr:origin)
        {
            dates.add(gr.getDate().substring(gr.getDate().length()-4));
        }
        for(String date:dates) {
            GroupedBooks tmp = new GroupedBooks();
            tmp.setDate(date);
            newOne.add(tmp);
        }

        for(GroupedBooks gr1:origin)
        {
            for(GroupedBooks gr2:newOne)
            {
                String tmpDate= gr1.getDate();
                tmpDate=tmpDate.substring(tmpDate.length()-4);
                if(tmpDate.compareTo(gr2.getDate())==0)
                {
                    int count = gr2.getCount();
                    gr2.setCount(count+gr1.getCount());
                }
            }
        }
        return newOne;
    }

}
