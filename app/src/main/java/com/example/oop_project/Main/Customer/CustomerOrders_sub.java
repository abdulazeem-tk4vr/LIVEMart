package com.example.oop_project.Main.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oop_project.Main.AlarmActivity;
import com.example.oop_project.Main.FragmentContent;
import com.example.oop_project.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerOrders_sub extends Fragment {

    String pname,custname;
    RecyclerView recview;
    OrderAdapter adapter;
    public CustomerOrders_sub() {

    }

    public CustomerOrders_sub(String pname,String custname) {
        this.pname=pname;
        this.custname =custname;

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

        FirebaseRecyclerOptions<TransModel_sub> options =
                new FirebaseRecyclerOptions.Builder<TransModel_sub>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Cart").child("Customer").child(custname).child(pname), TransModel_sub.class)
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