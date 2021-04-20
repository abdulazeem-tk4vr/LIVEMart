package com.example.oop_project.MyItems;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_sub, container, false);

        recview = (RecyclerView) view.findViewById(R.id.recview_itemssub);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));

//        FirebaseRecyclerOptions<model_items_cat> options =
//                new FirebaseRecyclerOptions.Builder<model_items_cat>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Products").child("Categories").child(), model_itemsub.class)
//                        .build();
//
//        adapter = new adapter_itemsub(options);
//        recview.setAdapter(adapter);


        return  view;
    }
}