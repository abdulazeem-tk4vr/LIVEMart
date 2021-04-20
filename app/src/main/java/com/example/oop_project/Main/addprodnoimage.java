package com.example.oop_project.Main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oop_project.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

public class addprodnoimage extends AppCompatActivity {

    private String quantity, Price, Pname, downloadImageUrl;
    private ImageView InputProductImage;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private Button AddNewProductButton;
    private TextView pname, pquantity, pprice;
    private StorageReference ProductImagesRef;
    private DatabaseReference ProductsRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_product);


        InputProductImage = findViewById(R.id.select_product_image);
        AddNewProductButton = findViewById(R.id.add_new_product);
        pname = findViewById(R.id.product_name);
        pprice = findViewById(R.id.product_price);
        pquantity = findViewById(R.id.product_quantity);
        ProductImagesRef = FirebaseStorage.getInstance().getReference().child("Product Images");
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("User");


//        InputProductImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                OpenGallery();
//            }
//        });

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
        quantity = pquantity.getText().toString();
        Price = pprice.getText().toString();
        Pname = pname.getText().toString();


//       if (ImageUri == null)
//       {
//           Toast.makeText(this, "Product image is mandatory...", Toast.LENGTH_SHORT).show();
//       }
        if (TextUtils.isEmpty(quantity)) {
            Toast.makeText(this, "Please give the quantity...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Price)) {
            Toast.makeText(this, "Please write product Price...", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(Pname)) {
            Toast.makeText(this, "Please write product name...", Toast.LENGTH_SHORT).show();
        } else {
            SaveProductInfoToDatabase();
        }
    }

//    private void StoreProductInformation() {
//
//        final StorageReference filePath = ProductImagesRef.child(ImageUri.getLastPathSegment() + ".jpg");
//        final UploadTask uploadTask = filePath.putFile(ImageUri);
//
//        uploadTask.addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e)
//            {
//                String message = e.toString();
//                Toast.makeText(addproduct.this, "Error: " + message, Toast.LENGTH_SHORT).show();
//            }
//        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
//            {
//                Toast.makeText(addproduct.this, "Product Image uploaded Successfully...", Toast.LENGTH_SHORT).show();
//
//                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
//                    @Override
//                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
//                    {
//                        if (!task.isSuccessful())
//                        {
//                            throw task.getException();
//                        }
//
//                        downloadImageUrl = filePath.getDownloadUrl().toString();
//                        return filePath.getDownloadUrl();
//                    }
//                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Uri> task)
//                    {
//                        if (task.isSuccessful())
//                        {
//                            downloadImageUrl = task.getResult().toString();
//
//                            Toast.makeText(addproduct.this, "got the Product image Url Successfully...", Toast.LENGTH_SHORT).show();
//
//                            SaveProductInfoToDatabase();
//                        }
//                    }
//                });
//            }
//        });
//
//    }

    private void SaveProductInfoToDatabase() {
        HashMap<String, Object> productMap = new HashMap<>();

//        productMap.put("image", downloadImageUrl);
        productMap.put("price", Price);
        productMap.put("pname", Pname);
        productMap.put("quantity", quantity);
        productMap.put("status", "Not Approved");

        ProductsRef.child("Retailer").child("Fgretailer").child("Products").child("Fruits").child(Pname).updateChildren(productMap);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
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