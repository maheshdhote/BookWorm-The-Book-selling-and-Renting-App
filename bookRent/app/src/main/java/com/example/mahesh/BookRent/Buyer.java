package com.example.mahesh.BookRent;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Buyer extends AppCompatActivity {

    private  List<Book> lstBook;


    DatabaseReference returnAllrecords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer);

        lstBook = new ArrayList<>();

       // lstBook.add(new Book("Author","CateBook","96575","id here","price","Title here",R.drawable.wingsoffire));
        //lstBook.add(new Book("Author","CateBook","96575","id here","price","Title here",R.drawable.wingsoffire));
        returnAllrecords = FirebaseDatabase.getInstance().getReference();

        lstBook.clear();


        returnAllrecords.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot retSnapshot:dataSnapshot.getChildren())
                {
                    Readbooks readbooks = new Readbooks();
                    readbooks.setAuthor(retSnapshot.getValue(Readbooks.class).getAuthor());
                    readbooks.setCategory(retSnapshot.getValue(Readbooks.class).getCategory());
                    readbooks.setContact(retSnapshot.getValue(Readbooks.class).getContact());
                    readbooks.setId(retSnapshot.getValue(Readbooks.class).getId());
                    readbooks.setPrice(retSnapshot.getValue(Readbooks.class).getPrice());
                    readbooks.setTitle(retSnapshot.getValue(Readbooks.class).getTitle());
                    readbooks.setUrlofimage(retSnapshot.getValue(Readbooks.class).getUrlofimage());
                    Log.d("Author", "onDataChange:"+readbooks.getAuthor());
                    Log.d("Title", "onDataChange:"+readbooks.getTitle());
                    Log.d("ID", "onDataChange:"+readbooks.getId());
                    Log.d("URL","Here :"+readbooks.getUrlofimage());

                    String auth = readbooks.getAuthor();
                    String title = readbooks.getTitle();
                    String cat = readbooks.getCategory();
                    String pri =readbooks.getPrice();
                    String id = readbooks.getId();

                    String contact = readbooks.getContact();
                    String urlofimage= readbooks.getUrlofimage();


                    try{
                        URL url = new URL(urlofimage);
                        Uri uri = Uri.parse(url.toURI().toString());
                        Log.d("URI",""+uri);
                        lstBook.add(new Book(auth,cat,contact,id,pri,title,urlofimage,uri));
                    }
                    catch(Exception e)
                    {
                        Log.d("Error","In Exceptiion");

                        Log.e("InExceptio ",":"+e);
                    }



                }
                RecyclerView myrv =  findViewById(R.id.recyclerview_id);
                RecyclerViewAdapter myAdapter =new RecyclerViewAdapter(Buyer.this,lstBook);
                myrv.setLayoutManager(new GridLayoutManager(Buyer.this,3));
                myrv.setAdapter(myAdapter);

            }




            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
}
