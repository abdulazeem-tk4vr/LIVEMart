package com.example.oop_project.Main;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.oop_project.R;


public class Wholesaler_Add extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wholesaler__add, container, false);

        Button addprodnoimage,addprodimage;
        addprodimage = view.findViewById(R.id.addprod);
        addprodnoimage = view.findViewById(R.id.addprodnoimage);

        addprodimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), com.example.oop_project.Main.addproduct.class);
                startActivity(intent);
            }
        });

        addprodnoimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), com.example.oop_project.Main.addprodnoimage.class);
                startActivity(intent);
            }
        });

        return view;
    }
}