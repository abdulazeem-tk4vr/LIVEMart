package com.example.oop_project.Main.Customer;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oop_project.Main.FeedbackDisplay;
import com.example.oop_project.Main.FeedbackFrag;
import com.example.oop_project.Main.Retailer.RetailerTrans;
import com.example.oop_project.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.BuildConfig;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;


public class Shopadapter extends FirebaseRecyclerAdapter<model_shop, Shopadapter.myviewholder>  {
    public String arg_cat,arg_pname,p_username,p_usertype, dist_temp,parent_usertype;
    public double cutoffDist;
    public int quantityDemand;
    public String uid;
    public String source;
    private FragmentManager mFragmentManager;
    public String fbtext="NO";
    Context context;
    // category , product name


    public Shopadapter(@NonNull FirebaseRecyclerOptions<model_shop> options, Context context, String catname , String pname,double cutDist,int quantity) {
        super(options);
        SharedPreferences sh = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

        // The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
        p_username  = sh.getString("username", "Macha");
        p_usertype  = sh.getString("usertype", "Customer");
        arg_cat=catname;
        arg_pname=pname;
        cutoffDist = cutDist;
        quantityDemand = quantity;
        this.context=context;

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
    private double distance(double lat1, double lon1, double lat2, double lon2) {
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
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull final model_shop model) {
        Log.i("memes",arg_pname);
        Log.i("memes",p_username);
        Log.i("memes",p_usertype);
        Log.i("miccheck",fbtext);
        holder.shopname.setText(model.getRname());
        holder.qty.setText(model.getQuantity());
        holder.rate.setText(model.getPrice());
        if(quantityDemand != 0) {
            holder.tc.setText(Float.toString(Float.parseFloat(model.getPrice()) *quantityDemand));
        }
        else {
            holder.tc.setError("Please enter quantity!");
        }

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbref = database.getReference().child("User");
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        holder.b2.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                if(p_usertype.equals("Customer")){
                    source = "Retailer";
                }else if(p_usertype.equals("Retailer")){
                    source = "Wholesaler";
                }
                db.child("Feedback").child(source).child(model.getRname()).child(arg_pname).child(p_username).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            String retname=model.getRname();

                            fbtext = "By "+p_username+": \n"+snapshot.child("feedback").getValue().toString();

                            //db.child("Cart").child("Customer").child("Macha").child(uid).child(arg_pname).setValue(map);

                        }else{
                            fbtext = "NO FEEDBACK FOR THIS ITEM YET";
                        }
                        AppCompatActivity activity=(AppCompatActivity)view.getContext();
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,new FeedbackDisplay(fbtext)).addToBackStack(null).commit();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

