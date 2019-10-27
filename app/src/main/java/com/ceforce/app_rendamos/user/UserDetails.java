package com.ceforce.app_rendamos.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ceforce.app_rendamos.ASQ3TestActivity;
import com.ceforce.app_rendamos.R;

public class UserDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
    }

    public void onApply(View view){
        Intent i = new Intent(this, ASQ3TestActivity.class);
        startActivity(i);
    }
}
