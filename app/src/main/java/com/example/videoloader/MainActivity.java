package com.example.videoloader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.example.videoloader.Adapter.VideoAdapter;
import com.example.videoloader.Model.VideoModel;

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

        getPermission();
    }
    private void getPermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.READ_EXTERNAL_STORAGE }, 101);
        }
        else {
            fetchVideos();
            Toast.makeText(MainActivity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }
    }

    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 101) {

            // Checking whether user granted the permission or not.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchVideos();

                // Showing the toast message
                Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void fetchVideos(){
        Uri uri;
        Cursor cursor;
        int column_index_data,thum;

        String absoluteImagePath ;

        uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {
                MediaStore.MediaColumns.DATA,
                MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Video.Media._ID,
                MediaStore.Video.Thumbnails.DATA};

        String orderBy = MediaStore.Images.Media.DATE_TAKEN;

        cursor = getApplicationContext().getContentResolver().query(uri,projection,null,null,orderBy+" DESC");

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        thum = cursor.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.DATA);

        while (cursor.moveToNext())
        {
            absoluteImagePath = cursor.getString(column_index_data);

            VideoModel videoModel = new VideoModel();
            videoModel.setSelected(false);
            videoModel.setPath(absoluteImagePath);
            videoModel.setThumb(cursor.getString(thum));

            arrayList.add(videoModel);
        }

        VideoAdapter videoAdapter = new VideoAdapter(getApplicationContext(),arrayList,MainActivity.this);
        recyclerView.setAdapter(videoAdapter);
        cursor.close();
    }
}























