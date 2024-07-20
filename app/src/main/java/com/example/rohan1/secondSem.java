package com.example.rohan1;

//import android.content.ActivityNotFoundException;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import java.util.ArrayList;
//import java.util.List;
//
//public class secondSem extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//    private BookAdapter2 bookAdapter;
//    private List<BookItem> bookItemList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_second_sem);
//
//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
//
//        // Set up RecyclerView with GridLayoutManager
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
//        recyclerView.setLayoutManager(layoutManager);
//
//        // Initialize bookItemList
//        bookItemList = new ArrayList<>();
//        bookItemList.add(new BookItem("Java", R.drawable.books));
//        bookItemList.add(new BookItem("Flutter", R.drawable.books));
//        bookItemList.add(new BookItem("CPP", R.drawable.books));
//        bookItemList.add(new BookItem("DBMS", R.drawable.books));
//
//        // Set up adapter
//        bookAdapter = new BookAdapter2(this, bookItemList);
//        recyclerView.setAdapter(bookAdapter);
//    }
//}
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

public class secondSem extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookAdapter2 bookAdapter;
    private List<BookItem> bookItemList;
    Toolbar toolbar2;
    Animation animation;
    ImageView homebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_sem);
        toolbar2 = findViewById(R.id.toolbar2);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        homebutton = findViewById(R.id.home_button);



        toolbar2.setTitle("Subjects");

        setSupportActionBar(toolbar2);
        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation_on_book);
        recyclerView.startAnimation(animation);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Set up RecyclerView with GridLayoutManager
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        // Initialize bookItemList
        bookItemList = new ArrayList<>();
        bookItemList.add(new BookItem("Engineering Mathematics-II", R.drawable.trans));
        bookItemList.add(new BookItem("Engineering Chemistry", R.drawable.trans));
        bookItemList.add(new BookItem("Basic Electronics Engineering", R.drawable.trans));
        bookItemList.add(new BookItem("Engineering Machanics", R.drawable.trans));
        bookItemList.add(new BookItem("Engineering Graphics", R.drawable.trans));
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
                Intent intent = new Intent(secondSem.this,MainActivity2.class);
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
