package com.example.videoloader.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.videoloader.Model.VideoModel;
import com.example.videoloader.R;
import com.example.videoloader.VideoPlayerActivity;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    Context context;
    ArrayList<VideoModel> arrayList;
    Activity activity;

    public VideoAdapter(Context context, ArrayList<VideoModel> arrayList, Activity activity) {
        this.context = context;
        this.arrayList = arrayList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_video,parent,false);
        return new VideoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load("file://"+arrayList.get(position).getThumb())
                .skipMemoryCache(false)
                .into(holder.imageView);

        holder.relativeLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        holder.relativeLayout.setAlpha(0);

        holder.relativeLayout.setOnClickListener(v -> {
                Intent i = new Intent(context, VideoPlayerActivity.class);
                i.putExtra("Video",arrayList.get(position).getThumb());
                activity.startActivity(i);
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{

        ImageView imageView;
        RelativeLayout relativeLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.iv_image);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }
}
