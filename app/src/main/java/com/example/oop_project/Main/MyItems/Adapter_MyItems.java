package com.example.oop_project.Main.MyItems;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity=(AppCompatActivity)v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,new item_sub(model.pname)).addToBackStack(null).commit();
            }
        });

    }

    @NonNull
    @Override
    public itemviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemdesign, parent, false);
        return new itemviewholder(view);
    }

    public class itemviewholder extends RecyclerView.ViewHolder
    {
        ImageView img1;
        TextView nametext;

        public itemviewholder(@NonNull View itemView) {
            super(itemView);

            img1 = itemView.findViewById(R.id.img1);
            nametext = itemView.findViewById(R.id.nametext);
        }
    }
}
