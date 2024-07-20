package com.example.rohan1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rohan1.Adapter.BookAdapter2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class seventhSem extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookAdapter2 bookAdapter;
    private List<BookItem> bookItemList;
    Toolbar toolbar2;
    Animation animation;
    ImageView homebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seventh_sem);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        homebutton = findViewById(R.id.home_button);

        toolbar2 = findViewById(R.id.toolbar2);
        toolbar2.setTitle("Subjects");

        setSupportActionBar(toolbar2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation_on_book);
        recyclerView.startAnimation(animation);


        // Set up RecyclerView with GridLayoutManager
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        // Initialize bookItemList
        bookItemList = new ArrayList<>();
        bookItemList.add(new BookItem("High Performance Computing", R.drawable.trans));
        bookItemList.add(new BookItem("Artificial Intelligence And Robotics", R.drawable.trans));
        bookItemList.add(new BookItem("Data Analysis", R.drawable.trans));
        bookItemList.add(new BookItem("Elective-I", R.drawable.trans));
        bookItemList.add(new BookItem("Elective-II", R.drawable.trans));


        // Set up mapping between subjects and PDF file names
        Map<String, String> pdfMap = new HashMap<>();
        pdfMap.put("Java", "rohan.pdf");
        pdfMap.put("Flutter", "rohan1.pdf");
        pdfMap.put("CPP", "rohan2.pdf");
        pdfMap.put("DBMS", "rohan3.pdf");

        // Set up adapter
        bookAdapter = new BookAdapter2(this, bookItemList, pdfMap);
        recyclerView.setAdapter(bookAdapter);
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(seventhSem.this,MainActivity2.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
