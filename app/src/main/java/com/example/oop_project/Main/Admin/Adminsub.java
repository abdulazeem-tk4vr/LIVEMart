package com.example.oop_project.Main.Admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oop_project.Main.Customer.adapter_sub;
import com.example.oop_project.Main.Customer.model_subcategory;
import com.example.oop_project.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class Adminsub extends Fragment {

    String pname;
    RecyclerView recview;
    admin_subadapter adapter;

    public Adminsub() {

    }

    public Adminsub(String pname) {
        this.pname=pname;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.categories2, container, false);

        recview = (RecyclerView) view.findViewById(R.id.recview2);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));

        Log.i("pract","this is" +pname);

        FirebaseRecyclerOptions<model_subcategory> options =
                new FirebaseRecyclerOptions.Builder<model_subcategory>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Products").child(pname),model_subcategory.class)
                        .build();
        adapter = new admin_subadapter(options,pname);
        recview.setAdapter(adapter);


        return  view;
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}