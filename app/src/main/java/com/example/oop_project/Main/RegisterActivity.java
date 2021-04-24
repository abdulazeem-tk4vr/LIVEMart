package com.example.oop_project.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oop_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.widget.Toast.LENGTH_LONG;

public class RegisterActivity extends AppCompatActivity implements LocationListener {


    private Button create, button_location;
    private EditText InputName, InputPhoneNumber, InputPassword, ConfirmPassword, EmailID;
    private Spinner UserType;
    LocationManager locationManager;
    Double user_latitude, user_longitude;
    String s_long,s_lat;
    private TextView Longitude_tfield, Latitude_tfield;


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
            button_location = findViewById(R.id.button_location);
            Longitude_tfield = findViewById(R.id.longitude_text);
            Latitude_tfield = findViewById(R.id.latitude_text);

        } //View Object Creation

        if (ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(RegisterActivity.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }


        button_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getLocation();


            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = InputName.getText().toString().trim();
                String number = InputPhoneNumber.getText().toString().trim();
                String pwd = InputPassword.getText().toString().trim();
                String mail = EmailID.getText().toString().trim();
                String repwd = ConfirmPassword.getText().toString().trim();
                String user = UserType.getSelectedItem().toString().trim();
                       String longitude = Longitude_tfield.getText().toString().trim();
                       String latitude = Latitude_tfield.getText().toString().trim();


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

                        if (user.contains("Customer")) {

                            if (!(snapshot.child("User").child("Customer").child(username).exists())) {
                                HashMap<String, Object> userdataMap = new HashMap<>();
                                userdataMap.put("phone", number);
                                userdataMap.put("password", pwd);
                                userdataMap.put("username", username);
                                userdataMap.put("email", mail);
                                userdataMap.put("latitude", s_long);
                                userdataMap.put("longitude", s_lat);
                                userdataMap.put("UID", "UID_3");


                                rootref.child("User").child("Customer").child(username).child("Details").updateChildren(userdataMap)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {


                                                if (task.isSuccessful()) {
                                                    Toast.makeText(RegisterActivity.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(intent);
                                                } else {
                                                    Toast.makeText(RegisterActivity.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
                                                }

                                            }
                                        });
                            } else {
                                Toast.makeText(getApplicationContext(), "An account with this username already exists", LENGTH_LONG).show();
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }

                        } else if (user.contains("Retailer")) {
                            if (!(snapshot.child("User").child("Retailer").child(username).exists())) {
                                HashMap<String, Object> userdataMap = new HashMap<>();
                                userdataMap.put("phone", number);
                                userdataMap.put("password", pwd);
                                userdataMap.put("username", username);
                                userdataMap.put("email", mail);
                                userdataMap.put("latitude", s_lat);
                                userdataMap.put("longitude", s_long);
                                userdataMap.put("UID", "UID_3");



                                Log.i("Wazzup", "is it working");

                                rootref.child("User").child("Retailer").child(username).child("Details").updateChildren(userdataMap)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {


                                                if (task.isSuccessful()) {
                                                    Toast.makeText(RegisterActivity.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();


                                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(intent);
                                                } else {

                                                    Toast.makeText(RegisterActivity.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
                                                }

                                            }
                                        });
                            } else {
                                Toast.makeText(getApplicationContext(), "An account with this username already exists", LENGTH_LONG).show();
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        } else if (user.contains("Wholesaler")) {

                            if (!(snapshot.child("User").child("Wholesaler").child(username).exists())) {
                                HashMap<String, Object> userdataMap = new HashMap<>();
                                userdataMap.put("phone", number);
                                userdataMap.put("password", pwd);
                                userdataMap.put("username", username);
                                userdataMap.put("email", mail);
                                userdataMap.put("latitude", s_lat);
                                userdataMap.put("longitude", s_long);

                                rootref.child("User").child("Wholesaler").child(username).child("Details").updateChildren(userdataMap)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {


                                                if (task.isSuccessful()) {
                                                    Toast.makeText(RegisterActivity.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();


                                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(intent);
                                                } else {

                                                    Toast.makeText(RegisterActivity.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
                                                }

                                            }
                                        });
                            } else {
                                Toast.makeText(getApplicationContext(), "An account with this username already exists", LENGTH_LONG).show();
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
                if (name.isEmpty()) {
                    InputName.setError("Please enter your Username !");
                    return;
                }


            }

            private void ValidatePhone() {

                String phone = InputPhoneNumber.getText().toString();
                String regez_num = "^\\d{10}$";

                Pattern pattern = Pattern.compile(regez_num);
                Matcher matcher = pattern.matcher(phone);

                if (phone.isEmpty()) {
                    InputPhoneNumber.setError("Please enter your contact number !");
                    return;
                } else if(!(matcher.matches())){
                    InputPhoneNumber.setError("Please enter a valid 10 digit contact number !");
                    return;
                }

            }

            private void ValidateEmail() {

                String mail = EmailID.getText().toString();

                String regex = "^(.+)@(.+)$";

                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(mail);

                if (mail.isEmpty()) {
                    EmailID.setError("Please enter your email ID");
                    return;
                } else if (!(matcher.matches())) {
                    EmailID.setError("Please enter a valid email ID");
                    return;
                }
            }

            private void ValidatePwd() {
                String password = InputPassword.getText().toString();
                String repass = ConfirmPassword.getText().toString();

                if (password.isEmpty()) {
                    InputPassword.setError("Please enter your Password !");
                    return;
                } else if (!(password.equals(repass))) {
                    ConfirmPassword.setError("Passwords do not match !");
                    return;
                }
            }

            private void ValidateUser() {
                String user_type = UserType.getSelectedItem().toString();
                if (!(user_type.contains("Customer") || user_type.contains("Wholesaler") || user_type.contains("Retailer"))) {
                    Toast.makeText(getApplicationContext(), "Please select a valid user", LENGTH_LONG).show();
                    return;
                }
            }


        });


    }

    @SuppressLint("MissingPermission")
    private void getLocation() {

        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 5, RegisterActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

        user_longitude = location.getLongitude();
        user_latitude = location.getLatitude();

        s_long = Double.toString(user_longitude);
        s_lat = Double.toString(user_latitude);


        Longitude_tfield.setText(s_long);
        Latitude_tfield.setText(s_lat);


        try {
            Geocoder geocoder = new Geocoder(RegisterActivity.this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            String address = addresses.get(0).getAddressLine(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }


}


