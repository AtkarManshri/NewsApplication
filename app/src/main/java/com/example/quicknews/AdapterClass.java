package com.example.quicknews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ViewHolder>

{  //things to be declred in adpter
    Context context;
    ArrayList<Model> modelArrayList; //get data in array artices

    public AdapterClass(Context context, ArrayList<Model> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public AdapterClass.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //toreturn view
        View view= LayoutInflater.from(context).inflate(R.layout.layout_item,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterClass.ViewHolder holder, int position) {
    //to set data acording to item position we need on bind view holder
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            //to open web view on clicking card
            public void onClick(View v) {
                Intent intent=new Intent(context, Webview.class);
                //to open url on new activity
                intent.putExtra("url",modelArrayList.get(position).getUrl());
                context.startActivity(intent);

            }
        });
        holder.time.setText("Published At:"+modelArrayList.get(position).getPublishedAt());
        holder.author.setText(modelArrayList.get(position).getAuthor());
        holder.heading.setText(modelArrayList.get(position).getTitle());
        holder.content.setText(modelArrayList.get(position).getDescription());
        //to show image use glide
        Glide.with(context).load(modelArrayList.get(position).getUrlToImage()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView heading,content,author,time;
        CardView cardView; //taking card view so that if any user click on card it will direct to web view
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            heading=itemView.findViewById(R.id.mainHeading);
            content=itemView.findViewById(R.id.content);
            author=itemView.findViewById(R.id.author);
            time=itemView.findViewById(R.id.time);
            imageView=itemView.findViewById(R.id.imageView);
            cardView=itemView.findViewById(R.id.cardView);

        }
    }
}
