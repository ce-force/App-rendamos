package com.ceforce.app_rendamos.asq3data;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import android.util.Log;
import android.view.View;

import com.ceforce.app_rendamos.R;
import com.ceforce.app_rendamos.RecyclerView.RecyclerViewAdapter;
import com.ceforce.app_rendamos.Utilities.DateUtilities;
import com.ceforce.app_rendamos.login.LoginManager;
import com.ceforce.app_rendamos.login.SaveSharedPreference;
import com.ceforce.app_rendamos.user.User;
import com.ceforce.app_rendamos.user.UserDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HistoricalResults extends AppCompatActivity {
    TableLayout T;
    int[] puntajes;
    String[] Areas={
            "Comunicación",
            "Motora Fina",
            "Motora Gruesa",
            "Resolución de problemas",
            "Socio-individual"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical_results);
        T=findViewById(R.id.table);

        ArrayList<Integer> intList = new ArrayList<>();
        LoginManager loginManager = new LoginManager();

        JSONObject LoginData = null;
        JSONObject UserInfo = null;
        try {

            Bundle bundle = getIntent().getExtras();

            String studentId = (String) bundle.get("studentId");

            String answer = SaveSharedPreference.getUserData(this);

            JSONObject answerJSON = new JSONObject(answer);

            UserInfo = new JSONObject(answerJSON.getString("UserInfo"));
            LoginData = new JSONObject(answerJSON.getString("LoginData"));


            LoginManager logManager = new LoginManager();
            User teacher = new User(UserInfo.getInt("uid"), UserInfo.getString("givenName"), UserInfo.getString("email"), LoginData.getString("access_token"));
            intList = loginManager.getGlobalScores(teacher.getAccess_token(), studentId);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        puntajes=new int[5];

        puntajes[0] = intList.get(0);
        puntajes[1] = intList.get(1);
        puntajes[2] = intList.get(2);
        puntajes[3] = intList.get(3);
        puntajes[4] = intList.get(4);

        createTable();





    }

    public void createRows() {
        TableLayout tl = (TableLayout) findViewById(R.id.table);
        for (int i= 0;i<5;i++) {
            TableRow tr = new TableRow(this);
            TextView T1 = new TextView(this);
            T1.setText(Areas[i]);
            TextView T2 = new TextView(this);
//            T2.setText(""+Limites[i]);
//            TextView T3 = new TextView(this);
//            T3.setText(""+array[i]);
//            if (array[i]>Limites[i]){
//                T3.setTextColor(Color.BLUE);
//            }
//            else{
//                T3.setTextColor(Color.RED);
//            }
            tr.addView(T1);
            tr.addView(T2);
//            tr.addView(T3);
            tl.addView(tr);

        }
    }



    public void toUserHistory(View view){
        Intent intent = new Intent(this, UserDetails.class);
        startActivity(intent);
    }

    void createTable(){
        for (int i=0;i<5;i++){
            TableRow tr= new TableRow(this);
            TextView T1=new TextView(this);
            T1.setText(Areas[i]);
            tr.addView(T1);
            TextView T2=new TextView(this);
            T2.setText(""+puntajes[i]);
            tr.addView(T2);
            T.addView(tr);
        }
    }


}
