package com.example.oop_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SendOTPActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entermobilenumber);

        final EditText inputMobile = findViewById(R.id.phone_number);
        Button buttonGetOTP = findViewById(R.id.get_otp);
        ProgressBar progressBar = findViewById(R.id.progressbar_sendotp);

        buttonGetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputMobile.getText().toString().isEmpty()) {
                    Toast.makeText(SendOTPActivity.this, "Enter your mobile number please", Toast.LENGTH_SHORT).show();
                    return;
                }

//
//                {
//                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                            "+91" + inputMobile.getText().toString().trim(),
//                            60,
//                            TimeUnit.SECONDS,
//                            SendOTPActivity.this,
//                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                                @Override
//                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//                                    progressBar.setVisibility(View.GONE);
//                                    buttonGetOTP.setVisibility(View.VISIBLE);
//                                }
//
//                                @Override
//                                public void onVerificationFailed(@NonNull FirebaseException e) {
//                                    progressBar.setVisibility(View.GONE);
//                                    buttonGetOTP.setVisibility(View.VISIBLE);
//                                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//
//                                @Override
//                                public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                                    progressBar.setVisibility(View.GONE);
//                                    buttonGetOTP.setVisibility(View.VISIBLE);
//                                    Intent intent = new Intent(SendOTPActivity.this, verifyenterotptwo.class);
//                                    intent.putExtra("mobile", inputMobile.getText().toString().trim());
//                                    intent.putExtra("verificationId", verificationId);
//                                    startActivity(intent);
//
//                                }
//                            }
//
//                    );
//                }

                Intent intent = new Intent(getApplicationContext(), VerifyOTPActivity.class);
                intent.putExtra("mobile", inputMobile.getText().toString().trim());
                startActivity(intent);
            }
        });
    }
}