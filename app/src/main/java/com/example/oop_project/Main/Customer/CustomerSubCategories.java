package com.example.oop_project.Main.Customer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oop_project.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class    CustomerSubCategories extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    String pname;
    RecyclerView recview;
    adapter_sub adapter;
    public CustomerSubCategories() {

    }

    public CustomerSubCategories(String pname) {
        this.pname=pname;

    }

    public static CustomerSubCategories newInstance(String param1, String param2) {
        CustomerSubCategories fragment = new CustomerSubCategories();
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

        View view=inflater.inflate(R.layout.categories2, container, false);

        recview = (RecyclerView) view.findViewById(R.id.recview2);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<model_subcategory> options =
                new FirebaseRecyclerOptions.Builder<model_subcategory>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Products").child(pname).orderByChild("status").equalTo("Approved"), model_subcategory.class)
                        .build();
        adapter = new adapter_sub(options,pname);
        recview.setAdapter(adapter);


        return  view;
    }

    public void onBackPressed()
    {
        AppCompatActivity activity=(AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,new CustomerCategories()).addToBackStack(null).commit();

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