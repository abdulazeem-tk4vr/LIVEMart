package com.example.oop_project.Main.Retailer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.oop_project.Main.MyItems.model_items_cat;
import com.example.oop_project.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseError;

public class CategoryAdapter extends FirebaseRecyclerAdapter<model_items_cat, CategoryAdapter.myviewholder> {

    public CategoryAdapter(@NonNull FirebaseRecyclerOptions<model_items_cat> options) {
        super(options);

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
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull final model_items_cat model) {


        holder.nametext.setText(model.getPname());
        Glide.with(holder.img1.getContext()).load(model.getImage()).into(holder.img1);
        holder.cutdisttext.setVisibility(View.INVISIBLE);
        holder.cutDist.setVisibility(View.INVISIBLE);
        holder.qty.setVisibility(View.INVISIBLE);
        holder.quantlabel.setVisibility(View.INVISIBLE);

//        if(model.getPname().equals("Fruits")) {
//            holder.itemView.setVisibility(View.VISIBLE);
//            }
//            else{
//            holder.itemView.setVisibility(View.GONE);
//        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,new RetailerSubCategories(model.getPname())).addToBackStack(null).commit();

            }
        });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign, parent, false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        ImageView img1;
        TextView nametext, qty,quantlabel;
        TextView cutdisttext;
        EditText cutDist;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            cutdisttext = itemView.findViewById(R.id.cutDisttext);
            cutDist = itemView.findViewById(R.id.cutDist);
            img1 = itemView.findViewById(R.id.img1);
            nametext = itemView.findViewById(R.id.nametext);
            qty = itemView.findViewById(R.id.qtyText);
            quantlabel = itemView.findViewById(R.id.textView5);


        }
    }

}
