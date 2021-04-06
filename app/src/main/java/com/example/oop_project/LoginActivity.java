package com.example.oop_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private TextView t_username, t_password;
    private Button login;
    String username, password, user_type;
    Spinner s_usertype;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        t_password = findViewById(R.id.login_password_input);
        t_username = findViewById(R.id.login_username);
        login = findViewById(R.id.login_btn);
        s_usertype = findViewById(R.id.spinner_user);

        {
            Spinner mySpinner = (Spinner) findViewById(R.id.spinner_user);
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.users));
            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mySpinner.setAdapter(myAdapter);
        } //Spinner Initialization


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                username = t_username.getText().toString().trim();
                password = t_password.getText().toString().trim();
                user_type = s_usertype.getSelectedItem().toString().trim();


                if (username.isEmpty()) {
                    t_username.setError("Please enter the username!");
                }

                if (password.isEmpty()) {
                    t_password.setError("Please enter the password!");
                }

                if (!user_type.contains("Customer") | !user_type.contains("Retailer") | !user_type.contains("Wholesaler")) {
                    Toast.makeText(LoginActivity.this, "Please select a valid user type!", Toast.LENGTH_SHORT).show();
                }


                DatabaseReference rootref;
                rootref = FirebaseDatabase.getInstance().getReference();


                rootref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if ((snapshot.child("User").child(user_type).child(username).exists())) {
                            if ((snapshot.child("User").child(user_type).child(username).child(password).exists())) {

                                {

                                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

// Creating an Editor object to edit(write to the file)
                                    SharedPreferences.Editor myEdit = sharedPreferences.edit();

// Storing the key and its value as the data fetched from edittext
                                    myEdit.putString("username", username);
                                    myEdit.putString("usertype", user_type);

                                    myEdit.commit();


// Once the changes have been made,
// we need to commit to apply those changes made,
// otherwise, it will throw an error

                                } //Shared Pref Initialization

                                Intent intent = new Intent(LoginActivity.this, SendOTPActivity.class);
                                startActivity(intent);

                            } else {
                                t_password.setError("Enter the correct password");
                            }
                        } else {
                            t_username.setError("Invalid username, try again");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });


    }
}