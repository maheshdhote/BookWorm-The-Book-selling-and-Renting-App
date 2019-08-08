package com.example.mahesh.BookRent;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    View view;
    private Context mContext;
    private List<Book> mData;

    public RecyclerViewAdapter(Context mContext, List<Book> mData) {
        this.mContext = mContext;
        this.mData = mData;

    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater =LayoutInflater.from(mContext);
        view =mInflater.inflate(R.layout.cardview_item_book,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.tv_book_title.setText(mData.get(position).getTitle());
        //holder.img_book_thumbnail.setImageURI(mData.get(position).getUri());
        Picasso.get().load(mData.get(position).getUri()).into(holder.img_book_thumbnail);



        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //passing data to book activity
                Intent intent = new Intent(mContext,Book_Activity.class);
                intent.putExtra("Title",mData.get(position).getTitle());
                intent.putExtra("Category",mData.get(position).getCategory());
                intent.putExtra("Author",mData.get(position).getAuthor());
                intent.putExtra("Contact",mData.get(position).getContact());
                intent.putExtra("Price",mData.get(position).getPrice());
                intent.putExtra("Thumbnail",mData.get(position).getURLofImage());

                mContext.startActivity(intent);

            }
        });

        //set On Click listener
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
{
    TextView tv_book_title;
    ImageView img_book_thumbnail;
    CardView cardView;

    public MyViewHolder(View itemView)
    {
        super(itemView);
        tv_book_title =itemView.findViewById(R.id.book_title);
        img_book_thumbnail= itemView.findViewById(R.id.book_img_id);
        cardView = itemView.findViewById(R.id.cardview_id);


    }
}



}
