package com.example.oop_project.Main.MyItems;

import android.content.Context;
import android.content.SharedPreferences;
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


public class item_sub extends Fragment {
    RecyclerView recview;
    adapter_itemsub adapter;
    String prod_type;



    public item_sub(){

    }

    public item_sub(String prod_type){
    this.prod_type=prod_type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        SharedPreferences sh = getActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

        // The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
        String p_username = sh.getString("username", "Retailer");
        String p_usertype = sh.getString("usertype", "Fgretailer");

        View view = inflater.inflate(R.layout.fragment_item_sub, container, false);

        recview = (RecyclerView) view.findViewById(R.id.recview_itemssub);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));


        FirebaseRecyclerOptions<model_itemsub> options =
                new FirebaseRecyclerOptions.Builder<model_itemsub>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("User").child(p_usertype).child(p_username).child("Products").child(prod_type), model_itemsub.class)
                        .build();


            adapter = new adapter_itemsub(options,getContext());
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