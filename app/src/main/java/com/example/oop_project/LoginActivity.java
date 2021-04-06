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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private TextView t_username,t_password,t_user_type;
    private Button login;
    String username,password,user_type;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        t_password = findViewById(R.id.login_password_input);
        t_username = findViewById(R.id.login_username);
        t_user_type = findViewById(R.id.user_type_text);
        login = findViewById(R.id.login_btn);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                {

                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);

// Creating an Editor object to edit(write to the file)
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();

// Storing the key and its value as the data fetched from edittext
                    myEdit.putString("username", t_username.getText().toString().trim());
                    myEdit.putString("usertype", t_user_type.getText().toString().trim());

                    myEdit.commit();


// Once the changes have been made,
// we need to commit to apply those changes made,
// otherwise, it will throw an error

                }

                username=t_username.getText().toString().trim();
                password=t_password.getText().toString().trim();
                user_type= t_user_type.getText().toString().trim();

                Intent intent = new Intent(LoginActivity.this,SendOTPActivity.class);
                startActivity(intent);

//                if(username.isEmpty()){
//                    t_username.setError("Please enter the username");
//                }
//
//                if(password.isEmpty()){
//                    t_password.setError("Please enter the password");
//                }
//
//                if(user_type.isEmpty()){
//                    t_user_type.setError("Please enter a user type");
//                }else if (!user_type.contains("Customer") | !user_type.contains("Retailer")|!user_type.contains("Wholesaler")){
//                    t_user_type.setError("Please enter the correct user type");
//                }
//
//
//                DatabaseReference rootref;
//                rootref = FirebaseDatabase.getInstance().getReference();
//
//
//
//                rootref.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        if((snapshot.child("User").child(user_type).child(username).exists())){
//                            if((snapshot.child("User").child(user_type).child(username).child(password).exists())){
//
//
//
//                            }else{
//                                t_password.setError("Enter the correct password");
//                            }
//                        }else{
//                            t_username.setError("Invalid username, try again");
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });



            }
        });








    }
}