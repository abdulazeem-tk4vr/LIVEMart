package com.example.oop_project.Main.Admin;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.oop_project.Main.Customer.CustomerShopList;
import com.example.oop_project.Main.Customer.model_subcategory;
import com.example.oop_project.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class admin_subadapter extends FirebaseRecyclerAdapter<model_subcategory, admin_subadapter.myviewholder> {
    String catname;

    public admin_subadapter(@NonNull FirebaseRecyclerOptions<model_subcategory> options, String category) {
        super(options);
        catname=category;
    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();
    }

    @Override
    public void onError(@NonNull DatabaseError error) {
        super.onError(error);
        error.toException().printStackTrace();
    }


    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull final model_subcategory model) {
        holder.nametext.setText(model.getPname());
        Glide.with(holder.img1.getContext()).load(model.getImage()).into(holder.img1);
        holder.status.setText(model.getStatus());

        DatabaseReference rootref = FirebaseDatabase.getInstance().getReference().child("Products").child("Fruits").child(model.getPname());

       holder.approve.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               HashMap<String, Object> productMap = new HashMap<>();
               productMap.put("status", "Approved");
               rootref.updateChildren(productMap);
           }
       });


        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> productMap = new HashMap<>();
                productMap.put("status", "Not Approved");
                rootref.updateChildren(productMap);
            }
        });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adminrowdesign, parent, false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        ImageView img1;
        TextView nametext;
        Button approve,reject;
        TextView status;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            approve = itemView.findViewById(R.id.approve);
            reject = itemView.findViewById(R.id.reject);
            img1 = itemView.findViewById(R.id.img1);
            nametext = itemView.findViewById(R.id.nametext);
            status = itemView.findViewById(R.id.status);

        }
    }

}
