package com.ceforce.app_rendamos.asq3data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.ceforce.app_rendamos.R;

import java.lang.reflect.Array;

public class TestResults extends AppCompatActivity {

    int[] array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_results);

        array = getIntent().getIntArrayExtra("result");
    }
}

