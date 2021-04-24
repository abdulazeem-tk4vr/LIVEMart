package com.example.oop_project.Main.Retailer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oop_project.Main.AlarmActivity;
import com.example.oop_project.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrderAdapter extends FirebaseRecyclerAdapter<Orders_sub, OrderAdapter.myviewholder> {
    boolean t;
    public  String cust_uid;
    Context ct;
    public OrderAdapter(@NonNull FirebaseRecyclerOptions<Orders_sub> options,Context c) {
        super(options);
        ct = c;
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
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull final Orders_sub model) {
        SharedPreferences sh = ct.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);


        String p_username  =  sh.getString("username","Fgretailer");
        String p_usertype  = sh.getString("usertype","Retailer");
        holder.shop.setText("Wholesaler Name: ");
        holder.pname.setText(model.getPname());
        holder.custname.setText(model.getShop());
        holder.cost.setText(model.getCost());
        holder.ddate.setText(model.getDdate());
        holder.dname.setText(model.getDname());
        holder.dnum.setText(model.getDnumber());
        holder.dqty.setText(model.getQuantity());
        holder.status.setText(model.getStatus());

        holder.codbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String date = model.getDdate();
                Toast.makeText(ct,"The order will be delivered on " + date,Toast.LENGTH_SHORT).show();

            }
        });
        holder.offbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ct, AlarmActivity.class);
                ct.startActivity(i);

            }
        });
        holder.delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                DatabaseReference uid_ref = FirebaseDatabase.getInstance().getReference().child("User").child("Retailer").child(p_username).child("Details").child("UID");
                uid_ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                            cust_uid = snapshot.getValue().toString();
                            Log.i("meme","value"+cust_uid);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                Log.i("meme","value"+cust_uid);
                DatabaseReference Rootref = FirebaseDatabase.getInstance().getReference().child("Cart").child("Retailer").child(p_username).child(cust_uid).child(model.getPname());

                Rootref.removeValue();
            }
        });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_info, parent, false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        TextView custname,cost,ddate,dname,dnum,dqty,status,pname,shop;
        Button codbtn,offbtn,delbtn;
        public myviewholder(@NonNull View view) {
            super(view);
            shop = (TextView) view.findViewById(R.id.Custtitle);
            pname = (TextView) view.findViewById(R.id.Titletext);
            custname = (TextView) view.findViewById(R.id.CustName);
            cost = (TextView) view.findViewById(R.id.cost_trans);
            ddate = (TextView) view.findViewById(R.id.deliverydate);
            dname = (TextView) view.findViewById(R.id.deliveryexec);
            dnum = (TextView) view.findViewById(R.id.deliverynum);
            dqty = (TextView) view.findViewById(R.id.quantity_trans);
            status = (TextView) view.findViewById(R.id.stat_trans);
            codbtn = (Button) view.findViewById(R.id.codbutton);
            offbtn = (Button) view.findViewById(R.id.offlinetransact);
            delbtn = (Button) view.findViewById(R.id.deletebtn);
        }
    }

}
