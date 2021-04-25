package com.example.oop_project.Main.Retailer;

import android.content.Context;
import android.content.SharedPreferences;
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

public class RetailerTrans_sub extends Fragment {

    String pname,p_username,p_usertype;

    RecyclerView recview;
    TransactionAdapter adapter;
    boolean t;
    public RetailerTrans_sub() {

    }

    public RetailerTrans_sub(String pname,boolean t) {
        this.pname=pname;
        this.t=t;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        SharedPreferences sh = getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        p_username  = sh.getString("username", "Fgretailer");
        p_usertype  = sh.getString("usertype", "Retailer");

        View view=inflater.inflate(R.layout.categories2, container, false);

        recview = (RecyclerView) view.findViewById(R.id.recview2);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<TransModel_sub> options =
                new FirebaseRecyclerOptions.Builder<TransModel_sub>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Transaction").child("Retailer").child(p_username).child(pname), TransModel_sub.class)
                        .build();
        adapter = new TransactionAdapter(options,t);
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