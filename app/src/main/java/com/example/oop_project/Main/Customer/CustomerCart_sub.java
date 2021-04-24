package com.example.oop_project.Main.Customer;

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
import com.example.oop_project.Main.Retailer.Orders_sub;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerCart_sub extends Fragment {

    String pname;

    RecyclerView recview;
    CartAdapter adapter;

    SharedPreferences sh = getActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

                    // The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
                    String p_username = sh.getString("username", "Macha");
                    String p_usertype = sh.getString("usertype", "Customer");
    public CustomerCart_sub() {

    }

    public CustomerCart_sub(String pname) {
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
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Cart").child("Customer").child(p_username).child(pname), Orders_sub.class)
                        .build();
        adapter = new CartAdapter(options);
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