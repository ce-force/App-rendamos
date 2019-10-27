package com.ceforce.app_rendamos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.ceforce.app_rendamos.asq3data.TestResults;
import com.ceforce.app_rendamos.ui.DatePickerFragment;

public class ASQ3TestActivity extends AppCompatActivity {
    TextView tv1;
    TextView area;
    TableLayout T;
    RadioGroup R1;
    RadioGroup R2;
    RadioGroup R3;
    RadioGroup R4;
    RadioGroup R5;
    RadioGroup R6;

    TestResults testResults = new TestResults();

    String[] Areas = {
            "Comunicación",
            "Motora Fina",
            "Motora Gruesa",
            "Resolución de problemas",
            "Socio-individual"
    };
    int AreaActual = 0;
    int[][] punt = new int[5][6];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asq3_test);

        tv1 = findViewById(R.id.textView);
        area = findViewById(R.id.Area);
        T = findViewById(R.id.table);
        R1 = findViewById(R.id.Rgroup1);
        R2 = findViewById(R.id.Rgroup2);
        R3 = findViewById(R.id.Rgroup3);
        R4 = findViewById(R.id.Rgroup4);
        R5 = findViewById(R.id.Rgroup5);
        R6 = findViewById(R.id.Rgroup6);


        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                punt[i][j] = -1;
            }
        }
        Refresh();
    }

    void show() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                Log.d("STATUSXD1", i + "" + j + "" + punt[i][j]);
            }
        }
    }

    void clean() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                punt[i][j] = -1;
            }
        }
    }

    void Select() {
        RadioGroup[] RAll = {R1, R2, R3, R4, R5, R6};
        for (int j = 0; j < 6; j++) {
            int index = RAll[j].indexOfChild(findViewById(RAll[j].getCheckedRadioButtonId()));
            if (index != -1) {
                punt[AreaActual][j] = index * 5;
            }
            //Log.d("STATUSXD",""+index);

        }
    }

    void Refresh() {
        RadioGroup[] RAll = {R1, R2, R3, R4, R5, R6};
        area.setText(Areas[AreaActual]);
        for (int j = 0; j < 6; j++) {
            if (punt[AreaActual][j] == -1) {
                RAll[j].clearCheck();
            } else {
                ((RadioButton) RAll[j].getChildAt(punt[AreaActual][j] / 5)).setChecked(true);
            }
            Log.d("STATUSX1", AreaActual + "" + j + "" + punt[AreaActual][j]);
        }

    }

    public void onNext(View view) {
        if (AreaActual == 4) return;
        Select();
        show();
        AreaActual++;
        Refresh();
    }

    public void onBack(View view) {
        if (AreaActual == 0) return;
        Select();
        show();
        AreaActual--;
        Refresh();
    }

    public void onConfirm(View view) {
        Intent intent = new Intent(this, TestResults.class);
        intent.putExtra("result", finalReturn());
        startActivity(intent);
        tableResults(punt);

    }

    public void onCancel(View view) {
        clean();
        Refresh();

    }

    public void onDate(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        ((DatePickerFragment) newFragment).setTv(tv1);
        newFragment.show(getSupportFragmentManager(), "datePicker");
        Log.d("STATUS", "" + ((DatePickerFragment) newFragment).getDateString());
    }

    int sumatoria;
    int fila, columna;


    public int tableResults(int[][] matriz) {
        sumatoria = 0;
        for (fila = 0; fila < matriz.length; fila++) {
            for (columna = 0; columna < matriz[fila].length; columna++) {
                sumatoria += matriz[fila][columna];
            }
        }
//        Log.d("SUMATORIA >> ", String.valueOf(sumatoria));
        return sumatoria;
    }


    public int sumFila(int area) {
        int s = 0;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 6; i++) {
                s += punt[area][i];
            }
        }
        return s;
    }

    public int[] finalReturn() {

        int array[] = new int[5];

        for (int i = 0; i < 5; i++) {

            array[i] = sumFila(i);
        }
        return array;
    }

}

