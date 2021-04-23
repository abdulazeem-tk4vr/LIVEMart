//package com.example.oop_project.Main.otp_extra;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.oop_project.R;
//import com.example.oop_project.Main.addproduct;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.FirebaseException;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.PhoneAuthCredential;
//import com.google.firebase.auth.PhoneAuthProvider;
//
//import java.util.concurrent.TimeUnit;
//
//public class verifyenterotptwo extends AppCompatActivity {
//
//    EditText iptn1, iptn2, iptn3, iptn4, iptn5, iptn6;
//    TextView tview;
//
//    String getotpbackend;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_verifyenterotptwo);
//
//        iptn1 = findViewById(R.id.inputotp1);
//        iptn2 = findViewById(R.id.inputotp2);
//        iptn3 = findViewById(R.id.inputotp3);
//        iptn4 = findViewById(R.id.inputotp4);
//        iptn5 = findViewById(R.id.inputotp5);
//        iptn6 = findViewById(R.id.inputotp6);
//
//        tview = findViewById(R.id.textmobileshow);
//
//        tview.setText(String.format(
//                "+91-%s", getIntent().getStringExtra("mobile")
//        ));
//
//        getotpbackend = getIntent().getStringExtra("backendotp");
//
//        final ProgressBar progressBar1 = findViewById(R.id.progressbar_sendotp);
//
//        final Button setotp = findViewById(R.id.verify_otp);
//
//        setotp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                if (!iptn1.getText().toString().trim().isEmpty() && !iptn2.getText().toString().trim().isEmpty() && !iptn3.getText().toString().trim().isEmpty() && !iptn4.getText().toString().trim().isEmpty() && !iptn5.getText().toString().trim().isEmpty() && !iptn6.getText().toString().trim().isEmpty()) {
//                    String entercodeotp = iptn1.getText().toString() +
//                            iptn2.getText().toString() +
//                            iptn3.getText().toString() +
//                            iptn4.getText().toString() +
//                            iptn5.getText().toString() +
//                            iptn6.getText().toString();
//                    if (getotpbackend != null) {
//                        progressBar1.setVisibility(View.VISIBLE);
//                        setotp.setVisibility(View.INVISIBLE);
//
//                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
//                                getotpbackend, entercodeotp
//                        );
//                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
//                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<AuthResult> task) {
//                                        progressBar1.setVisibility(View.GONE);
//                                        setotp.setVisibility(View.VISIBLE);
//
//                                        if (task.isSuccessful()) {
//                                            Intent intent = new Intent(getApplicationContext(), addproduct.class);
//                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                            startActivity(intent);
//                                        } else {
//                                            Toast.makeText(verifyenterotptwo.this, "Enter the cprrect otp", Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Please check Internet Connection", Toast.LENGTH_SHORT).show();
//                    }
//
////                    Toast.makeText(getApplicationContext(), "Verifying OTP", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getApplicationContext(), "Please enter the OTP properly!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        numberotpmove();
//
//
//        findViewById(R.id.resend_otp).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                        "+91" + getIntent().getStringExtra("mobile"),
//                        60,
//                        TimeUnit.SECONDS,
//                        verifyenterotptwo.this,
//                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                            @Override
//                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//
//                            }
//
//                            @Override
//                            public void onVerificationFailed(@NonNull FirebaseException e) {
//
//                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void onCodeSent(@NonNull String newbackendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//
//                                getotpbackend = newbackendotp;
//                                Toast.makeText(verifyenterotptwo.this, "OTP sent successfully", Toast.LENGTH_SHORT).show();
//
//                            }
//                        }
//
//                );
//
//            }
//        });
//
//    }
//
//    private void numberotpmove() {
//
//        iptn1.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (!s.toString().trim().isEmpty()) {
//                    iptn2.requestFocus();
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        iptn2.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (!s.toString().trim().isEmpty()) {
//                    iptn3.requestFocus();
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        iptn3.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (!s.toString().trim().isEmpty()) {
//                    iptn4.requestFocus();
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        iptn4.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (!s.toString().trim().isEmpty()) {
//                    iptn5.requestFocus();
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        iptn5.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (!s.toString().trim().isEmpty()) {
//                    iptn6.requestFocus();
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//
//    }
//
//
//
//}