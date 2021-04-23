package com.example.oop_project.Main.Retailer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oop_project.Main.MyItems.model_items_cat;
import com.example.oop_project.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class RetailerCategories extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    RecyclerView recview;
    CategoryAdapter adapter;
    public String p_username, p_usertype;
    public RetailerCategories() {
//        SharedPreferences sh = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
//        p_username  = sh.getString("username", "Fgretailer");
//        p_usertype  = sh.getString("usertype", "Retailer");
    }

    public static RetailerCategories newInstance(String param1, String param2) {
        RetailerCategories fragment = new RetailerCategories();
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

        View view = inflater.inflate(R.layout.categories, container, false);

        recview = (RecyclerView) view.findViewById(R.id.recview);
        Context context = getContext();
        recview.setLayoutManager(new LinearLayoutManager(context));
        FirebaseRecyclerOptions<model_items_cat> options =
                new FirebaseRecyclerOptions.Builder<model_items_cat>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Products").child("Categories"), model_items_cat.class)
                        .build();

        adapter = new CategoryAdapter(options);
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
