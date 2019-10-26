package com.ceforce.app_rendamos.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ceforce.app_rendamos.MainActivity;
import com.ceforce.app_rendamos.R;

public class LoginActivity extends AppCompatActivity {

    private EditText id, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id = findViewById(R.id.idInputField);
        password = findViewById(R.id.passwordInputField);

//        tvSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intSignUp = new Intent(LoginActivity.this,RegisterActivity.class);
//                startActivity(intSignUp);
//            }
//        });

    }

    public void validateInputFields(View view) {
                SaveSharedPreference.setPassword(this,password.getText().toString());
                SaveSharedPreference.setDNI(this,id.getText().toString());
        boolean allFields = false;
        if (id.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
            Toast.makeText(this, "Llene todos los campos.", Toast.LENGTH_LONG).show();
            allFields = true;
        }
        if(id.getText().toString().length() < 9 && !allFields){
            Toast.makeText(this, "Su id debe tener longitud igual a 9.", Toast.LENGTH_LONG).show();
        }
        if(password.getText().toString().length() < 8 || password.getText().toString().length() > 10){
            Toast.makeText(this, "Su constraseña debe tener mínimo mínimo 8 caracteres y máximo 10.", Toast.LENGTH_LONG).show();
        }
        else{

            Intent intSignUp = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intSignUp);
        }


    }

