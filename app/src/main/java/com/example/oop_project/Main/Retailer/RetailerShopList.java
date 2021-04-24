package com.example.oop_project.Main.Retailer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oop_project.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class RetailerShopList extends Fragment{
    double cutDist=1000;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    public double cutoffDistance;
    String catname,pname;
    RecyclerView recview1;
    RetailerShopadapter adapter;
    Button filter;
    double quantity;
    EditText cutoffDist;
    public RetailerShopList() {

    }

    public RetailerShopList(String catname, String pname, double cutDistance,double qty) {
        this.cutoffDistance = cutDistance;
        this.catname = catname;
        this.pname = pname;
        this.quantity = qty;
    }

    public static RetailerShopList newInstance(String param1, String param2) {
        RetailerShopList fragment = new RetailerShopList();
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
            mParam1 = getArguments().getString(ARG_PARAM1).trim();
            mParam2 = getArguments().getString(ARG_PARAM2).trim();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_shop_filter, container, false);
        recview1 = (RecyclerView) view.findViewById(R.id.rcycler);
        recview1.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.i("Mine dis", "pname:" + pname);
        Log.i("Mine dis", "catname:" + catname);

        DatabaseReference rootref = FirebaseDatabase.getInstance().getReference();
        Query query = rootref.child("Quantity").child(catname).child(pname).child("Wholesaler");
        //pname = Fruits

        FirebaseRecyclerOptions<RetailerShopModel> options =
                new FirebaseRecyclerOptions.Builder<RetailerShopModel>()
                        .setQuery(query, RetailerShopModel.class)
                        .build();

        adapter = new RetailerShopadapter(options,getContext(),  catname , pname,cutoffDistance,(int) quantity);
        recview1.setAdapter(adapter);


        return view;
    }

    public void onBackPressed() {
        AppCompatActivity activity = (AppCompatActivity) getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, new RetailerSubCategories()).addToBackStack(null).commit();

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