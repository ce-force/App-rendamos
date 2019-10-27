package com.ceforce.app_rendamos;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
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
    User teacher;
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
                    answer=logManager.post_Login("1111", "Te$t1234");
                    //teacher=new User(answer.getInt(""))

                    JSONObject LoginData=new JSONObject(answer.getString("LoginData"));
                    JSONObject UserInfo=new JSONObject(answer.getString("UserInfo"));
                    teacher=new User(UserInfo.getInt("uid"),UserInfo.getString("givenName"),UserInfo.getString("email"),LoginData.getString("access_token"));
                    String[][] matrix=logManager.give_my_kids(teacher.access_token);
                    for(int r=0;r<matrix.length; r++) {
                        Log.e("id",matrix[r][0]);
                        Log.e("Nombre",matrix[r][1]);
                        Log.e("dob",matrix[r][2]);
                        Log.e("earlyBirht",matrix[r][3]);




                    }

                    //                    JSONArray kids=logManager.getHttpResponse("http://192.168.128.23:7321/ApiServer/api/Student/GetMyStudents",teacher.access_token);
//                    String[][] matrix = new String[kids.length()][4];
//                    Log.e("Size de kids",String.valueOf(kids.length()));
//                    for(int r=0;r<kids.length(); r++) {
//                        JSONObject kid=kids.getJSONObject(r);
//                        matrix[r][0]= String.valueOf(kid.getInt("id"));
//                        matrix[r][1]=kid.getString("firstName")+" "+ kid.getString("lastName");
//                        matrix[r][2]=kid.getString("dob");
//                        matrix[r][3]=kid.getString("earlyBirthAmount");
//
//
//                    }
                     //Log.e("Este es el kid 1",kid1.toString());
                    Log.e("NombreKid1",matrix[1][1]);
                    Log.e("DATA",UserInfo.getString("givenName"));

                    //Log.v("Exists: ", userExists.toString());

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
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