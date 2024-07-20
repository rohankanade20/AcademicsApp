package com.example.rohan1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.rohan1.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    FirebaseAuth auth;
    FirebaseFirestore firestore;

    String loggedInUsername;

    //    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        databaseHelper = new DatabaseHelper(this);

        firestore= FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        binding.ForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgetActivity.class));
            }
        });



        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.loginEmail.getText().toString();
                String password = binding.loginPassword.getText().toString();
                if (email.isEmpty()) {
                    binding.loginEmail.setError("Enter Your email");

                }else if (password.isEmpty()) {
                    binding.loginPassword.setError("Enter Your password");

                }else {

                    auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                startActivity(new Intent(LoginActivity.this, MainActivity2.class));
                                finish();  //bcz parat back kelya vr return login page vr yayala nko
                            }else {

                                Toast.makeText(LoginActivity.this,"User does not exist",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        binding.signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);

            }
        });

    }

    private void showToast(String s) {
        Toast.makeText(LoginActivity.this,s,Toast.LENGTH_SHORT).show();
    }
}
