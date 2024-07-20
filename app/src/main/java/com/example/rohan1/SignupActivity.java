package com.example.rohan1;

import static androidx.fragment.app.FragmentManager.TAG;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.rohan1.databinding.ActivitySignupBinding;
import com.example.rohan1.model.My_Models;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {
//    private static final int RC_NOTIFICATION = 99;
private static final int RC_PERMISSIONS = 99;
    private static final String[] PERMISSIONS = {Manifest.permission.POST_NOTIFICATIONS, Manifest.permission.CALL_PHONE};

       ActivitySignupBinding binding;
      FirebaseAuth auth;
   FirebaseFirestore firestore;

//    ActivitySignupBinding binding;
//    DatabaseHelper databaseHelper;
//    FirebaseDatabase database;
//    DatabaseReference reference;
//    EditText signupEmail,signupPassword,confirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
       FirebaseMessaging.getInstance().subscribeToTopic("Notify")
               .addOnCompleteListener(new OnCompleteListener<Void>() {
                   @Override
                   public void onComplete(@NonNull Task<Void> task) {
                       String msg = "Done";
                       if (!task.isSuccessful()){
                           msg = "Failed";
                       }
                   }
               });

//        databaseHelper = new DatabaseHelper(this);
        firestore= FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();


        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.signupName.getText().toString();
                String email = binding.signupEmail.getText().toString();
                String password = binding.signupPassword.getText().toString();
                String confirmPassword = binding.signupConfirm.getText().toString();

                if (name.isEmpty() && email.isEmpty() && password.isEmpty() && confirmPassword.isEmpty()){
                    showToast("All Fields Are Mandatory");
                }else if (!email.isEmpty() && !isValidEmail(email)){
                   binding.signupEmail.setError("Enter Valid Email ID");
                }else if (name.isEmpty()){
                    binding.signupName.setError("Enter Your Name");
                } else if (email.isEmpty()) {
                    binding.signupEmail.setError("Enter Your email");

                }else if (password.isEmpty()) {
                    binding.signupPassword.setError("Enter Your password");

                }else if (!isPasswordValid(password)) {
                   showToast("Password must have length of 5 & contains special character & Numbers");

                }else if (confirmPassword.isEmpty()) {
                    binding.signupConfirm.setError("Confirm Your Password");

                }else if (!confirmPassword.equals(password) ) {
                    binding.signupConfirm.setError("Password is not matched");

                }else{
                    auth.createUserWithEmailAndPassword(binding.signupEmail.getText().toString(), binding.signupPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String userId = task.getResult().getUser().getUid();
                                String userEmail = binding.signupEmail.getText().toString();

                                // Check if the user email already exists in the Firestore collection

                                firestore.collection("users")
                                        .whereEqualTo("email", userEmail)
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    if (!task.getResult().isEmpty()) {
                                                        // User with the same email already exists
                                                        showToast("User with this email already exists");
                                                    } else {
                                                        // User doesn't exist, proceed with registration
                                                        My_Models models = new My_Models(name, email, password);
                                                        firestore.collection("users")
                                                                .document(userId)
                                                                .set(models)
                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                        if (task.isSuccessful()) {
                                                                            // Registration successful
                                                                            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                                                            intent.putExtra("name", name);
                                                                            startActivity(intent);
                                                                            showToast("SignUp Successful!!");
                                                                        } else {
                                                                            showToast("Registration failed: " + task.getException().getLocalizedMessage());
                                                                        }
                                                                    }
                                                                });
                                                    }
                                                } else {
                                                    showToast("Error checking email availability: " + task.getException().getLocalizedMessage());
                                                }
                                            }
                                        });
                            } else {
                                showToast("Registration failed: " + task.getException().getLocalizedMessage());
                            }
                        }
                    });

//                    auth.createUserWithEmailAndPassword(binding.signupEmail.getText().toString(),binding.signupPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()){
//                                My_Models models= new My_Models(name,email,password);
//                                String id = task.getResult().getUser().getUid();
//                                firestore.collection("users").document().set(models).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//                                        if (task.isSuccessful()){
//
//                                            Toast.makeText(SignupActivity.this,task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
//
//                            }
//                        }
//                    });
//                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
//                    intent.putExtra("name",name);
//                    startActivity(intent);
//                   showToast("SignUp Successfull !!");

                }
            }
        });

        binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });




        // permission for noticiation



        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            requestPermissionsIfNeeded();
        }




    }
    private void requestPermissionsIfNeeded() {
        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, RC_PERMISSIONS);
        }
    }

    private boolean allPermissionsGranted() {
        for (String permission : PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RC_PERMISSIONS) {
            boolean allPermissionsGranted = true;
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    allPermissionsGranted = false;
                    break;
                }
            }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == RC_NOTIFICATION){
//            if (grantResults[0]== PackageManager.PERMISSION_GRANTED){
//                Toast.makeText(this,"Permission Allowed",Toast.LENGTH_SHORT).show();
//            }else {
//                Toast.makeText(this,"Permission Not Allowed",Toast.LENGTH_SHORT).show();
//
//            }
//        }
//    }
        }}

    private boolean isValidEmail (CharSequence target){

        return (target != null && Patterns.EMAIL_ADDRESS.matcher(target).matches());
        }

    

//    private boolean containsAtLeastOneDigit(CharSequence target) {
//        for (int i = 0; i < target.length(); i++) {
//            if (Character.isDigit(target.charAt(i))) {
//                return true;
//            }
//        }
//        return false;
//    }

    private boolean isPasswordValid(String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$).{5,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private void showToast (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}
