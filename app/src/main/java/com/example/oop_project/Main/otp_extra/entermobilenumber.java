//package com.example.oop_project.Main.otp_extra;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//import com.example.oop_project.R;
//import com.google.firebase.FirebaseException;
//import com.google.firebase.auth.PhoneAuthCredential;
//import com.google.firebase.auth.PhoneAuthProvider;
//
//import java.util.concurrent.TimeUnit;
//
//public class entermobilenumber extends AppCompatActivity {
//
//    EditText enternumber;
//    Button getotpbuttoon;
//    ProgressBar progressBar;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_entermobilenumber);
//
//        enternumber = findViewById(R.id.phone_number);
//        getotpbuttoon = findViewById(R.id.get_otp);
//        progressBar = findViewById(R.id.progressbar_verifyotp);
//
//        getotpbuttoon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!enternumber.getText().toString().trim().isEmpty()) {
//                    if ((enternumber.getText().toString().trim().length() == 10)) {
//
//
//                        progressBar.setVisibility(View.VISIBLE);
//                        getotpbuttoon.setVisibility(View.INVISIBLE);
//
//                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                                "+91" + enternumber.getText().toString().trim(),
//                                60,
//                                TimeUnit.SECONDS,
//                                entermobilenumber.this,
//                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                                    @Override
//                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//                                        progressBar.setVisibility(View.GONE);
//                                        getotpbuttoon.setVisibility(View.VISIBLE);
//                                    }
//
//                                    @Override
//                                    public void onVerificationFailed(@NonNull FirebaseException e) {
//                                        progressBar.setVisibility(View.GONE);
//                                        getotpbuttoon.setVisibility(View.VISIBLE);
//                                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                                    }
//
//                                    @Override
//                                    public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                                        progressBar.setVisibility(View.GONE);
//                                        getotpbuttoon.setVisibility(View.VISIBLE);
//                                        Intent intent = new Intent(getApplicationContext(), verifyenterotptwo.class);
//                                        intent.putExtra("mobile", enternumber.getText().toString().trim());
//                                        intent.putExtra("backendotp", backendotp);
//                                        startActivity(intent);
//
//                                    }
//                                }
//
//                        );
//
//
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Please enter the correct Mobile Number", Toast.LENGTH_SHORT).show();
//                    }
//
//                } else {
//                    Toast.makeText(getApplicationContext(), "Please enter your Mobile Number", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });
//
//
//    }
//}