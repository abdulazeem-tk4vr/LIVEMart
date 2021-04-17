package com.example.oop_project.Retailer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.oop_project.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Adapter_MyItems extends FirebaseRecyclerAdapter<model_items_cat, Adapter_MyItems.itemviewholder> {


    public Adapter_MyItems(@NonNull FirebaseRecyclerOptions<model_items_cat> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull itemviewholder holder, int position, @NonNull model_items_cat model) {
        holder.nametext.setText(model.getPname());
        Glide.with(holder.img1.getContext()).load(model.getImage()).into(holder.img1);
        holder.cutdisttext.setVisibility(View.INVISIBLE);
        holder.cutDist.setVisibility(View.INVISIBLE);
        holder.Quant.setVisibility(View.INVISIBLE);
        holder.Quanttext.setVisibility(View.INVISIBLE);
    }

    @NonNull
    @Override
    public itemviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign, parent, false);
        return new itemviewholder(view);
    }

    public class itemviewholder extends RecyclerView.ViewHolder
    {
        ImageView img1;
        TextView nametext;
        TextView cutdisttext,Quant;
        EditText cutDist,Quanttext;

        public itemviewholder(@NonNull View itemView) {
            super(itemView);
            Quanttext = itemView.findViewById(R.id.qtyText);
            Quant = itemView.findViewById(R.id.textView5);
            cutdisttext = itemView.findViewById(R.id.cutDisttext);
            cutDist = itemView.findViewById(R.id.cutDist);
            img1 = itemView.findViewById(R.id.img1);
            nametext = itemView.findViewById(R.id.nametext);
        }
    }
}
