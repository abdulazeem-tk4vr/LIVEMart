package com.example.oop_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class entermobilenumber extends AppCompatActivity {

    EditText enternumber;
    Button getotpbuttoon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entermobilenumber);

         enternumber = findViewById(R.id.phone_number);
         getotpbuttoon = findViewById(R.id.get_otp);

         getotpbuttoon.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(!enternumber.getText().toString().trim().isEmpty()){
                     if((enternumber.getText().toString().trim().length() == 10)){

                         Intent intent = new Intent(getApplicationContext(), verifyenterotptwo.class);
                         intent.putExtra("mobile",enternumber.getText().toString().trim());
                         startActivity(intent);

                     } else {
                         Toast.makeText(getApplicationContext(),"Please enter the correct Mobile Number",Toast.LENGTH_SHORT).show();
                     }

                 } else {
                     Toast.makeText(getApplicationContext(),"Please enter your Mobile Number",Toast.LENGTH_SHORT).show();

                 }
             }
         });


    }
}