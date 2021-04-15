package com.example.oop_project.Retailer;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.oop_project.Customer.CustomerSubCategories;
import com.example.oop_project.Retailer.model_category;
import com.example.oop_project.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseError;

public class CategoryAdapter extends FirebaseRecyclerAdapter<model_category, CategoryAdapter.myviewholder> {

    public CategoryAdapter(@NonNull FirebaseRecyclerOptions<model_category> options) {
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
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull final model_category model) {


        holder.nametext.setText(model.getPname());
        Glide.with(holder.img1.getContext()).load(model.getImage()).into(holder.img1);
        holder.cutdisttext.setVisibility(View.INVISIBLE);
        holder.cutDist.setVisibility(View.INVISIBLE);
//        if(model.getPname().equals("Fruits")) {
//            holder.itemView.setVisibility(View.VISIBLE);
//            }
//            else{
//            holder.itemView.setVisibility(View.GONE);
//        }


        holder.img1.setOnClickListener(new View.OnClickListener() {
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
        TextView nametext;
        TextView cutdisttext;
        EditText cutDist;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            cutdisttext = itemView.findViewById(R.id.cutDisttext);
            cutDist = itemView.findViewById(R.id.cutDist);
            img1 = itemView.findViewById(R.id.img1);
            nametext = itemView.findViewById(R.id.nametext);

        }
    }

}