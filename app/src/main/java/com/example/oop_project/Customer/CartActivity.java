package com.example.oop_project.Customer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oop_project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends Fragment {

   FirebaseDatabase db =FirebaseDatabase.getInstance();
    ArrayList  Keys = new ArrayList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_cart_activity, container, false);
        db.getReference().child("Cart").child("Customer").child("Macha").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Keys.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Keys.add(dataSnapshot.getKey());
                }
                String nkey = new String();
                nkey = String.valueOf(Keys);
                Log.i("memesda",nkey);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;


    }
}