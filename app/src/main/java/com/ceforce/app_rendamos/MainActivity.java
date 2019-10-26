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
    private static final String[] respuesta = {""};
      JSONObject answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadApi = (Button)findViewById(R.id.loadApi);
        postReq = (Button)findViewById(R.id.postReq);

        loadApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getHttpResponse();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        postReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    postRequest();
                    Thread.sleep(1000);
                    Toast toast = Toast.makeText(getApplicationContext(),
                            answer.toString(),
                            Toast.LENGTH_SHORT);

                    toast.show();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void getHttpResponse() throws IOException {

        String url = "http://www.mocky.io/v2/597c41390f0000d002f4dbd1";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

//        Response response = client.newCall(request).execute();
//        Log.e(TAG, response.body().string());

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String mMessage = e.getMessage().toString();
                Log.w("failure Response", mMessage);
                //call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String mMessage = response.body().string();
                Log.e(TAG, mMessage);
            }
        });
    }

    public void postRequest() throws IOException {

        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        String url = "http://192.168.128.23:7321/ApiServer/api/LogIn";


        OkHttpClient client = new OkHttpClient();

        final JSONObject postdata = new JSONObject();
        try {
            postdata.put("username", "1111");
            postdata.put("password", "Te$t1234");
        } catch(JSONException e){
            // TODO Auto-generated catch block
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
                Log.w("failure Response", mMessage);
                //call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                 String mMessage = response.body().string();
                try {
                    answer=new JSONObject(mMessage);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                respuesta[0] = mMessage;
                Log.e(TAG, mMessage);
                Log.e("Response 2", respuesta[0]);

                //Toast.makeText(getApplicationContext(),mMessage,Toast.LENGTH_SHORT).show();
            }
        });
        Log.e("FINAL","HOla como etas"+ respuesta[0]);
//
//        //Toast toast = Toast.makeText(getApplicationContext(),respuesta[0].substring(0,10)
//                ,
//                Toast.LENGTH_SHORT);
//
//        toast.show();



    }
}