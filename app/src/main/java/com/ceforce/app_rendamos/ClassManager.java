package com.ceforce.app_rendamos;

import android.util.Log;
import android.view.textclassifier.TextLinks;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ClassManager {

    String url = "http://192.168.128.23:7321/ApiServer/api/ClassRoom/GetMyClassRoom";

    public void getMyClassRoom(String auth){

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", auth)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.v("Error: ", "Request Failure\n"+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.v("Success!", response.body().string());

            }
        });
    }

}
