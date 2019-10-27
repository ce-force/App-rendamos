package com.ceforce.app_rendamos.asq3data;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.ceforce.app_rendamos.R;

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
        puntajes=new int[5][6];
        createTable();



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
