package com.example.oop_project.Retailer;

import android.content.Context;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oop_project.Customer.CustomerSubCategories;
import com.example.oop_project.Customer.adapter_sub;
import com.example.oop_project.Customer.model_category;
import com.example.oop_project.Customer.myadapterCustomerCategory;
import com.example.oop_project.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class RetailerTrans extends Fragment implements AdapterView.OnItemClickListener{
    ArrayList<String> Keys = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listview = view.findViewById(R.id.listView);

        ArrayAdapter<String> adapter= new ArrayAdapter<>(getActivity(),R.layout.row,R.id.maintransview,Keys);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transaction_layout, container, false);
        return view;
    }
    public RetailerTrans(ArrayList<String> Keys) {
        this.Keys=Keys;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String selectedFromList = (String) (parent.getItemAtPosition(position));
        AppCompatActivity activity=(AppCompatActivity)view.getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,new RetailerTrans_sub(selectedFromList)).addToBackStack(null).commit();
    }
}
