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
import com.google.firebase.database.FirebaseDatabase;


public class Shopadapter extends FirebaseRecyclerAdapter<model_shop, Shopadapter.myviewholder>  {






    public String arg_cat,arg_pname,p_username,p_usertype;
    // category , product name


    public Shopadapter(@NonNull FirebaseRecyclerOptions<model_shop> options, Context context, String catname , String pname) {
        super(options);
        SharedPreferences sh = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

        // The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
        p_username  = sh.getString("username", "Macha");
        p_usertype  = sh.getString("usertype", "Customer");
       arg_cat=catname;
       arg_pname=pname;


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




            Log.i("memes",arg_pname);
            Log.i("memes",p_username);
            Log.i("memes",p_usertype);


            // to retrieve the coordinates from the user
            // retrieve coordinates from the shop
            // calculate distance
//        private double distance(double lat1, double lon1, double lat2, double lon2) {
//            double theta = lon1 - lon2;
//            double dist = Math.sin(deg2rad(lat1))
//                    * Math.sin(deg2rad(lat2))
//                    + Math.cos(deg2rad(lat1))
//                    * Math.cos(deg2rad(lat2))
//                    * Math.cos(deg2rad(theta));
//            dist = Math.acos(dist);
//            dist = rad2deg(dist);
//            dist = dist * 60 * 1.1515;
//            return (dist/1000);
//
//        }


            //display details


        FirebaseDatabase.getInstance().getReference().child("Quantity").child(arg_cat).child(arg_pname);







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
        TextView shopname,qty,rate,tc,dist;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            this.itemView.getContext();
            int a =1;


            shopname = itemView.findViewById(R.id.shopnameTV);
            qty = itemView.findViewById(R.id.qtyTV);
            rate = itemView.findViewById(R.id.rateTV);
            tc = itemView.findViewById(R.id.totalcostTV);
            dist = itemView.findViewById(R.id.distTV);

        }
    }
}
