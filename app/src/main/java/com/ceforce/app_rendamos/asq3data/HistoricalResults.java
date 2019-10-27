package com.ceforce.app_rendamos.asq3data;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ceforce.app_rendamos.R;
import com.ceforce.app_rendamos.RecyclerView.RecyclerViewAdapter;
import com.ceforce.app_rendamos.Utilities.DateUtilities;
import com.ceforce.app_rendamos.user.UserDetails;

import org.json.JSONObject;

import java.util.ArrayList;

public class HistoricalResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical_results);


    }

    public void toUserHistory(View view){
        Intent intent = new Intent(this, UserDetails.class);
        startActivity(intent);
    }

}
