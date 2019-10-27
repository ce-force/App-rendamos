package com.ceforce.app_rendamos.asq3data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.ceforce.app_rendamos.R;

import java.lang.reflect.Array;

public class TestResults extends AppCompatActivity {

    int[] array;
    String[] Areas={
            "Comunicación",
            "Motora Fina",
            "Motora Gruesa",
            "Resolución de problemas",
            "Socio-individual"
    };
    int[] Limites={
            11,11,22,11,22
    };
    TextView[] Semaforos=new TextView[5];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_results);

        array = getIntent().getIntArrayExtra("Results");
        for (int k=0;k<5;k++) {
            Log.d("status2", "hola");
        }
        createRows();
    }

    public void createRows() {
        TableLayout tl = (TableLayout) findViewById(R.id.table);
        for (int i= 0;i<5;i++){
            TableRow tr = new TableRow(this);
            TextView T1 = new TextView(this);
            T1.setText(Areas[i]);
            TextView T2 = new TextView(this);
            T2.setText(""+Limites[i]);
            TextView T3 = new TextView(this);
            T3.setText(""+array[i]);
            if (array[i]>Limites[i]){
                T3.setTextColor(Color.BLUE);
            }
            else{
                T3.setTextColor(Color.RED);
            }
            tr.addView(T1);
            tr.addView(T2);
            tr.addView(T3);
            tl.addView(tr);
            Semaforos[i]=T3;
        }

    }

}

