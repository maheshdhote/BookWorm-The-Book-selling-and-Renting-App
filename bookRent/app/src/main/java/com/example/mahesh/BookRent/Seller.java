package com.example.mahesh.BookRent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class Seller extends AppCompatActivity {

    private FirebaseStorage storage;
    private Uri filepath;
    private StorageReference storageReference;
    private FirebaseDatabase database;
    private DatabaseReference addRecord;

    private Button choose,upload;
    private ImageView imageView;
    private EditText name,author,category,price,phone;

    String nameStr="",authorStr="",catStr="",priceStr="",phoneStr="";
    String id;

    static final int PICK_IMAGE_REQUEST = 71;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);
        choose =findViewById(R.id.chooseIDD);
        upload = findViewById(R.id.uploadIDD);
        imageView = findViewById(R.id.imageView);


        //Firebase Storage  and Reference Instances
        storage = FirebaseStorage.getInstance();
        storageReference=storage.getReference();

        name = findViewById(R.id.nameID);
        author = findViewById(R.id.authorID);
        category =findViewById(R.id.categoryID);
        price=findViewById(R.id.priceID);
        phone = findViewById(R.id.phonID);


        database = FirebaseDatabase.getInstance();
        addRecord = database.getReference();



        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    uploadImage();

            }
        });

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });


    }


    private void uploadImage()
    {
        //Uploading data

        nameStr = name.getText().toString().trim();
        authorStr = author.getText().toString().trim();
        catStr = category.getText().toString().trim();
        priceStr =price.getText().toString().trim();
        phoneStr =phone.getText().toString().trim();




        if(nameStr.isEmpty())
        {
           // Toast.makeText(Seller.this,"Enter Name",Toast.LENGTH_LONG).show();
           // return;
            name.setError("Name required");
            name.requestFocus();
            return;

        }
        else if (authorStr.isEmpty())
        {
            //Toast.makeText(Seller.this,"Enter author",Toast.LENGTH_LONG).show();
           // return;
            author.setError("Auther required");
            author.requestFocus();
            return;

        }

        else if(catStr.isEmpty())
        {
           // Toast.makeText(Seller.this,"Enter category",Toast.LENGTH_LONG).show();
           // return;
            category.setError("category required");
            category.requestFocus();
            return;


        }
        else if(phoneStr.isEmpty())
        {

           // Toast.makeText(Seller.this,"Enter Phone",Toast.LENGTH_LONG).show();
           // return ;
            phone.setError("phone required");
            phone.requestFocus();
            return;
        }
        else if(phoneStr.length()<10 ||phoneStr.length()>10)
        {
            phone.setError("phone is incorrect");
            phone.requestFocus();
            return;
        }
        else if(priceStr.isEmpty())
        {
          //  Toast.makeText(Seller.this,"Enter Price",Toast.LENGTH_LONG).show();
          //  return;
            price.setError("price required");
            price.requestFocus();
            return;

        }





        //  Image Processing
        if(imageView.getDrawable()==null)
        {
            Toast.makeText(Seller.this, "Choose Image", Toast.LENGTH_SHORT).show();
            return;
        }

        id = addRecord.push().getKey();

        //Uploading Image
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading ...");
        progressDialog.show();

        StorageReference ref=storageReference.child("images/"+id);

        ref.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                //Adding data if photo uploaded successfully
                String URLofImage=taskSnapshot.getDownloadUrl().toString();
                ReturnRecord returnRecord = new ReturnRecord(id,nameStr, authorStr, catStr, priceStr, phoneStr,URLofImage);
                addRecord.child(id).setValue(returnRecord).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                    }
                });


                progressDialog.dismiss();;
                Toast.makeText(Seller.this,"Uploaded Data SuccessFully",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Seller.this,Buyer.class);
                startActivity(intent);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            progressDialog.dismiss();
                Toast.makeText(Seller.this,"Failed Upload",Toast.LENGTH_SHORT).show();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress=(100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
            progressDialog.setMessage("Uploaded"+(int)progress+"%");
            }
        });
    }


    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        {
            if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null)
            {
                filepath =data.getData();
                try
                {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                    imageView.setImageBitmap(bitmap);
                }catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

        }



}
