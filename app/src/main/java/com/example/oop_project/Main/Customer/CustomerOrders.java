package com.example.oop_project.Main.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.oop_project.Main.AlarmActivity;
import com.example.oop_project.R;

import java.util.ArrayList;

public class CustomerOrders extends Fragment implements AdapterView.OnItemClickListener{
    ArrayList<String> Keys = new ArrayList<>();
    String pname;

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
        Button notifbtn = (Button) view.findViewById(R.id.notifbtn);
        notifbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), AlarmActivity.class);
                getContext().startActivity(i);
            }
        });
        return view;
    }
    public CustomerOrders(ArrayList<String> Keys, String pname) {
        this.Keys=Keys;
        this.pname=pname;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String selectedFromList = (String) (parent.getItemAtPosition(position));
        AppCompatActivity activity=(AppCompatActivity)view.getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,new CustomerOrders_sub(selectedFromList,pname)).addToBackStack(null).commit();
    }
}
