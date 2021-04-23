package com.example.oop_project.Main.MyItems;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oop_project.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class MyItems extends Fragment {



    RecyclerView recview;
    Adapter_MyItems adapter;


    public MyItems() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_my_items, container, false);

        recview = (RecyclerView) view.findViewById(R.id.recview_items);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));


        FirebaseRecyclerOptions<model_items_cat> options =
                new FirebaseRecyclerOptions.Builder<model_items_cat>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Products").child("Categories"), model_items_cat.class)
                        .build();

        adapter = new Adapter_MyItems(options);
        recview.setAdapter(adapter);


        return view;



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