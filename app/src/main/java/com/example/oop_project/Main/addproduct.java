package com.example.oop_project.Main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oop_project.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

import static android.widget.Toast.LENGTH_LONG;

public class addproduct extends AppCompatActivity  {

    private String quantity,Price,Pname, downloadImageUrl,category,p_username,latitude,longitude;
    private ImageView InputProductImage ;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private Button AddNewProductButton;
    private TextView pname , pquantity , pprice;
    private StorageReference ProductImagesRef;
    private DatabaseReference ProductsRef,Quantityref,dbref,imageref;
    private Spinner product_type;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_product);


        {
            SharedPreferences sh = getApplicationContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

            // The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
            p_username = sh.getString("username", "Babuwhole");
            Log.i("memes",p_username);
// We can then use the data
        }//SharedPrefs




        InputProductImage = findViewById(R.id.select_product_image);
        AddNewProductButton = findViewById(R.id.add_new_product);
        pname = findViewById(R.id.product_name);
        pprice = findViewById(R.id.product_price);
        pquantity = findViewById(R.id.product_quantity);
        ProductImagesRef = FirebaseStorage.getInstance().getReference().child("Product Images");


        {

            product_type = (Spinner) findViewById(R.id.spinner);
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Products));
            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            product_type.setAdapter(myAdapter);
        } //Spinner Initialization



        InputProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });

        AddNewProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {


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
        quantity = pquantity.getText().toString();
        Price = pprice.getText().toString();
        Pname = pname.getText().toString();
        category = product_type.getSelectedItem().toString();


        if (!(category.contains("Fruits") || !category.contains("Vegetables") )) {
            Toast.makeText(getApplicationContext(), "Please select a valid user", LENGTH_LONG).show();
        }
        else if (ImageUri == null)
       {
           Toast.makeText(this, "Product image is mandatory...", Toast.LENGTH_SHORT).show();
       }
        else if (TextUtils.isEmpty(quantity))
        {
            Toast.makeText(this, "Please give the quantity...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Price))
        {
            Toast.makeText(this, "Please write product Price...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Pname))
        {
            Toast.makeText(this, "Please write product name...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            StoreProductInformation();
        }
    }

    private void StoreProductInformation() {

        final StorageReference filePath = ProductImagesRef.child(ImageUri.getLastPathSegment() + ".jpg");
        final UploadTask uploadTask = filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                String message = e.toString();
                Toast.makeText(addproduct.this, "Error: " + message, Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                Toast.makeText(addproduct.this, "Product Image uploaded Successfully...", Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                    {
                        if (!task.isSuccessful())
                        {
                            throw task.getException();
                        }

                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task)
                    {
                        if (task.isSuccessful())
                        {
                            downloadImageUrl = task.getResult().toString();

                            Toast.makeText(addproduct.this, "got the Product image Url Successfully...", Toast.LENGTH_SHORT).show();

                            SaveProductInfoToDatabase();
                        }
                    }
                });
            }
        });

    }

    private void SaveProductInfoToDatabase() {





        {
            dbref = FirebaseDatabase.getInstance().getReference().child("User").child("Wholesaler").child(p_username).child("Details");
            dbref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    latitude = snapshot.child("latitude").getValue().toString();
                    Log.e("memes", latitude);
                    longitude = snapshot.child("longitude").getValue().toString();
                    Log.e("memes", longitude);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }//Coordinate Extraction

        {
            ProductsRef = FirebaseDatabase.getInstance().getReference().child("User").child("Wholesaler").child(p_username).child("Products");

            HashMap<String, Object> productMap = new HashMap<>();
            productMap.put("pname", Pname);
            productMap.put("price", Price);
            productMap.put("quantity", quantity);

            ProductsRef.child(category).child(Pname).updateChildren(productMap);
        }//Productref

        {
            Quantityref = FirebaseDatabase.getInstance().getReference().child("Quantity").child(category).child(Pname).child("Wholesaler").child(p_username);
            HashMap<String, Object> quantityMap = new HashMap<>();
            quantityMap.put("rname", p_username);
            quantityMap.put("price", Price);
            quantityMap.put("quantity",quantity);
            quantityMap.put("latitude",latitude);
            quantityMap.put("longitude",longitude);

            Quantityref.updateChildren(quantityMap);
        }//QuantityRef

        {
            imageref = FirebaseDatabase.getInstance().getReference().child("Products").child(category).child(Pname);
            HashMap<String, Object> imagemap = new HashMap<>();
            imagemap.put("pname",Pname);
            imagemap.put("status","Approved");
            imagemap.put("image",downloadImageUrl);

            imageref.updateChildren(imagemap);
        }//Imageref



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==GalleryPick  &&  resultCode==RESULT_OK  &&  data!=null)
        {
            ImageUri = data.getData();
            InputProductImage.setImageURI(ImageUri);
        }
    }
}