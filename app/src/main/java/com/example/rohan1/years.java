package com.example.rohan1;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ExpandableListView;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class years extends AppCompatActivity {
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_years);
        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Academics Year");

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Initialize ExpandableListView
        expandableListView = findViewById(R.id.expandableListView);

        // Load the animation
        Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);

        // Apply the animation to the ExpandableListView
        expandableListView.setAnimation(slideUp);

        // Prepare list data
        prepareListData();

        // Create and set adapter
        HashMap<String, Integer> listGroupIcons = null;
        expandableListAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild, listGroupIcons);
        expandableListView.setAdapter(expandableListAdapter);
        // Load the animation


        // Apply animation to parent items sequentially



//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(years.this, subjects.class);
//               startActivity(intent);

        // Set click listener for child items
//        expandableListAdapter.setOnChildClickListener(new ExpandableListAdapter.OnChildClickListener(){
//            @Override
//            public void onChildClick(int groupPosition, int childPosition) {
//                Intent intent = new Intent(years.this, subjects.class);
//                startActivity(intent);
//            }
//
//            @Override
//            public void onChildClick(String childItem) {
//                Intent intent = new Intent(years.this, subjects.class);
//                startActivity(intent);
//            }
//        });

        // Define icons for groups
        listGroupIcons = new HashMap<>();
        listGroupIcons.put("First Year", R.drawable.baseline_saved_search_24);
        listGroupIcons.put("Second Year", R.drawable.baseline_saved_search_24);
        listGroupIcons.put("Third Year", R.drawable.baseline_saved_search_24);
        listGroupIcons.put("Fourth Year", R.drawable.baseline_saved_search_24);

        // Create adapter with icons
        expandableListAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild, listGroupIcons);
        expandableListView.setAdapter(expandableListAdapter);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }






    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        // Adding group data
        listDataHeader.add("First Year");
        listDataHeader.add("Second Year");
        listDataHeader.add("Third Year");
        listDataHeader.add("Fourth Year");

        // Adding child data
        List<String> group1 = new ArrayList<>();
        group1.add("1st Semister");
        group1.add("2nd Semister");


        List<String> group2 = new ArrayList<>();
        group2.add("3rd Semister");
        group2.add("4th Semister");

        List<String> group3 = new ArrayList<>();
        group3.add("5th Semister");
        group3.add("6th Semister");

        List<String> group4 = new ArrayList<>();
        group4.add("7th Semister");
        group4.add("8th Semister");


        listDataChild.put(listDataHeader.get(0), group1);
        listDataChild.put(listDataHeader.get(1), group2);
        listDataChild.put(listDataHeader.get(2), group3);
        listDataChild.put(listDataHeader.get(3), group4);
    }
}


