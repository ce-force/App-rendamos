package com.ceforce.app_rendamos.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ceforce.app_rendamos.R;

public class RegisterActivity extends AppCompatActivity {

    TextView tvSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tvSignIn = findViewById(R.id.tvSignUp);

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignUp = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intSignUp);
            }
        });

    }
}
