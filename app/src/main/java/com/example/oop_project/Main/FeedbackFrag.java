package com.example.oop_project.Main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oop_project.Main.Customer.model_category;
import com.example.oop_project.Main.Customer.myadapterCustomerCategory;
import com.example.oop_project.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FeedbackFrag extends Fragment {

    RecyclerView recview;
    myadapterCustomerCategory adapter;
    TextView tv,tv2;
    EditText et;
    Button bt;
    String source;
    String p_usertype, p_username, arg_pname,retname;

    public FeedbackFrag(String p_usertype,String p_username,String arg_pname,String retname) {
        this.p_username=p_username;
        this.p_usertype=p_usertype;
        this.arg_pname=arg_pname;
        this.retname=retname;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.feedback_layout, container, false);

        tv = view.findViewById(R.id.feedbacktv);
        et = view.findViewById(R.id.feedbackip);
        bt = view.findViewById(R.id.addfbbtn);

        Random random = new Random();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        bt.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                tv.setText("Feedback Added!");
                Map<String, Object> map = new HashMap<>();
                map.put("feedback",et.getText().toString());
                map.put("seller",retname);
                Log.i("eeiu",p_username);
                Log.i("eeiu",p_usertype);
                Log.i("eeiu",arg_pname);
                if(p_usertype.equals("Customer")){
                    source = "Retailer";
                }else if(p_usertype.equals("Retailer")){
                    source = "Wholesaler";
                }
                if(db.child("Feedback").child(source).child(p_username).child(arg_pname).child(retname)!=null){
                    db.child("Feedback").child(source).child(retname).child(arg_pname).child(p_username).updateChildren(map);
                }else {
                    //db.child("Feedback").child(p_usertype).child(p_username).child(arg_pname).child(retname).setValue(map);
                    db.child("Feedback").child(source).child(retname).child(arg_pname).child(p_username).setValue(map);
                }
            }
        });
        return view;
    }

}
