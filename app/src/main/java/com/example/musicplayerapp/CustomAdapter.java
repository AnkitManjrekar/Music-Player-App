package com.example.musicplayerapp;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>  {

    ArrayList<AudioModel> songsList;
    Context ctx;

    public CustomAdapter(ArrayList<AudioModel> songsList, Context context) {
        this.songsList = songsList;
        this.ctx = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.text_row_item, parent, false);
        return new CustomAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AudioModel songData = songsList.get(position);
        holder.textView.setText(Integer.toString(position+1)+".");
        holder.textView2.setText(songData.getTitle());
        if(MyMediaPlayer.currentIndex == position){
            holder.textView2.setTextColor(Color.parseColor("#E91563"));
        }else{
            holder.textView2.setTextColor(Color.parseColor("#000000"));
        }
        int p = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyMediaPlayer.getInstance().reset();
                MyMediaPlayer.currentIndex = p;
                Intent intent = new Intent(ctx, MusicPlayerActivity.class);
                intent.putExtra("LIST", songsList);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView, textView2;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView6);
            textView2 = itemView.findViewById(R.id.textView7);
        }
    }
}

