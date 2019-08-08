package com.example.mahesh.BookRent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Book_Activity extends AppCompatActivity {
    private TextView tvtitle,tvauthor,tvcategory,tvcontact,tvprice;
    private ImageView img;
    private Button callbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_);

        tvtitle = findViewById(R.id.title_id);
        tvcategory = findViewById(R.id.category_id);
        tvauthor= findViewById(R.id.author_id);
        tvprice = findViewById(R.id.price_id);
        tvcontact=findViewById(R.id.contact_id);
        img = findViewById(R.id.bookthumbnail);
        callbtn = findViewById(R.id.btncall);

        Intent intent = getIntent();
        String Title =intent.getExtras().getString("Title");
        String Category =intent.getExtras().getString("Category");
        String Author =intent.getExtras().getString("Author");
        String price =intent.getExtras().getString("Price");
        final String Contact =intent.getExtras().getString("Contact");
        String Thumbnail = intent.getExtras().getString("Thumbnail");




        tvtitle.setText("Title : "+Title);
        tvcategory.setText("Category : "+Category);
        tvauthor.setText("Author : "+Author);
        tvcontact.setText("Contact : "+Contact);
        tvprice.setText("Price : " +price);
        Glide.with(Book_Activity.this).load(Thumbnail).into(img);

        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+Contact));
                startActivity(intent);
            }
        });
    }
}
