package com.example.oop_project.Main.Admin;

import android.util.Log;
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
import com.example.oop_project.Main.Customer.CustomerSubCategories;
import com.example.oop_project.Main.Customer.model_category;
import com.example.oop_project.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseError;

public class adminadapter extends FirebaseRecyclerAdapter<model_category, adminadapter.myviewholder> {

    public adminadapter(@NonNull FirebaseRecyclerOptions<model_category> options) {
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
        holder.Quant.setVisibility(View.INVISIBLE);
        holder.Quanttext.setVisibility(View.INVISIBLE);
        Log.i("practi","diff"+(model.getPname()));

//        if(model.getPname().equals("Fruits")) {
//            holder.itemView.setVisibility(View.VISIBLE);
//            }
//            else{
//            holder.itemView.setVisibility(View.GONE);
//        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                CustomerSubCategories(model.getPname())
                Log.i("pract","diff"+(model.getPname()));
                AppCompatActivity activity=(AppCompatActivity)view.getContext();

                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,new Adminsub(model.getPname())).addToBackStack(null).commit();


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
        TextView cutdisttext,Quant;
        EditText cutDist,Quanttext;

        public myviewholder(@NonNull View itemView) {
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
