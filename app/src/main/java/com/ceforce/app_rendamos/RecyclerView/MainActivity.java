package com.ceforce.app_rendamos.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.ceforce.app_rendamos.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "started: ok");
        initBitmaps();
    }

    private void initBitmaps(){

        Log.d(TAG, "init bitmaps: preparing");

        mImageUrls.add("1");
        mNames.add("Havasu Falls");

        mImageUrls.add("2");
        mNames.add("Trondheim");

        mImageUrls.add("3");
        mNames.add("Portugal");



        initRecyclerView();


        Log.d(TAG, "init bitmaps: done");

    }

    private void initRecyclerView(){

        Log.d(TAG, "initRecyclerView: init recyclerView");
        RecyclerView recyclerView = findViewById(R.id.recycler);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
