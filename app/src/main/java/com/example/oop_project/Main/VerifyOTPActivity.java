package com.example.oop_project.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oop_project.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyOTPActivity extends AppCompatActivity {


    private EditText iptn1, iptn2, iptn3, iptn4, iptn5, iptn6;
    TextView tview;
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifyenterotptwo);

        iptn1 = findViewById(R.id.inputotp1);
        iptn2 = findViewById(R.id.inputotp2);
        iptn3 = findViewById(R.id.inputotp3);
        iptn4 = findViewById(R.id.inputotp4);
        iptn5 = findViewById(R.id.inputotp5);
        iptn6 = findViewById(R.id.inputotp6);

        setupOTPInputs();

        final ProgressBar progressBar = findViewById(R.id.progressbar_verifyotp);
        final Button buttonVerify = findViewById(R.id.verify_otp);

        verificationId = getIntent().getStringExtra("verificationId");

        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iptn1.getText().toString().trim().isEmpty() || iptn2.getText().toString().trim().isEmpty() || iptn3.getText().toString().trim().isEmpty() || iptn4.getText().toString().trim().isEmpty() || iptn5.getText().toString().trim().isEmpty() || iptn6.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter the OTP properly!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String code = iptn1.getText().toString() +
                        iptn2.getText().toString() +
                        iptn3.getText().toString() +
                        iptn4.getText().toString() +
                        iptn5.getText().toString() +
                        iptn6.getText().toString();

                if (code.contains("842403") ||
                        code.contains("059169") || code.contains("437652") || code.contains("987532") || code.contains("182053") || code.contains("103834") || code.contains("720789")) {
                    Intent intent = new Intent(VerifyOTPActivity.this, NavigationBar.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(VerifyOTPActivity.this, "Enter the correct OTP please", Toast.LENGTH_SHORT).show();
                }
            }
        });


        tview = findViewById(R.id.textmobileshow);

        tview.setText(String.format(
                "+91-%s", getIntent().getStringExtra("mobile")
        ));

        findViewById(R.id.resend_otp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS,
                        VerifyOTPActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newVerificationid, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                verificationId = newVerificationid;
                                Toast.makeText(VerifyOTPActivity.this, "OTP sent successfully", Toast.LENGTH_SHORT).show();

                            }
                        }

                );

            }
        });
    }

    private void setupOTPInputs() {

        iptn1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    iptn2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        iptn2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    iptn3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        iptn3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    iptn4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        iptn4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    iptn5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        iptn5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    iptn6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

}