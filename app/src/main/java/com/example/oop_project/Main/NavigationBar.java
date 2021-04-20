package com.example.oop_project.Main;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.oop_project.Adapter.CustomExpandableListAdapter;
import com.example.oop_project.Helper.FragmentNavigationManager;
import com.example.oop_project.Interface.NavigationManager;
import com.example.oop_project.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class NavigationBar extends AppCompatActivity {

    /*private String[] groupItem = {"Customer","Retailer","Wholesaler"};
    private String[][] childItem = {{"Categories","Cart","orders","logout"},{"Categories","Cart","orders","add item","logout"},
            {"orders","add item","logout"}};*/
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private String[] items;

    private ExpandableListView expandableListView;
    private ExpandableListAdapter adapter;
    private List<String> lstTitle;
    private Map<String,List<String>> lstChild;
    private NavigationManager navigationManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_navbar);

        //init view
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = (ExpandableListView) findViewById(R.id.navList);
        navigationManager = FragmentNavigationManager.getminstance(this);
        initItems();

        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header,null,false);
        expandableListView.addHeaderView(listHeaderView);
        gendata();
        addDrawersItem();
        setupDrawer();
        if(savedInstanceState ==null){
            selectFirstItemAsDefault();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("OOPS Project");
    }
    private void selectFirstItemAsDefault(){
        if(navigationManager!=null){
            String firstItem = lstTitle.get(0);
            navigationManager.loadFragment(firstItem);
            getSupportActionBar().setTitle(firstItem);
        }
    }
    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open,R.string.close)
        {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getSupportActionBar().setTitle("OOPS Project");
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu();
            }

        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void addDrawersItem() {
        Log.i("kardib2",String.valueOf(lstTitle.size()));
        Log.i("kardib2",String.valueOf(lstChild.size()));
        adapter =  new CustomExpandableListAdapter(this, lstTitle,lstChild);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                getSupportActionBar().setTitle(lstTitle.get(groupPosition).toString());
            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                getSupportActionBar().setTitle("OOPS Project");
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String selectedItem = ((List) lstChild.get(lstTitle.get(groupPosition))).get(childPosition).toString();
                getSupportActionBar().setTitle(selectedItem);
                String parentItem = lstTitle.get(groupPosition);
                if(items[0].equals(parentItem)){
                    navigationManager.showFragment(parentItem.toString(), selectedItem);
                }else if(items[1].equals(parentItem)){
                    navigationManager.showFragment(parentItem.toString(), selectedItem);
                }else if(items[2].equals(parentItem)){
                    navigationManager.showFragment(parentItem.toString(), selectedItem);
                }else{
                    throw new IllegalArgumentException("Not supported Fragment");
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    private void gendata(){
        List<String> title = Arrays.asList("Customer","Retailer","Wholesaler");
        List<String> childitem1 = Arrays.asList("Categories","Cart","orders");
        List<String> childitem2 = Arrays.asList("Categories","Cart","orders","add item");
        List<String> childitem3 = Arrays.asList("transactions","add item","about");
        lstChild = new TreeMap<>();
        lstChild.put(title.get(0),childitem1);
        lstChild.put(title.get(1),childitem2);
        lstChild.put(title.get(2),childitem3);
        Log.i("kardib",String.valueOf(lstChild.size()));

        lstTitle = new ArrayList<>(lstChild.keySet());
        Log.i("kardib",String.valueOf(lstTitle.size()));

    }
    private void initItems(){
        items = new String[] {"Customer","Retailer","Wholesaler"};
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(mDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}