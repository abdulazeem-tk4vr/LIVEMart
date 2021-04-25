package com.example.oop_project.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.oop_project.Main.Admin.Admin;
import com.example.oop_project.Main.Admin.Adminsub;
import com.example.oop_project.Main.Customer.CustomerSubCategories;
import com.example.oop_project.R;

public class AdminActivity extends AppCompatActivity {
    Button admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        admin = findViewById(R.id.adminButton);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppCompatActivity activity=(AppCompatActivity)v.getContext();

                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,new Admin()).addToBackStack(null).commit();
            }
        });
    }
}