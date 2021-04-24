package com.example.oop_project.Main.Customer;

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
import com.example.oop_project.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseError;

public class adapter_sub extends FirebaseRecyclerAdapter<model_subcategory, adapter_sub.myviewholder> {
    String catname;
    public adapter_sub(@NonNull FirebaseRecyclerOptions<model_subcategory> options,String category) {
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
        holder.cutdisttext.setVisibility(View.VISIBLE);
        holder.cutDist.setVisibility(View.VISIBLE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double cutDist=1000;
                int qty = 1;
                if(holder.cutDist.getText().length() != 0 ){
                    cutDist = Double.parseDouble(String.valueOf(holder.cutDist.getText()));

                }
                if(holder.qtyText.getText().length() !=0){
                qty = (int) Double.parseDouble(String.valueOf(holder.qtyText.getText()));}
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,new CustomerShopList(catname,model.getPname(),cutDist,qty)).addToBackStack(null).commit();
                Log.i("mine sub",catname +" hello "+model.getPname());
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
        EditText cutDist,qtyText;
        Button bt;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            cutdisttext = itemView.findViewById(R.id.cutDisttext);
            cutDist = itemView.findViewById(R.id.cutDist);
            img1 = itemView.findViewById(R.id.img1);
            nametext = itemView.findViewById(R.id.nametext);
            qtyText = itemView.findViewById(R.id.qtyText);
        }
    }

}
