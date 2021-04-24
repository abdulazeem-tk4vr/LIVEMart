package com.example.oop_project.Main.Customer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oop_project.R;
import com.example.oop_project.Main.Retailer.Orders_sub;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseError;

public class CartAdapter extends FirebaseRecyclerAdapter<Orders_sub, CartAdapter.myviewholder> {
    boolean t;
    public CartAdapter(@NonNull FirebaseRecyclerOptions<Orders_sub> options) {
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
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull final Orders_sub model) {

        holder.shop.setText("Customer Name: ");
        holder.pname.setText(model.getPname());
        holder.custname.setText(model.getShop());
        holder.cost.setText(model.getCost());
        holder.ddate.setText(model.getDdate());
        holder.dname.setText(model.getDname());
        holder.dnum.setText(model.getDnumber());
        holder.dqty.setText(model.getQuantity());
        holder.status.setText(model.getStatus());
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_info, parent, false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        TextView custname,cost,ddate,dname,dnum,dqty,status,pname,shop;
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
        }
    }

}
