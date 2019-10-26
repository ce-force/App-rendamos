package com.ceforce.app_rendamos.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ceforce.app_rendamos.R;

public class LoginActivity extends AppCompatActivity {

    TextView tvSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvSignUp = findViewById(R.id.tvSignUp);


        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignUp = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intSignUp);
            }
        });

    }
}
