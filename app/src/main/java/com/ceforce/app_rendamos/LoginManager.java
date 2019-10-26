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

public class LoginManager {


    private static final String TAG = "Response";
    Button loadApi, postReq;
    private static final String[] respuesta = {""};
    JSONObject answer;

    public JSONObject getUserData(String user, String pass){

        try {
            postUserRequest(user, pass);
            Thread.sleep(1000);
        }
        catch (Exception e){

            //pass

        }

        return answer;
    }


    public void postUserRequest(String user, String pass) throws IOException {

        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        String url = "http://192.168.128.23:7321/ApiServer/api/LogIn";


        OkHttpClient client = new OkHttpClient();

        final JSONObject postdata = new JSONObject();
        try {
            postdata.put("username", user);
            postdata.put("password", pass);
        } catch(JSONException e){

            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(MEDIA_TYPE, postdata.toString());

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String mMessage = e.getMessage().toString();
                answer = null;
                Log.w("Failure Response", mMessage);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String mMessage = response.body().string();
                try {
                    answer = new JSONObject(mMessage);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("Success! Response: ", answer.toString());

            }
        });

    }


}
