package com.example.oop_project.Main.MyItems;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oop_project.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class adapter_itemsub extends FirebaseRecyclerAdapter<model_itemsub,adapter_itemsub.myitemsubviewholder>{


    public adapter_itemsub(@NonNull FirebaseRecyclerOptions<model_itemsub> options, Context context) {

        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myitemsubviewholder holder, int position, @NonNull model_itemsub model) {
        holder.product_name.setText(model.getPname());
        holder.price.setText(model.getPrice());
        holder.quantity.setText(model.getQuantity());


    }

    @NonNull
    @Override
    public myitemsubviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myitems_card, parent, false);
        return new myitemsubviewholder(view);    }

    public class myitemsubviewholder extends RecyclerView.ViewHolder{
            TextView product_name,price,quantity;
            public myitemsubviewholder(@NonNull View itemView) {
                super(itemView);

                product_name = itemView.findViewById(R.id.textView4);
                price = itemView.findViewById(R.id.pricelabel);
                quantity = itemView.findViewById(R.id.quanitylabel);
            }
        }





}



//
//
//
