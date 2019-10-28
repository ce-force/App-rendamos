package com.ceforce.app_rendamos.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ceforce.app_rendamos.MainActivity;
import com.ceforce.app_rendamos.R;
import com.ceforce.app_rendamos.ui.home.HomeFragment;
import com.ceforce.app_rendamos.user.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

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

//        String access_token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjlCQ0U2OTc1MDUzMkU3QjNEOUU3MkU4ODcwOTZENDk2RUEyNzdBOEIiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJtODVwZFFVeTU3UFo1eTZJY0piVWx1b25lb3MifQ.eyJuYmYiOjE1NzIyMDExMjQsImV4cCI6MTU3MjIwNDcyNCwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo3MzIxL2F1dGhzZXJ2ZXIiLCJhdWQiOlsiaHR0cDovL2xvY2FsaG9zdDo3MzIxL2F1dGhzZXJ2ZXIvcmVzb3VyY2VzIiwiRGVodmlBUEkiXSwiY2xpZW50X2lkIjoiZGVodmlfYXBpIiwic3ViIjoiMSIsImF1dGhfdGltZSI6MTU3MjIwMTEyNCwiaWRwIjoibG9jYWwiLCJnaXZlbl9uYW1lIjoiWW9uYXRhbiBMZWl0b24iLCJlbWFpbCI6ImphcWxvdWkxQGdtYWlsLmNvbSIsInJvbGUiOiI1Iiwiem9uZWluZm8iOiIxIiwic2NvcGUiOlsiZW1haWwiLCJvcGVuaWQiLCJyb2xlIiwiYXBpLnJlc291cmNlIiwib2ZmbGluZV9hY2Nlc3MiXSwiYW1yIjpbInB3ZCJdfQ.JA5RyKidMZB9BC9ye-PLQJlZhJg_mNq0rGCg4gbqW2EKI39cvV5ep0EFxllQkvAMNp9vlSz76bRMJZkJqFtBLaucoBnq5vvejiBx3YFXyb4fIGVGIQ7Y2f7aiYen036hd7Bo1iPPeLr-fGwEu17VUJv2GVpdrgz8nsl1w7nx7PjEchEgrkq1Fdvh5FVtei-SGyBYwHakvwbN0ISkLBzZqpDdUVgdaZhBjimCMzLwLb4-ZqQ8i3S76z1oGtqbPtggUvuQ-uFo54VAKjBq2wtoETKHtPtMaCV8T5e6l6wBMA-8GQUxSljDG4n4-34uaghycbbHucqxPDbmOOIOPOTMug";
//
//        ArrayList<Integer> ints = new LoginManager().getGlobalScores(access_token, "11");

//        Log.d("FINALVALUES", ints.toString());

    }


}


