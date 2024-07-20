//package com.example.rohan1;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.widget.Toast;
//
//public class MainActivity extends AppCompatActivity {
//    Toolbar toolbar;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        toolbar = findViewById(R.id.toolbar);
//
//
//        setSupportActionBar(toolbar);
//        if ((getSupportActionBar() != null)) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setTitle("My App");
//
//        }
//        toolbar.setTitle("My App");
//        toolbar.setSubtitle("My Toolbar");
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        new MenuInflater(this).inflate(R.menu.opt_menu,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int itemId = item.getItemId();
//        if (itemId == R.id.account) {
//            Intent intent = new Intent(this, SignupActivity.class);
//            startActivity(intent);
//            Toast.makeText(this, "Create New Account", Toast.LENGTH_SHORT).show();
//        } else if (itemId == R.id.help) {
//            Toast.makeText(this, "Contact US", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "Rate US", Toast.LENGTH_SHORT).show();
//
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void showToast(String message) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//    }
//}