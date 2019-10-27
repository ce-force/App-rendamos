package com.ceforce.app_rendamos.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ceforce.app_rendamos.MainActivity;
import com.ceforce.app_rendamos.R;
import com.ceforce.app_rendamos.RecyclerView.RecyclerViewAdapter;
import com.ceforce.app_rendamos.User;

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
        Log.d("NETXD2",""
                +logManager.userRequest(id.getText().toString(), password.getText().toString())
                +id.getText().toString()
                +password.getText().toString());
        if (id.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor, llene todos los campos.", Toast.LENGTH_SHORT).show();
        }
        else if (false && id.getText().toString().length() == 9) {
            Toast.makeText(this, "Su id debe tener longitud igual a 9.", Toast.LENGTH_SHORT).show();
        }
        else if (password.getText().toString().length() < 8 || password.getText().toString().length() > 10) {
            Toast.makeText(this, "Su constraseña debe tener mínimo mínimo 8 caracteres y máximo 10.", Toast.LENGTH_SHORT).show();
        }
        else if (!logManager.userRequest(id.getText().toString(), password.getText().toString())) {
            Toast.makeText(this, "Su DNI o contraseña son incorrectos", Toast.LENGTH_SHORT).show();
        } else {
            SaveSharedPreference.setPassword(this, password.getText().toString());
            SaveSharedPreference.setDNI(this, id.getText().toString());
            Intent intSignUp = new Intent(LoginActivity.this, MainActivity.class);


            JSONObject answer = logManager.post_Login(id.getText().toString(), password.getText().toString());

            JSONObject LoginData=new JSONObject(answer.getString("LoginData"));
            JSONObject UserInfo=new JSONObject(answer.getString("UserInfo"));
            teacher=new User(UserInfo.getInt("uid"),UserInfo.getString("givenName"),UserInfo.getString("email"),LoginData.getString("access_token"));
            String[][] matrix=logManager.give_my_kids(teacher.access_token);
            if(matrix==null){
                Log.e("NO","NO TIENE KIDS ESTE MEN") ;
            }
            else{

                for(int r=0;r<matrix.length; r++) {
                    Log.e("id",matrix[r][0]);
                    Log.e("Nombre",matrix[r][1]);
                    Log.e("dob",matrix[r][2]);
                    Log.e("earlyBirht",matrix[r][3]);

                }





                startActivity(intSignUp);


        }


    }


}

