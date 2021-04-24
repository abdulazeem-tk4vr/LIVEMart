package com.example.oop_project.Main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.oop_project.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class addprodnoimage extends AppCompatActivity {

    private String quantity, Price, Pname, p_username, nkey, category, temp_quantity;
    private ImageView InputProductImage;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private Button AddNewProductButton;
    private TextView pname, pquantity, pprice;

    private DatabaseReference ProductsRef, UserData, Quantdata;
    private Spinner product_type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnoimage);


        {
            SharedPreferences sh = getApplicationContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

            // The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
            p_username = sh.getString("username", "Babuwhole");
            Log.i("memes", p_username);
// We can then use the data
        }//SharedPrefs

        InputProductImage = findViewById(R.id.select_product_image);
        AddNewProductButton = findViewById(R.id.add_new_product);
        pname = findViewById(R.id.product_name);
        pprice = findViewById(R.id.product_price);
        pquantity = findViewById(R.id.product_quantity);
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("User");


        {
            product_type = (Spinner) findViewById(R.id.spinner);
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Products));
            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            product_type.setAdapter(myAdapter);
        } //Spinner Product Type Initialization


        AddNewProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateProductData();
            }
        });


    }

    private void OpenGallery() {

        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }

    private void ValidateProductData() {
        Price = pprice.getText().toString().trim();
        Pname = pname.getText().toString().trim();
        quantity = pquantity.getText().toString().trim();
        category = product_type.getSelectedItem().toString();

        if (TextUtils.isEmpty(quantity)) {
            Toast.makeText(this, "Please write product quantity...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Price)) {
            Toast.makeText(this, "Please write product Price...", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(Pname)) {
            Toast.makeText(this, "Please write product name...", Toast.LENGTH_SHORT).show();
        } else {
            SaveProductInfoToDatabase();
        }
    }


    private void SaveProductInfoToDatabase() {

        UserData = FirebaseDatabase.getInstance().getReference()
                .child("User")
                .child("Wholesaler")
                .child(p_username)
                .child("Products")
                .child(category)
                .child(Pname)
                .child("quantity");

        UserData
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        HashMap<String, Object> productMap = new HashMap<>();
                        productMap.put("price", Price);
                        productMap.put("pname", Pname);

                        int newQuantity;
                        if (task.getResult().exists()) {
                            int currentQuantity = Integer.parseInt( (String )task.getResult().getValue());
                            newQuantity = Integer.parseInt(quantity) + currentQuantity;
                        } else {
                            newQuantity = Integer.parseInt(quantity);
                        }

                        productMap.put("quantity", String.valueOf(newQuantity));

                        FirebaseDatabase.getInstance().getReference().child("User").child("Wholesaler").
                                child(p_username).child("Products").child(category).child(Pname).updateChildren(productMap);

                        Toast.makeText(this, Pname+" successfully updated.", Toast.LENGTH_SHORT).show();
                    }
                });


        // if snapshot exists
        // Quantity = Quantity + temp_quantity;
        // update
//        if(InputProduct)

        // Quantdata.child(category).child(Pname).child("Wholesaler").child(p_username).updateChildren(productMap);
//        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//        startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GalleryPick && resultCode == RESULT_OK && data != null) {
            ImageUri = data.getData();
            InputProductImage.setImageURI(ImageUri);
        }
    }
}