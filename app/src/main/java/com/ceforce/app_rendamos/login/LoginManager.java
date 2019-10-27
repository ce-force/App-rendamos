package com.ceforce.app_rendamos.login;

import android.util.Log;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginManager {
    //JSONObject answer = new JSONObject();
    Boolean exists = false;
    Boolean HaveKids=false;

    private static final String TAG = "Response";
    Button loadApi, postReq;
    private static final String[] respuesta = {""};
    JSONObject answer;
    JSONArray answerRequest;
    String answerTest;

    public String[][] getAttendanceStudent(String access_token, String idStudent) throws InterruptedException, JSONException, IOException {
        JSONArray antendaces=this.getHttpResponse(" http://192.168.128.23:7321/ApiServer/api/Attendance/GetByStudentId?studentId="+idStudent,access_token);
        String[][] matrix = new String[antendaces.length()][2];
        for(int r=0;r<antendaces.length(); r++) {
            JSONObject antendance=antendaces.getJSONObject(r);
            matrix[r][0]= String.valueOf(antendance.getInt("id"));
            matrix[r][1]= String.valueOf(antendance.getInt("formId"));
        }
        return matrix;

    }


    public ArrayList<Integer> getASQResults(String access_token, String idStudent){
        ArrayList<Integer> indList = new ArrayList<>();
        try {
            String[][] matrix = getAttendanceStudent(access_token, idStudent);

            for (int i = 0; i < matrix.length; i++) {
                indList.add(Integer.parseInt(matrix[i][0]));
            }

            return indList;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int manageAreaResults(JSONArray array){

        int res = 0;

        for (int i = 0; i < array.length(); i++) {

            try {
                int val = ((JSONObject) array.get(i)).getInt("value");

                res += val;

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        return res;
    }

    public ArrayList<Integer> manageResults(ArrayList<JSONObject> values){

        int res1 = 0 , res2 = 0, res3 = 0, res4 = 0, res5 = 0, res6 = 0;

        for (JSONObject obj:values) {

            try {
                JSONArray innerObject = (JSONArray) obj.get("resultList");

                for (int i = 0; i < innerObject.length(); i++) {

                    JSONObject resultList = (JSONObject) innerObject.get(i);

                    JSONArray results = (JSONArray) resultList.get("results");
                    int areaId = resultList.getInt("areaId");
                    int holder = manageAreaResults(results);

                    if(areaId == 1){
                        res1+=holder;
                    }
                    else if (areaId == 2){

                        res2+=holder;

                    }
                    else if (areaId == 3){

                        res3+=holder;
                    }
                    else if (areaId == 4){

                        res4+=holder;
                    }
                    else if (areaId == 5){

                        res5+=holder;
                    }
                    else if (areaId == 6){

                        res6+=holder;
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        ArrayList<Integer> ints = new ArrayList<>();

        ints.add(res1);
        ints.add(res2);
        ints.add(res3);
        ints.add(res4);
        ints.add(res5);
        ints.add(res6);


        return ints;





    }

    public ArrayList<JSONObject> getResultsFromAttendance(String access_token, ArrayList<Integer> inds){

        ArrayList<JSONObject> results = new ArrayList<>();

        for (Integer ind:inds) {

            String URL = "http://192.168.128.23:7321/ApiServer/api/Result/GetResultByAttendanceId?attendanceId=" + Integer.toString(ind);
            try {
                JSONObject jsonObject = getHttpResponseObject(URL, access_token);

                results.add(jsonObject);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        Log.d("RESULTS", results.toString());

        return results;


    }


    public void postAddAttendance(JSONObject data,String token){

        Log.d("POST_ATTENDANCE", "init");

        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        String url = "http://192.168.128.23:7321/ApiServer/api/Attendance/AddAttendance";


        OkHttpClient client = new OkHttpClient();

        JSONObject postdata = data;

        RequestBody body = RequestBody.create(MEDIA_TYPE, postdata.toString());

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer "+token)

                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String mMessage = e.getMessage().toString();
                Log.d("Failure Response", mMessage);

                exists = false;
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String mMessage = response.body().string();
                Log.d("Exists! Response ", exists.toString());
                try {
                    answer = new JSONObject(mMessage);
                    exists = true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public ArrayList<Integer> getGlobalScores(String access_token, String studentId){

        ArrayList<Integer> finalList = new LoginManager().getASQResults(access_token, studentId);

        ArrayList<Integer> ints = new LoginManager().manageResults(new LoginManager().getResultsFromAttendance(access_token, finalList));

        return ints;
    }

    public JSONObject getUserData(String user, String pass){

        try {
            post_Login(user, pass);
            Thread.sleep(500);
        }
        catch (Exception e){

        }

        return answer;
    }

    public static String join(String [] list, String delim) {

        StringBuilder sb = new StringBuilder();

        String loopDelim = "";

        for (int i = 0; i < list.length; i++) {

            sb.append(loopDelim);
            sb.append(list[i]);

            loopDelim = delim;
        }

        return sb.toString();
    }

    public int getId(String access_token) throws InterruptedException, JSONException, IOException {
        JSONArray antendaces=this.getHttpResponse("http://192.168.128.23:7321/ApiServer/api/Attendance/GetAllAttendances",access_token);
        if(antendaces==null){
            Log.e("NO ID ULTIMO","no hay");
            return 0;


        }
        else{
            JSONObject ultimo=antendaces.getJSONObject(antendaces.length()-1);
            return  1+ Integer.parseInt(ultimo.getString("id"));
        }
    }

    public JSONObject postAttendance(String access_token, String formName, int studentId, int appId){
        try {

            String form  = getFormFromName(access_token, formName);

            JSONObject formObject = new JSONObject(form);

            JSONObject attendance = new JSONObject();

            int id = getId(access_token);

            attendance.put("id", id);

            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz");
            String today = df.format(c);

            attendance.put("date", today);

            attendance.put("formId", formObject.get("id"));

            attendance.put("studentId", studentId);
            attendance.put("applicatorId", appId);

            attendance.put("status", "Finished");
            attendance.put("form", formObject);


            Log.d("POST_ATTENDANCE", "Before");

            postAddAttendance(attendance,access_token);

            return attendance;
        }
        catch (Exception e){

            e.printStackTrace();
            return null;
        }
    }

    public String getFormFromName(String access_token, String name){


        String exten = join(name.split(" "), "%20");


        String url = "http://192.168.128.23:7321/ApiServer/api/Form/GetByName?formHeaderName="+ exten;

        try {

            JSONObject form = getHttpResponseObject(url, access_token);

            return form.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String [][] give_my_kids(String access_token) throws InterruptedException, JSONException, IOException {
        JSONArray kids=this.getHttpResponse("http://192.168.128.23:8141/ApiServer/api/Student/GetMyStudents",access_token);
        if(kids!=null){
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
        else{return null;}

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
        Thread.sleep(500);
        if(respuesta[0]!=""){
            Log.e("Si tiene kids",respuesta[0]);
            answerRequest=new JSONArray(respuesta[0]);
            return answerRequest;

        }
        else{
            Log.e("No hay Kids","NO HAY");

            return null;
        }

    }

    public JSONObject getHttpResponseObject(String urlEntry, String access_token) throws IOException, InterruptedException, JSONException {

        String url = urlEntry;

        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer "+access_token)
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
                    answer = new JSONObject(mMessage);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        Thread.sleep(500);
        if(answer != null){
            return answer;

        }
        else{
            Log.e("No hay Kids","NO HAY");

            return null;
        }

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

        JSONObject postdata = new JSONObject();
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
                exists = false;
                Log.w("Failure Response", mMessage);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String mMessage = response.body().string();
                try {
                    answer = new JSONObject(mMessage);
                    exists = true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("Exists! Response ", exists.toString());

            }
        });
        Thread.sleep(500);
        return answer;

    }

    public boolean userRequest(String user, String pass) throws IOException {

        this.getUserData(user,pass);

        return exists;

    }

}
