package com.example.oop_project.Customer;

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


public class CitemsFruits extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    RecyclerView cat_items;
    myadapterCustomerItems adapter;

    public CitemsFruits() {

    }


    public static CitemsFruits newInstance(String param1, String param2) {
        CitemsFruits fragment = new CitemsFruits();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =  inflater.inflate(R.layout.fragment_customer_items_fruits, container, false);

       cat_items = view.findViewById(R.id.cat_items);
       cat_items.setLayoutManager( new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<modelcitems_fruits> options =
                new FirebaseRecyclerOptions.Builder<modelcitems_fruits>()
                    .setQuery(FirebaseDatabase.getInstance().getReference().child("Products").child("Fruits"),modelcitems_fruits.class)
                .build();

        adapter = new myadapterCustomerItems(options);
        cat_items.setAdapter(adapter);

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