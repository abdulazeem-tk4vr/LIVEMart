package com.example.oop_project.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.oop_project.Customer.CustomerCart;
import com.example.oop_project.Customer.CustomerCategories;
import com.example.oop_project.Customer.CustomerOrders;
import com.example.oop_project.Main.FragmentContent;
import com.example.oop_project.Interface.NavigationManager;
import com.example.oop_project.Main.MainActivity;
import com.example.oop_project.Main.NavigationBar;
import com.example.oop_project.R;
import com.example.oop_project.Retailer.RetailerCategories;
import com.example.oop_project.Retailer.RetailerTrans;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.BuildConfig;

import java.util.ArrayList;

public class FragmentNavigationManager implements NavigationManager {
    private FragmentManager mFragmentManager;
    private NavigationBar mainActivity;
    private static FragmentNavigationManager mInstance;

    public static FragmentNavigationManager getminstance(NavigationBar mainActivity){
       if(mInstance==null)
           mInstance= new FragmentNavigationManager();
       mInstance.configure(mainActivity);
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


//        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
//
//// Creating an Editor object to edit(write to the file)
//        SharedPreferences.Editor myEdit = sharedPreferences.edit();
//
//// Storing the key and its value as the data fetched from edittext
//        myEdit.putString("username", "Macha");
//        myEdit.commit();
//        SharedPreferences sh = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
//
//        String p_username  = sh.getString("username", "");
        switch(parentItem)
        {
            case "Customer":
                switch(childItem)
                {
                    case "Categories":
                        showFragment(new CustomerCategories(),false);
                        Log.i("status","customer cat");
                        break;
                    case "Cart":
                        showFragment(new CustomerCart(),false);
                        break;
                    case "orders":
                        String p_username="Macha";
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
                        showFragment(new CustomerCart(),false);
                        break;
                    case "orders":
                        FirebaseDatabase.getInstance().getReference().child("Transaction").child("Retailer").child("Fgretailer")
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
                        break;
                    case "transactions":
                        FirebaseDatabase.getInstance().getReference().child("Transaction").child("Wholesaler").child("Ytwhole")
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
