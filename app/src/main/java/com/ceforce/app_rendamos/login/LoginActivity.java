package com.ceforce.app_rendamos.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ceforce.app_rendamos.LoginManager;
import com.ceforce.app_rendamos.MainActivity;
import com.ceforce.app_rendamos.R;
import com.ceforce.app_rendamos.ui.home.HomeFragment;
import com.ceforce.app_rendamos.user.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private EditText id, password;
    LoginManager logManager = new LoginManager();
    User teacher;

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

    public void validateInputFields(View view) throws IOException, JSONException {

        if (id.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor, llene todos los campos.", Toast.LENGTH_SHORT).show();
        } else if (false && id.getText().toString().length() == 9) {
            Toast.makeText(this, "Su id debe tener longitud igual a 9.", Toast.LENGTH_SHORT).show();
        } else if (password.getText().toString().length() < 8 || password.getText().toString().length() > 10) {
            Toast.makeText(this, "Su constraseña debe tener mínimo mínimo 8 caracteres y máximo 10.", Toast.LENGTH_SHORT).show();
        } else if (!logManager.userRequest(id.getText().toString(), password.getText().toString())) {
            Toast.makeText(this, "Su DNI o contraseña son incorrectos", Toast.LENGTH_SHORT).show();
        } else {
            SaveSharedPreference.setPassword(this, password.getText().toString());
            SaveSharedPreference.setDNI(this, id.getText().toString());
            Intent intSignUp = new Intent(LoginActivity.this, MainActivity.class);


            JSONObject answer = null;
            try {
                answer = logManager.post_Login(id.getText().toString(), password.getText().toString());
                SaveSharedPreference.setUserData(this,answer.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }




            startActivity(intSignUp);


            }


        }


}


