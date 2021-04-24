package com.example.oop_project.Main.Retailer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oop_project.Main.FragmentContent;
import com.example.oop_project.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class RetailerOrders_sub extends Fragment {

    String pname;

    RecyclerView recview;
    OrderAdapter adapter;
    public RetailerOrders_sub() {

    }

    public RetailerOrders_sub(String pname) {
        this.pname=pname;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.categories2, container, false);

        recview = (RecyclerView) view.findViewById(R.id.recview2);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Orders_sub> options =
                new FirebaseRecyclerOptions.Builder<Orders_sub>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Cart").child("Retailer").child("Fgretailer").child(pname).orderByChild("status").equalTo("Approved"), Orders_sub.class)
                        .build();
        adapter = new OrderAdapter(options,getContext());
        recview.setAdapter(adapter);


        return  view;
    }

    public void onBackPressed()
    {
        AppCompatActivity activity=(AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,new FragmentContent()).addToBackStack(null).commit();

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