        holder.b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int q = quantityDemand;
                if(p_usertype.equals("Customer")) {
                    dbref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if(snapshot.exists()) {
                                String shop_name = (String) holder.shopname.getText();
                                uid = snapshot.child("Customer").child(p_username).child("Details").child("UID").getValue().toString();
                                Log.i("dei",uid.toString());
                                Map<String, Object> map = new HashMap<>();
                                map.put("pname", arg_pname);
                                map.put("cost", holder.tc.getText());
                                map.put("quantity", String.valueOf(quantityDemand));
                                map.put("shop", holder.shopname.getText());
                                map.put("dname", "daboi");
                                map.put("ddate", "1/6/21");
                                Log.i("Brux","kdnsk");
                                map.put("dnumber", "67564");
                                map.put("status", "Approved");
                                DatabaseReference db = FirebaseDatabase.getInstance().getReference();
                                db.child("Cart").child("Customer").child(p_username).child(uid).child(arg_pname).updateChildren(map);



                                Map<String, Object> mapa = new HashMap<>();
                                mapa.put("pname", arg_pname);
                                mapa.put("cost", holder.tc.getText());
                                mapa.put("quantity", String.valueOf(quantityDemand));
                                mapa.put("username", p_username);
                                mapa.put("dname", "daboi");
                                mapa.put("ddate", "1/6/21");
                                Log.i("Brux","kdnsk");
                                mapa.put("dnumber", "67564");
                                mapa.put("status", "Approved");
                                DatabaseReference dba = FirebaseDatabase.getInstance().getReference();

                                dba.child("Transaction").child("Retailer").child(shop_name).child(uid).child(arg_pname).updateChildren(mapa);

                                Toast.makeText(context, arg_pname+" was added to "+uid, Toast.LENGTH_SHORT).show();





                                //db.child("Cart").child("Customer").child("Macha").child(uid).child(arg_pname).setValue(map);

                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }

            }
        });
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String str1 = snapshot.child(p_usertype).child(p_username).child("Details").child("latitude").getValue().toString();
                    Log.e("memes", str1);
                    String str2= snapshot.child(p_usertype).child(p_username).child("Details").child("longitude").getValue().toString();
                    Log.e("memes", str2);
                    Log.i("status","random");
                    if(p_usertype.equals("Customer")){
                        parent_usertype="Retailer";
                    }else if(p_usertype.equals("Retailer")){
                        parent_usertype="Wholesaler";
                    }

                    String str3 = snapshot.child(parent_usertype).child(model.getRname()).child("Details").child("latitude").getValue().toString();
                    String str4 = snapshot.child(parent_usertype).child(model.getRname()).child("Details").child("longitude").getValue().toString();
                    double lat1,lat2,lon1,lon2;
                    lat1 = Double.parseDouble(str1);
                    lat2 = Double.parseDouble(str3);
                    lon1 = Double.parseDouble(str2);
                    lon2 = Double.parseDouble(str4);
                    String str = new DecimalFormat("#.00#").format(distance(lat1,lon1,lat2,lon2));
                    holder.dist.setText(str);
                    double activeDist = Double.parseDouble(str);
                    int qtyPresent = (int) Double.parseDouble(String.valueOf(holder.qty.getText()));
                    if(activeDist>cutoffDist){
                        holder.itemView.setVisibility(View.INVISIBLE);
                        Log.i("Dist","DS1 does not exist");
                    }
                    else {
                        holder.itemView.setVisibility(View.VISIBLE);
                    }
                    if(quantityDemand>qtyPresent){
                        holder.dist.setVisibility(View.INVISIBLE);
                        holder.rate.setVisibility(View.INVISIBLE);
                        holder.r.setVisibility(View.INVISIBLE);
                        holder.b1.setVisibility(View.INVISIBLE);
                        holder.b2.setVisibility(View.INVISIBLE);
                        holder.tc.setVisibility(View.INVISIBLE);

                        holder.t16.setVisibility(View.INVISIBLE);
                        holder.t21.setVisibility(View.INVISIBLE);
                        holder.sub.setVisibility(View.INVISIBLE);

                        holder.qty.setText("LOW on STOCK, return on 20th May!");
                    }
                }else{
                    Log.i("memes","DS1 does not exist");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



////        lat2 = Double.parseDouble(String.valueOf(database.getReference().child("User").child("Retailer").child(model.getRname()).child("Details").child("latitude").get()));
////        lon2 = Double.parseDouble(String.valueOf(database.getReference().child("User").child("Retailer").child(model.getRname()).child("Details").child("longitude").get()));
////        Log.e("distance: ", Double.toString(distance(lat1,lon1,lat2,lon2)));





        // to retrieve the coordinates from the user
        // retrieve coordinates from the shop
        // calculate distance



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
        TextView shopname,qty,rate,tc,dist,r;
        Button b1,b2,bt;
        TextView t16,t21,sub;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            this.itemView.getContext();
            int a =1;


            shopname = itemView.findViewById(R.id.shopnameTV);
            qty = itemView.findViewById(R.id.qtyTV);
            rate = itemView.findViewById(R.id.rateTV);
            tc = itemView.findViewById(R.id.totalcostTV);
            dist = itemView.findViewById(R.id.distTV);
            b1 = itemView.findViewById(R.id.addtocartbtn);
            b2 = itemView.findViewById(R.id.feedbackbtn);
            r = itemView.findViewById(R.id.retailer_sub_name);
            t16 = itemView.findViewById(R.id.textView16);
            t21 = itemView.findViewById(R.id.textView21);
            sub = itemView.findViewById(R.id.retailer_sub_name);




        }
    }
}

