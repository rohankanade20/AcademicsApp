package com.example.rohan1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class about_us extends AppCompatActivity {

    Toolbar toolbar;
TextView sakshi;
//   private int PERMISSION_CODE = 100;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        toolbar = findViewById(R.id.toolbar);
        sakshi = findViewById(R.id.sakshi);


        toolbar.setTitle("About Us");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
//
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},PERMISSION_CODE);
//        }
        sakshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneno = sakshi.getText().toString();
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+phoneno));
                startActivity(i);

            }
        });


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }



}