package com.example.rohan1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class splash extends AppCompatActivity {
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

         auth = FirebaseAuth.getInstance();



        Thread td = new Thread(){
            public void run(){
                try {
                    sleep(2800);
//                    DatabaseHelper databaseHelper = new DatabaseHelper(splash.this);
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    if (firebaseUser!= null) {
                        // If user is logged in, redirect to MainActivity2
                        Intent mainIntent = new Intent(splash.this, MainActivity2.class);
                        startActivity(mainIntent);


                    } else {
                        // If user is not logged in, redirect to SignupActivity
                        Intent signupIntent = new Intent(splash.this, SignupActivity.class);
                        startActivity(signupIntent);
                    }

                }catch (Exception ex){

                    ex.printStackTrace();
                }finally {

//                    Intent splash = new Intent(splash.this, SignupActivity.class);
//                    startActivity(splash);
                    finish();
                }
            }
        };
        td.start();
    }
}
