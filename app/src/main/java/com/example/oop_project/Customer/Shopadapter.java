package com.example.oop_project.Customer;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oop_project.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseError;


public class Shopadapter extends FirebaseRecyclerAdapter<model_shop, Shopadapter.myviewholder>  {






    public String p_username,p_usertype;



    public Shopadapter(@NonNull FirebaseRecyclerOptions<model_shop> options) {
        super(options);
//        SharedPreferences sh = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
//
//        // The value will be default as empty string because for
//// the very first time when the app is opened, there is nothing to show
//        p_username  = sh.getString("username", "mama mia");
//        p_usertype  = sh.getString("usertype", "loco");
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
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull final model_shop model) {




        //    Log.i("memes",p_username);





        //Glide.with(holder.img1.getContext()).load().into(holder.img1);
        //add text and image based on layout ani designs
//        holder.img1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AppCompatActivity activity=(AppCompatActivity)view.getContext();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,new CustomerShopList()).addToBackStack(null).commit();
//            }
//        });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_row, parent, false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        //modify this based on what ani designs
        TextView shopname,qty,rate,tc,dist,lon;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            this.itemView.getContext();


            shopname = itemView.findViewById(R.id.shopnameTV);
            qty = itemView.findViewById(R.id.qtyTV);
            rate = itemView.findViewById(R.id.rateTV);
            tc = itemView.findViewById(R.id.totalcostTV);
            dist = itemView.findViewById(R.id.distTV);

        }
    }
}
