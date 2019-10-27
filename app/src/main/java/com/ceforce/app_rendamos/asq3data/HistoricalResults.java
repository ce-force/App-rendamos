package com.ceforce.app_rendamos.asq3data;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import android.util.Log;
import android.view.View;

import com.ceforce.app_rendamos.R;
import com.ceforce.app_rendamos.RecyclerView.RecyclerViewAdapter;
import com.ceforce.app_rendamos.Utilities.DateUtilities;
import com.ceforce.app_rendamos.user.UserDetails;

import org.json.JSONObject;

import java.util.ArrayList;

public class HistoricalResults extends AppCompatActivity {
    TableLayout T;
    int[][] puntajes;
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
        createTable();


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
            int s=0;
            for (int j = 1;j<7;j++){
                TextView T2=new TextView(this);
                T2.setText(""+puntajes[i][j-1]);
                s+=puntajes[i][j-1];
                tr.addView(T2);
            }
            TextView T3=new TextView(this);
            T3.setText(""+s);
            tr.addView(T3);
            T.addView(tr);
        }
    }


}
