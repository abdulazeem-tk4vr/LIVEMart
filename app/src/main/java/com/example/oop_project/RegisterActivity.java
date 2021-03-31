package com.example.oop_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

                String username = InputName.getText().toString();
                String number = InputPhoneNumber.getText().toString();
                String pwd = InputPassword.getText().toString();
                String repwd = ConfirmPassword.getText().toString();
                String user = UserType.getSelectedItem().toString();




                ValidateName();
                ValidatePhone();
                ValidateEmail();
                ValidatePwd();
                ValidateUser();


                if(user.contains("Customer")){

                }

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




