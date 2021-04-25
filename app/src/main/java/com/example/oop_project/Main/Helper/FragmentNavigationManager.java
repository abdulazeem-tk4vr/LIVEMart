package com.example.oop_project.Main.Helper;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.oop_project.Main.Admin.Admin;
import com.example.oop_project.Main.Customer.CustomerCart;
import com.example.oop_project.Main.Customer.CustomerCategories;
import com.example.oop_project.Main.Customer.CustomerOrders;
import com.example.oop_project.Main.FragmentContent;
import com.example.oop_project.Main.Interface.NavigationManager;
import com.example.oop_project.Main.NavigationBar;
import com.example.oop_project.Main.Wholesaler_Add;
import com.example.oop_project.Main.addprodnoimage;
import com.example.oop_project.R;
import com.example.oop_project.Main.Retailer.RetailerCategories;
import com.example.oop_project.Main.Retailer.RetailerOrders;
import com.example.oop_project.Main.Retailer.RetailerTrans;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.BuildConfig;

import java.util.ArrayList;

public class FragmentNavigationManager  extends Fragment implements NavigationManager {
    private FragmentManager mFragmentManager;
    private NavigationBar mainActivity;
    private static FragmentNavigationManager mInstance;
    private static String p_username;





    public FragmentNavigationManager() {

    }




    public static FragmentNavigationManager getminstance(NavigationBar mainActivity , String username){



        if(mInstance==null)
            mInstance= new FragmentNavigationManager();
        mInstance.configure(mainActivity);
        p_username=username;
        Log.i("checking","this is "+ p_username);
        return mInstance;
    }
    private void configure(NavigationBar mainActivity){
        mainActivity=mainActivity;
        mFragmentManager = mainActivity.getSupportFragmentManager();
    }
    @Override
    public void loadFragment(String title) {
        showFragment(FragmentContent.newInstance(title),false);
    }
    @Override
    public void showFragment(String parentItem, String childItem) {

//
//       Context context =  VerifyOTPActivity.getActivity()
//


        switch(parentItem)
        {
            case "Customer":
                switch(childItem)
                {
                    case "Categories":
                        showFragment(new CustomerCategories(),false);
                        Log.i("status","customer cat");
                        break;
                   
                    case "orders":
                        FirebaseDatabase.getInstance().getReference().child("Cart").child("Customer").child(p_username)
                                .addListenerForSingleValueEvent(new ValueEventListener() {

                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        ArrayList<String> Keys = new ArrayList<>();
//                        Keys.clear();
                                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                            Keys.add(snapshot.getKey());
                                            Log.i("randomstuff",snapshot.getKey());
                                        }
                                        FragmentManager fm =  mFragmentManager;
                                        FragmentTransaction ft = fm.beginTransaction().replace(R.id.container,new CustomerOrders(Keys,p_username));
                                        ft.addToBackStack(null);
                                        if(false || !BuildConfig.DEBUG){
                                            ft.commitAllowingStateLoss();
                                        }else{
                                            ft.commit();
                                        }
                                        fm.executePendingTransactions();
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }
                                });
                        break;
                }
                break;
            case "Retailer":
                switch(childItem)
                {
                    case "Categories":
                        showFragment(new RetailerCategories(),false);
                        Log.i("status","retailer cat");
                        break;
                    case "Cart":
                        FirebaseDatabase.getInstance().getReference().child("Cart").child("Retailer").child(p_username)
                                .addListenerForSingleValueEvent(new ValueEventListener() {

                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        ArrayList<String> Keys = new ArrayList<>();
//                        Keys.clear();
                                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                            Keys.add(snapshot.getKey());
                                            Log.i("randomstuff",snapshot.getKey());
                                        }
                                        FragmentManager fm =  mFragmentManager;
                                        FragmentTransaction ft = fm.beginTransaction().replace(R.id.container,new RetailerOrders(Keys));
                                        ft.addToBackStack(null);
                                        if(false || !BuildConfig.DEBUG){
                                            ft.commitAllowingStateLoss();
                                        }else{
                                            ft.commit();
                                        }
                                        fm.executePendingTransactions();
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }
                                });

                        break;
                    case "transactions":
                        FirebaseDatabase.getInstance().getReference().child("Transaction").child("Retailer").child(p_username)
                                .addListenerForSingleValueEvent(new ValueEventListener() {

                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        ArrayList<String> Keys = new ArrayList<>();
//                        Keys.clear();
                                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                            Keys.add(snapshot.getKey());
                                            Log.i("randomstuff",snapshot.getKey());
                                        }
                                        FragmentManager fm =  mFragmentManager;
                                        FragmentTransaction ft = fm.beginTransaction().replace(R.id.container,new RetailerTrans(Keys,false));
                                        ft.addToBackStack(null);
                                        if(false || !BuildConfig.DEBUG){
                                            ft.commitAllowingStateLoss();
                                        }else{
                                            ft.commit();
                                        }
                                        fm.executePendingTransactions();
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }
                                });

                        break;
                }
                break;
            case "Wholesaler":
                switch(childItem)
                {
                    case "add item":
                        showFragment(new Wholesaler_Add(),false);
                        break;
                    case "transactions":
                        FirebaseDatabase.getInstance().getReference().child("Transaction").child("Wholesaler").child(p_username)
                                .addListenerForSingleValueEvent(new ValueEventListener() {

                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        ArrayList<String> Keys = new ArrayList<>();
//                        Keys.clear();
                                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                            Keys.add(snapshot.getKey());
                                            Log.i("randomstuff",snapshot.getKey());
                                        }
                                        FragmentManager fm =  mFragmentManager;
                                        FragmentTransaction ft = fm.beginTransaction().replace(R.id.container,new RetailerTrans(Keys,true));
                                        ft.addToBackStack(null);
                                        if(false || !BuildConfig.DEBUG){
                                            ft.commitAllowingStateLoss();
                                        }else{
                                            ft.commit();
                                        }
                                        fm.executePendingTransactions();
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }
                                });

                        break;
                    case "about":
                        showFragment(new Admin(),false);
                        break;
                }
                break;
            default:
                System.out.println("no match");
        }


    }
    public void showFragment(Fragment fragmentContent, boolean b){

        FragmentManager fm =  mFragmentManager;
        FragmentTransaction ft = fm.beginTransaction().replace(R.id.container,fragmentContent);
        ft.addToBackStack(null);
        if(b || !BuildConfig.DEBUG){
            ft.commitAllowingStateLoss();
        }else{
            ft.commit();
        }
        fm.executePendingTransactions();
    }

}