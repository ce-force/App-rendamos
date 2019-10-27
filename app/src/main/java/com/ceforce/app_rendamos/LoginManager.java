package com.ceforce.app_rendamos;

import android.util.Log;
import android.widget.Button;

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

public class LoginManager {


    private static final String TAG = "Response";
    Button loadApi, postReq;
    private static final String[] respuesta = {""};
    JSONObject answer;
    JSONArray answerRequest;


    public JSONObject getUserData(String user, String pass){

        try {
            post_Login(user, pass);
            Thread.sleep(1000);
        }
        catch (Exception e){

            //pass

        }

        return answer;
    }

    public String [][] give_my_kids(String access_token) throws InterruptedException, JSONException, IOException {
        JSONArray kids=this.getHttpResponse("http://192.168.128.23:7321/ApiServer/api/Student/GetMyStudents",access_token);
        String[][] matrix = new String[kids.length()][4];
        Log.e("Size de kids",String.valueOf(kids.length()));
        for(int r=0;r<kids.length(); r++) {
            JSONObject kid=kids.getJSONObject(r);
            matrix[r][0]= String.valueOf(kid.getInt("id"));
            matrix[r][1]=kid.getString("firstName")+" "+ kid.getString("lastName");
            matrix[r][2]=kid.getString("dob");
            matrix[r][3]=kid.getString("earlyBirthAmount");
        }
        return matrix;
    }

    public JSONArray getHttpResponse(String urlEntry, String access_token ) throws IOException, InterruptedException, JSONException {


        String url = urlEntry;

        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer "+access_token)
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

                Log.e("Respuesta", mMessage);
                respuesta[0]=mMessage;


            }
        });
        Thread.sleep(1000);
        Log.e("RRRR",respuesta[0]);
        answerRequest=new JSONArray(respuesta[0]);
        return answerRequest;
    }

    public JSONObject post_Login(String user, String pass) throws IOException, InterruptedException {


        //FORMAT OF THE JSON
//    {
//        "LoginData": {
//        "access_token": "eyJhbGciOiJSUzI1NiIsImtpZCI6IjlCQ0U2OTc1MDUzMkU3QjNEOUU3MkU4ODcwOTZENDk2RUEyNzdBOEIiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJtODVwZFFVeTU3UFo1eTZJY0piVWx1b25lb3MifQ.eyJuYmYiOjE1NzIxMjU5MTgsImV4cCI6MTU3MjEyOTUxOCwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo3MzIxL2F1dGhzZXJ2ZXIiLCJhdWQiOlsiaHR0cDovL2xvY2FsaG9zdDo3MzIxL2F1dGhzZXJ2ZXIvcmVzb3VyY2VzIiwiRGVodmlBUEkiXSwiY2xpZW50X2lkIjoiZGVodmlfYXBpIiwic3ViIjoiMSIsImF1dGhfdGltZSI6MTU3MjEyNTkxOCwiaWRwIjoibG9jYWwiLCJnaXZlbl9uYW1lIjoiWW9uYXRhbiBMZWl0b24iLCJlbWFpbCI6ImphcWxvdWkxQGdtYWlsLmNvbSIsInJvbGUiOiI1Iiwiem9uZWluZm8iOiIxIiwic2NvcGUiOlsiZW1haWwiLCJvcGVuaWQiLCJyb2xlIiwiYXBpLnJlc291cmNlIiwib2ZmbGluZV9hY2Nlc3MiXSwiYW1yIjpbInB3ZCJdfQ.nHlaHziElKhniZiAGnXUAoPtwcR8fOe-o064l3AvTWJdCgB_idOAXk-_Yyax19YQVXFOam4Ak1u4vYmfveIITaBBfdIw6Z9GV2-IhGyEjplXHowLClVy24g1QtmbSMXqa92aGMbwsKWCL1iShU_hHcatRS7Bpb8hBTU56JdVcBDe7e-YUYnGCB7KTa1SU006uqGAt9gq5VVnoIkezh9WFEIZY1p8r4Xjo_1A8myACZVtT3JiF5pXTr-bp3h_eDtfZZq6MSiOM2LOoSCbKGo3FI-2lryPIG9qoZT8uSVVvblDMVrjZ9RMMmXjrLFWkSkb0k04mZ2i8Ml9jXt3Jg8DuQ",
//                "expires_in": 3600,
//                "token_type": "Bearer",
//                "refresh_token": "030b50e46869f134679374f6cc55547c8b042d4e81f85f01a4b38a49a1d9ce25",
//                "scope": "api.resource email offline_access openid role"
//    },
//        "UserInfo": {
//        "uid": 1,
//                "givenName": "Yonatan Leiton",
//                "email": "jaqloui1@gmail.com",
//                "role": "Profesor"
//    }
//    }

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
        Thread.sleep(1000);
        return answer;

    }


}
