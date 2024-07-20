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

public class fifthSem extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookAdapter2 bookAdapter;
    private List<BookItem> bookItemList;
    Toolbar toolbar2;
    Animation animation;
    ImageView homebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth_sem);
        toolbar2 = findViewById(R.id.toolbar2);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        homebutton = findViewById(R.id.home_button);


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
        bookItemList.add(new BookItem("Database Management Systems", R.drawable.trans));
        bookItemList.add(new BookItem("Theory Of Computation", R.drawable.trans));
        bookItemList.add(new BookItem("Systems Programming and Operating System", R.drawable.trans));
        bookItemList.add(new BookItem("Computer Networks And Security", R.drawable.trans));
        bookItemList.add(new BookItem("Internet of Things And Embedded Systems", R.drawable.trans));
        bookItemList.add(new BookItem("Human Computer Interface", R.drawable.trans));
        bookItemList.add(new BookItem("Distributed Systems", R.drawable.trans));
        bookItemList.add(new BookItem("Softeware Project Management", R.drawable.trans));

        // Set up mapping between subjects and PDF file names
        Map<String, String> pdfMap = new HashMap<>();
        pdfMap.put("Theory Of Computation", "rohan1.pdf");
        pdfMap.put("Database Management Systems", "dummy.pdf");
        pdfMap.put("Systems Programming and Operating System", "dummy.pdf");
        pdfMap.put("Computer Networks And Security", "dummy.pdf");
        pdfMap.put("Internet of Things And Embedded Systems", "dummy.pdf");
        pdfMap.put("Human Computer Interface", "dummy.pdf");
        pdfMap.put("Distributed Systems", "dummy.pdf");
        pdfMap.put("Softeware Project Management", "dummy.pdf");

        // Set up adapter
        bookAdapter = new BookAdapter2(this, bookItemList, pdfMap);
        recyclerView.setAdapter(bookAdapter);
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fifthSem.this,MainActivity2.class);
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
