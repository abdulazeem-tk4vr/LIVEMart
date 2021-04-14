package com.example.oop_project.Helper;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.oop_project.Customer.CustomerCart;
import com.example.oop_project.Customer.CustomerCategories;
import com.example.oop_project.Customer.CustomerOrders;
import com.example.oop_project.FragmentContent;
import com.example.oop_project.Interface.NavigationManager;
import com.example.oop_project.NavigationBar;
import com.example.oop_project.R;
import com.example.oop_project.Retailer.RetailerCategories;
import com.rey.material.BuildConfig;

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
                        showFragment(new CustomerOrders(),false);
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
                        showFragment(new CustomerOrders(),false);
                        break;
                }
                break;
            case "Wholesaler":
                System.out.println("Wholesaler");
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
