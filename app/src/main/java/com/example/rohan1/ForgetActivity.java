package com.example.rohan1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.rohan1.databinding.ActivityForgetBinding;
import com.example.rohan1.databinding.ActivityMain2Binding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetActivity extends AppCompatActivity {
    ActivityForgetBinding binding;

    FirebaseAuth auth;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgetBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Renew Password");

        setSupportActionBar(toolbar);


         auth = FirebaseAuth.getInstance();

        binding.buttonForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.editTextEmail.getText().toString();
// progressDialog.dismiss();
                if (email.isEmpty()){
                    binding.editTextEmail.setError("Enter Email");
                }else {
                    auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
//                                progressDialog.dismiss();
                                Toast.makeText(ForgetActivity.this,"Please check your Email",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ForgetActivity.this,LoginActivity.class));
                            }else{
//                                progressDialog.dismiss();
                                Toast.makeText(ForgetActivity.this,task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });

        binding.textViewBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgetActivity.this,LoginActivity.class));
            }
        });

        binding.imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgetActivity.this,LoginActivity.class));
            }
        });

    }
}