package com.example.rohan1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rohan1.databinding.ActivityDepartmentsBinding;
import com.example.rohan1.design.CustomAdapter;
import com.example.rohan1.model.SocialMedialist;

import java.util.ArrayList;

public class subjects extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Toolbar toolbar;
    private ArrayList<SocialMedialist> medialists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);
        toolbar = findViewById(R.id.toolbar);
        ListView listView = findViewById(R.id.listview);
        medialists = setIconAndName();
        toolbar.setTitle("Subjects");

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        CustomAdapter customAdapter = new CustomAdapter(subjects.this, medialists);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(this);


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
    private ArrayList<SocialMedialist> setIconAndName(){
        medialists = new ArrayList<>();
        medialists.add(new SocialMedialist(R.drawable.baseline_workspace_premium_24,"First Year Engineering"));
        medialists.add(new SocialMedialist(R.drawable.baseline_computer_24,"Computer Engineering"));
        medialists.add(new SocialMedialist(R.drawable.baseline_lightbulb_24,"Electronics & Communication"));
        medialists.add(new SocialMedialist(R.drawable.baseline_build_24,"Mechanical engineering"));
        medialists.add(new SocialMedialist(R.drawable.baseline_maps_home_work_24,"Civil engineering"));
        return medialists;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SocialMedialist list = medialists.get(position);
//        Toast.makeText(departments.this,"Social meadia..."+list.getSocialMediaName(),Toast.LENGTH_SHORT).show();
        if (position==0){
//           startActivity(new Intent(departments.this, years.class));
            Toast.makeText(this,"Social meadia..."+list.getSocialMediaName(),Toast.LENGTH_SHORT).show();
        } else {
//            Intent intent = new Intent(this, years.class);
//            startActivity(intent);
            Toast.makeText(this,"Social meadia..."+list.getSocialMediaName(),Toast.LENGTH_SHORT).show();
        }
    }

}