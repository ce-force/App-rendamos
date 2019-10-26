package com.ceforce.app_rendamos;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Response";
    Button loadApi, postReq;
    JSONObject answer;
    LoginManager logManager = new LoginManager();
    Boolean userExists = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadApi = findViewById(R.id.loadApi);
        postReq = findViewById(R.id.postReq);

        postReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    userExists = userRequest("1111", "Te$t1234");

                    Log.v("Exists: ", userExists.toString());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public boolean userRequest(String user, String pass) throws IOException {

        answer = logManager.getUserData(user, pass);

        if (answer != null){

            return true;

        }
        else
        {

            return false;

        }


    }
}