package com.ceforce.app_rendamos.asq3data;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ceforce.app_rendamos.ASQ3TestActivity;
import com.ceforce.app_rendamos.R;
import com.ceforce.app_rendamos.RecyclerView.RecyclerViewAdapter;
import com.ceforce.app_rendamos.login.LoginManager;
import com.ceforce.app_rendamos.login.SaveSharedPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class HistoryLog extends AppCompatActivity {

    private static final String TAG = "HistoryLog";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private String ID;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_log);
        ID=getIntent().getStringExtra("ID");

        final ListView list = findViewById(R.id.list);
        arrayList   = new ArrayList<>();
        Log.d(TAG, "started: ok");
        try {
            initBitmaps();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, arrayList);
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedItem=(String) list.getItemAtPosition(position);
                Toast.makeText(getBaseContext(),clickedItem,Toast.LENGTH_LONG).show();
                Intent mIntent = new Intent(getBaseContext(), ASQ3TestActivity.class);
                Bundle mBundle = new Bundle();
                int[][] arr= new int[0][];
                try {
                    arr = Recopilar(position);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mBundle.putSerializable("matrix", arr);
                mIntent.putExtras(mBundle);
                startActivity(mIntent);
            }
        });



    }

    int[][] Recopilar(int j) throws JSONException, IOException, InterruptedException {
        int[][] arr=new int[5][6];

        String answer = SaveSharedPreference.getUserData(this);

        JSONObject answerJSON = null;
        try {
            answerJSON = new JSONObject(answer);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("NETXD","hiii"+answerJSON.toString());

        JSONObject LoginData = new JSONObject(answerJSON.getString("LoginData"));

        Log.d("NETXD","hiii"+LoginData.getString("access_token"));
        LoginManager L = new LoginManager();
        String[][] tmp=L.getAttendanceStudent(LoginData.getString("access_token"),ID);
        Log.d("NETXD","hiii");

        Log.d("NETXD2",tmp[j][1]);

        JSONArray result=new JSONArray(tmp[j][1]);



//        for (int k = 0; k<5 ; k++){
//            JSONArray hola= r;
//            for (int m=0; m<6;m++){
//                arr[k][m]=
//
//            }
//
//        }



        return arr;
    }

    private void initBitmaps() throws JSONException, IOException, InterruptedException {

        Log.d(TAG, "init bitmaps: preparing");

        String answer = SaveSharedPreference.getUserData(this);

        JSONObject answerJSON = null;
        try {
            answerJSON = new JSONObject(answer);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("NETXD","hiii"+answerJSON.toString());

        JSONObject LoginData = new JSONObject(answerJSON.getString("LoginData"));

        Log.d("NETXD","hiii"+LoginData.getString("access_token"));
        LoginManager L = new LoginManager();
        String[][] tmp=L.getAttendanceStudent(LoginData.getString("access_token"),ID);
        Log.d("NETXD","hiii");

        for (int j = 0; j<tmp.length;j++){
            Log.d("NETXD",tmp[j][0]);
            arrayList.add(tmp[j][0]);
        }


        Log.d(TAG, "init bitmaps: done");

    }

}
