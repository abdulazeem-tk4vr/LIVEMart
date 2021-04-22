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
    String prod_type;

    public item_sub(){

    }

    public item_sub(String prod_type){
    this.prod_type=prod_type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_sub, container, false);

        recview = (RecyclerView) view.findViewById(R.id.recview_itemssub);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));




        return  view;
    }
}