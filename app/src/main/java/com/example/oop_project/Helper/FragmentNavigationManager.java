package com.example.oop_project.Helper;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.oop_project.FragmentContent;
import com.example.oop_project.Interface.NavigationManager;
import com.example.oop_project.NavigationBar;
import com.example.oop_project.R;
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
    public void showFragment(String title) {
        showFragment(FragmentContent.newInstance(title),false);

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
