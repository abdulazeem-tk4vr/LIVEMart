package com.example.oop_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.widget.Toast.LENGTH_LONG;

public class RegisterActivity extends AppCompatActivity {


    private Button create;
    private EditText InputName, InputPhoneNumber, InputPassword , ConfirmPassword , EmailID;
    private Spinner UserType;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        {
            Spinner mySpinner = (Spinner) findViewById(R.id.type_customer);
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.users));
            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mySpinner.setAdapter(myAdapter);
        } //Spinner Initialization

        {
            create = (Button) findViewById(R.id.reg_btn);
            InputName = (EditText) findViewById(R.id.editText);
            InputPassword = (EditText) findViewById(R.id.password_reg);
            EmailID = (EditText) findViewById(R.id.email);
            ConfirmPassword = (EditText) findViewById(R.id.editText2);
            InputPhoneNumber = (EditText) findViewById(R.id.reg_phone_number);
            UserType = (Spinner) findViewById(R.id.type_customer);
        } //View Object Creation




        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = InputName.getText().toString().trim();
                String number = InputPhoneNumber.getText().toString().trim();
                String pwd = InputPassword.getText().toString().trim();
                String mail = EmailID.getText().toString().trim();
                String repwd = ConfirmPassword.getText().toString().trim();
                String user = UserType.getSelectedItem().toString().trim();




                ValidateName();
                ValidatePhone();
                ValidateEmail();
                ValidatePwd();
                ValidateUser();






                    DatabaseReference rootref;
                    rootref = FirebaseDatabase.getInstance().getReference();

                    rootref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (user.contains("Custoner")) {

                                if (!(snapshot.child("User").child("Customer").child(number).exists())) {
                                    HashMap<String, Object> userdataMap = new HashMap<>();
                                    userdataMap.put("phone", number);
                                    userdataMap.put("password", pwd);
                                    userdataMap.put("username", username);
                                    userdataMap.put("email", mail);

                                    rootref.child("User").child("Customer").child(username).updateChildren(userdataMap)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {


                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(RegisterActivity.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();


                                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                                        startActivity(intent);
                                                    } else {

                                                        Toast.makeText(RegisterActivity.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
                                                    }

                                                }
                                            });
                                } else {
                                    Toast.makeText(getApplicationContext(), "An account with this phone number already exists", LENGTH_LONG).show();
                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }

                        }
                            else if (user.contains("Retailer")){
                                if (!(snapshot.child("User").child("Retailer").child(number).exists())) {
                                    HashMap<String, Object> userdataMap = new HashMap<>();
                                    userdataMap.put("phone", number);
                                    userdataMap.put("password", pwd);
                                    userdataMap.put("username", username);
                                    userdataMap.put("email", mail);

                                    rootref.child("User").child("Retailer").child(username).updateChildren(userdataMap)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {


                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(RegisterActivity.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();


                                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                                        startActivity(intent);
                                                    } else {

                                                        Toast.makeText(RegisterActivity.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
                                                    }

                                                }
                                            });
                                } else {
                                    Toast.makeText(getApplicationContext(), "An account with this phone number already exists", LENGTH_LONG).show();
                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            }
                            else if (user.contains("Wholesaler")){

                                if (!(snapshot.child("User").child("Wholsaler").child(number).exists())) {
                                    HashMap<String, Object> userdataMap = new HashMap<>();
                                    userdataMap.put("phone", number);
                                    userdataMap.put("password", pwd);
                                    userdataMap.put("username", username);
                                    userdataMap.put("email", mail);

                                    rootref.child("User").child("Wholesaler").child(username).updateChildren(userdataMap)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {


                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(RegisterActivity.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();


                                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                                        startActivity(intent);
                                                    } else {

                                                        Toast.makeText(RegisterActivity.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
                                                    }

                                                }
                                            });
                                } else {
                                    Toast.makeText(getApplicationContext(), "An account with this phone number already exists", LENGTH_LONG).show();
                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            }

                    }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


            }




            private void ValidateName() {

                String name = InputName.getText().toString();
                if(name.isEmpty()){
                    InputName.setError("Please enter your Username !");
                }


            }

            private void ValidatePhone() {

                String phone = InputPhoneNumber.getText().toString();

                if(phone.isEmpty()){
                    InputPhoneNumber.setError("Please enter your contact number !");
                }

            }

            private void ValidateEmail() {

                String mail = EmailID.getText().toString();

                String regex = "^(.+)@(.+)$";

                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(mail);

                if (mail.isEmpty()) {
                    EmailID.setError("Please enter your email ID");
                } else if(!(matcher.matches())){
                    EmailID.setError("Please enter a valid email ID");
                }
            }

            private void ValidatePwd() {
                String password = InputPassword.getText().toString();
                String repass = ConfirmPassword.getText().toString();

                if ( password.isEmpty()){
                    InputPassword.setError("Please enter your Password !");
                }
                else if (!(password.equals(repass))) {
                    ConfirmPassword.setError("Passwords do not match !");
                }
            }

            private void ValidateUser() {
                String user_type = UserType.getSelectedItem().toString();
                if(!(user_type.contains("Customer") || user_type.contains("Wholesaler")  || user_type.contains("Retailer"))){
                    Toast.makeText(getApplicationContext(),"Please select a valid user", LENGTH_LONG).show();
                }
            }











        });

    }

}




