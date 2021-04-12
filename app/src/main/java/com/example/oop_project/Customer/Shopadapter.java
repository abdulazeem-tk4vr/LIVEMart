package com.example.oop_project.Customer;

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

public class Shopadapter extends FirebaseRecyclerAdapter<model_shop, Shopadapter.myviewholder> {
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
    double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist/1000);

    }
    public Shopadapter(@NonNull FirebaseRecyclerOptions<model_shop> options) {
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
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull final model_shop model) {
        double lat1,lon1,lat2=0,lon2 = 0;//assigned as 0 till abdul finished sharedPref
        lat1 =  model.getLatitude();
        lon1 = model.getLongitude();
        double dist = distance(lat1,lon1,lat2,lon2);
        holder.rate.setText(model.getPrice());
        holder.qty.setText( model.getQuantity());
        holder.shopname.setText(model.getRname());
        holder.dist.setText(""+dist);
        holder.tc.setText("" + model.getPrice());



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
            shopname = itemView.findViewById(R.id.shopnameTV);
            qty = itemView.findViewById(R.id.qtyTV);
            rate = itemView.findViewById(R.id.rateTV);
            tc = itemView.findViewById(R.id.totalcostTV);
            dist = itemView.findViewById(R.id.distTV);

        }
    }
}
