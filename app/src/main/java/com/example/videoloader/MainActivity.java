package com.example.videoloader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager recyclerviewLayoutManager;

    private ArrayList<VideoModel> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        recyclerView = findViewById(R.id.recyclerView);
        recyclerviewLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(recyclerviewLayoutManager);
        arrayList = new ArrayList<>();
        fetchVideos();
    }
    private void fetchVideos(){

    }
}