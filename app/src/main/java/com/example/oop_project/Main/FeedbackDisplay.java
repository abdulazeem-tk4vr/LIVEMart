package com.example.oop_project.Main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oop_project.Main.Customer.myadapterCustomerCategory;
import com.example.oop_project.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FeedbackDisplay extends Fragment {

    RecyclerView recview;
    myadapterCustomerCategory adapter;
    TextView tv,tv2;
    EditText et;
    Button bt;
    String fb;

    public FeedbackDisplay(String fb) {
        this.fb=fb;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.feedback_display, container, false);

        tv = view.findViewById(R.id.userguy);
        tv.setText(fb);
        return view;
    }

}
