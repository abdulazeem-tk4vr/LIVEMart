package com.example.oop_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oop_project.R;

public class verifyenterotptwo extends AppCompatActivity {

    EditText iptn1,iptn2,iptn3,iptn4,iptn5,iptn6;
    TextView tview;
    Button setotp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifyenterotptwo);

        iptn1 =findViewById(R.id.inputotp1);
        iptn2 =findViewById(R.id.inputotp2);
        iptn3 =findViewById(R.id.inputotp3);
        iptn4 =findViewById(R.id.inputotp4);
        iptn5 =findViewById(R.id.inputotp5);
        iptn6 =findViewById(R.id.inputotp6);

        tview=findViewById(R.id.textmobileshow);

        tview.setText(String.format(
                "+91-%s",getIntent().getStringExtra("mobile")
                ));

        setotp = findViewById(R.id.verify_otp);
        setotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!iptn1.getText().toString().trim().isEmpty() && !iptn2.getText().toString().trim().isEmpty() && !iptn3.getText().toString().trim().isEmpty() && !iptn4.getText().toString().trim().isEmpty() && !iptn5.getText().toString().trim().isEmpty() && !iptn6.getText().toString().trim().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Verifying OTP",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Please enter the OTP properly!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        numberotpmove();

    }

    private void numberotpmove() {

        iptn1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
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
                if(!s.toString().trim().isEmpty()){
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
                if(!s.toString().trim().isEmpty()){
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
                if(!s.toString().trim().isEmpty()){
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
                if(!s.toString().trim().isEmpty()){
                    iptn6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });





    }






}