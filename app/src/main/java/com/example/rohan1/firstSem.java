package com.example.rohan1;

//import android.content.ActivityNotFoundException;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.Toast;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class firstSem extends AppCompatActivity {
//
//    private ListView listView;
//    private String[] subjects = {"Java", "Flutter", "CPP", "DBMS"};
//    private FirebaseStorage storage;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_first_sem);
//
//        listView = findViewById(R.id.listView);
//        storage = FirebaseStorage.getInstance();
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, subjects);
//        listView.setAdapter(adapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String subject = subjects[position];
//                retrieveAndOpenPdf(subject);
//            }
//        });
//    }
//
////    private void retrieveAndOpenPdf(String pdfName, final String subject) {
////        StorageReference pdfRef = storage.getReference().child(pdfName);
////        pdfRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
////            @Override
////            public void onSuccess(Uri uri) {
////                openPdf(uri, subject);
////            }
////        }).addOnFailureListener(new OnFailureListener() {
////            @Override
////            public void onFailure(@NonNull Exception e) {
////                Toast.makeText(firstSem.this, "Failed to retrieve PDF", Toast.LENGTH_SHORT).show();
////            }
////        });
////    }
//
//    private void retrieveAndOpenPdf(String subject) {
//        // Define a map to associate subjects with PDF file names
//        Map<String, String> pdfMap = new HashMap<>();
//        pdfMap.put("Java", "dummy.pdf");
//        pdfMap.put("Flutter", "rohan1.pdf");
//        pdfMap.put("CPP", "rohan2.pdf");
//        pdfMap.put("DBMS", "rohan3.pdf");
//
//        // Retrieve the PDF file name based on the selected subject
//        String pdfName = pdfMap.get(subject);
//        if (pdfName == null) {
//            Toast.makeText(firstSem.this, "PDF not found for subject: " + subject, Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        // Retrieve and open the PDF file
//        StorageReference pdfRef = storage.getReference().child(pdfName);
//        pdfRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                openPdf(uri,subject);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(firstSem.this, "Failed to retrieve PDF: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//
//    private void openPdf(Uri pdfUri, String subject) {
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setDataAndType(pdfUri, "application/pdf");
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        try {
//            startActivity(intent);
//        } catch (ActivityNotFoundException e) {
//            Toast.makeText(firstSem.this, "No application available to view PDF", Toast.LENGTH_SHORT).show();
//        }
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

public class firstSem extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BookAdapter2 bookAdapter;
    private List<BookItem> bookItemList;
    Toolbar toolbar2;
    Animation animation;
    ImageView homebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_sem);
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
        bookItemList.add(new BookItem("Engineering Mathematics-I", R.drawable.trans));
        bookItemList.add(new BookItem("Engineering Physics", R.drawable.trans));
        bookItemList.add(new BookItem("System in Mechanical Engineering", R.drawable.trans));
        bookItemList.add(new BookItem("Basic Electrical Engineering", R.drawable.trans));
        bookItemList.add(new BookItem("Programming and Problem Solving", R.drawable.trans));

        // Set up mapping between subjects and PDF file names
        Map<String, String> pdfMap = new HashMap<>();
        pdfMap.put("Engineering Mathematics-I", "dummy.pdf");
        pdfMap.put("Engineering Physics", "dummy.pdf");
        pdfMap.put("System in Mechanical Engineering", "dummy.pdf");
        pdfMap.put("Basic Electrical Engineering", "dummy.pdf");
        pdfMap.put("Programming and Problem Solving", "dummy.pdf");

        // Set up adapter
        bookAdapter = new BookAdapter2(this, bookItemList, pdfMap);
        recyclerView.setAdapter(bookAdapter);

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(firstSem.this,MainActivity2.class);
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
