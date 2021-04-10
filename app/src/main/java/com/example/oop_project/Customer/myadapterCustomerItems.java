package com.example.oop_project.Customer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.oop_project.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapterCustomerItems extends FirebaseRecyclerAdapter<modelcitems_fruits,myadapterCustomerItems.itemviewholder> {


    public myadapterCustomerItems(@NonNull FirebaseRecyclerOptions<modelcitems_fruits> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull itemviewholder holder, int position, @NonNull modelcitems_fruits model) {
        holder.nametext.setText(model.getPname());
        Glide.with(holder.img1.getContext()).load(model.getImage()).into(holder.img1);

    }

    @NonNull
    @Override
    public itemviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign,parent,false);
        return new itemviewholder(view);
    }

    public class itemviewholder extends RecyclerView.ViewHolder{

        ImageView img1;
        TextView nametext;


        public itemviewholder(@NonNull View itemView) {
            super(itemView);

            img1 = itemView.findViewById(R.id.img1);
            nametext = itemView.findViewById(R.id.nametext);


        }
    }
}
