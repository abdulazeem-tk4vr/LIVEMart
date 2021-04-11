package com.example.oop_project.Customer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oop_project.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerShopList extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    String pname, catname;
    RecyclerView recview;
    Shopadapter adapter;
    public CustomerShopList() {

    }

    public CustomerShopList(String catname,String pname) {
        this.pname=pname;
        this.catname=catname;
    }

    public static CustomerShopList newInstance(String param1, String param2) {
        CustomerShopList fragment = new CustomerShopList();
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
        View view=inflater.inflate(R.layout.shop_list, container, false);

        recview = (RecyclerView) view.findViewById(R.id.recview_shop);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.e("Mine","pname: "+pname);
        Log.e("Mine","catname: "+catname);
        FirebaseRecyclerOptions<model_shop> options =
                new FirebaseRecyclerOptions.Builder<model_shop>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Quantity").child(catname).child(pname).child("Retailer"), model_shop.class)
                        .build();
        adapter = new Shopadapter(options);
        recview.setAdapter(adapter);
        return view;
    }

    public void onBackPressed()
    {
        AppCompatActivity activity=(AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,new CustomerSubCategories()).addToBackStack(null).commit();

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