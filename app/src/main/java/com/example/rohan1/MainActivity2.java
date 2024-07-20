package com.example.rohan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.Objects;

public class MainActivity2 extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView notes, bgapp, clovering;

    //    TextView text,exploretext;
    LinearLayout splashtext, exploretext;
    Animation frombottom;
    ImageView imageView;
    TextView usertext;
    Toolbar toolbar;
    private boolean doubleBackToExitPressedOnce = false;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;
    private ImageView profileImageView;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationview);
        toolbar = findViewById(R.id.toolbar);
//        notes = findViewById(R.id.notes);
        bgapp = (ImageView) findViewById(R.id.bgapp);
        clovering = (ImageView) findViewById(R.id.clovering);
//        text = (TextView)findViewById(R.id.text);
        exploretext = (LinearLayout) findViewById(R.id.exploretext);
        splashtext = (LinearLayout) findViewById(R.id.splashtext);
        imageView = findViewById(R.id.welcomeImage);
        usertext = findViewById(R.id.usertext);
        auth = FirebaseAuth.getInstance();


        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        exploretext.startAnimation(frombottom);


        toolbar.setTitle("Explore");

        setSupportActionBar(toolbar);


//        bganim = AnimationUtils.loadAnimation(this,R.anim.bganim);
        bgapp.animate().translationY(-1700).setDuration(900).setStartDelay(500);
        clovering.animate().alpha(0).setDuration(800).setStartDelay(600);
        splashtext.animate().translationY(140).alpha(0).setDuration(1100).setStartDelay(300);


        Glide.with(this).asGif().load(R.drawable.searchbook1).into(imageView);
//        text.animate().alpha(0).setDuration(800).setStartDelay(250);
//        text.animate().alpha().setDuration(800).setStartDelay(250);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity2.this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        // Enable the custom icon for drawer toggle
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); // Enable the home button for the drawer toggle
        getSupportActionBar().setHomeButtonEnabled(true);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.about) {
                    showToast("About Us");

//                    Intent about = new Intent(getApplicationContext(), about_us.class);
//                    startActivity(about);

                } else if (id == R.id.privacy) {
                    showToast("read our privacy policy");
                } else if (id == R.id.share) {
                    showToast("Share This App");

                } else {
                    AlertDialog.Builder delDialog = new AlertDialog.Builder(MainActivity2.this);
                    delDialog.setTitle("Log out?");
                    delDialog.setMessage("Are you want to Log out?");
                    delDialog.setIcon(R.drawable.baseline_logout_24);
                    delDialog.setCancelable(false);// This allows the dialog to be canceled by the back button

                    delDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                             auth.signOut();
                            Intent intent = new Intent(MainActivity2.this, LoginActivity.class);
                            startActivity(intent);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //all activities above it in the stack are cleared.
                            startActivity(intent);
                            showToast("LogOut Successfully");

                        }


                    });

                    delDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            delDialog.setCancelable(true);

                        }
                    });
                    delDialog.show();
                }
                return true;
            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, departments.class);
                startActivity(intent);
            }
        });

        // Retrieve the username from the intent extras
        String username = " ";
        

        // Get the header view of the navigation drawer
        TextView textViewUsername = navigationView.getHeaderView(0).findViewById(R.id.usertext);

        // Set the username to the TextView in the navigation drawer header
        textViewUsername.setText(username);

        // Retrieve the username from the intent extras if the user is logged in
//        String username = " ";
//        if (isUserLoggedIn() ) {
//            username = isLoggedIn();
//        }
//
//        // Set the username to the TextView in the navigation drawer header
//        TextView textViewUsername = navigationView.getHeaderView(0).findViewById(R.id.usertext);
//        textViewUsername.setText(username);
//

    }


    boolean setLoginStatus(boolean status) {
        SharedPreferences sharedPreferences = getSharedPreferences("login_status", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", status);
        editor.apply();
        return status;
    }

    private String isLoggedIn() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        return databaseHelper.getLoggedInUserEmail();
    }

    private boolean isUserLoggedIn() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        return databaseHelper.isUserLoggedIn();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opt_menu, menu);
        return true;
//        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.account) {
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Create New Account", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.help) {
            Toast.makeText(this, "Contact US", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Rate US", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        // Check if back button is pressed once
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                finishAffinity();  //which is clo
                return;
            }

            // Set the flag to true and show toast message
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();

            // Reset the flag after 2 seconds
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

        private void showToast (String message){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }


    }
