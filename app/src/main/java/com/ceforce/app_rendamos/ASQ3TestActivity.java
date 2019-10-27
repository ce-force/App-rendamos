package com.ceforce.app_rendamos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import com.ceforce.app_rendamos.ui.DatePickerFragment;

public class ASQ3TestActivity extends AppCompatActivity {
    TextView tv1;
    TableLayout T;
    String[] Areas={
            "Comunicación",
            "Motora Fina",
            "Motora Gruesa",
            "Resolución de problemas",
            "Socio-individual"
    };
    int AreaActual=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asq3_test);

        tv1=findViewById(R.id.textView);
        T=findViewById(R.id.table);

        for (int i=0;i<Areas.length;i++){
            /*TableRow Row= new TableRow(this);
            TextView Tv=new TextView(this);
            Tv.setText(i+1);
            RadioGroup Rg=new RadioGroup(this);
            Row.addView(Tv);
            for(int j=1;j<7;j++){
                RadioButton Rb=new RadioButton(this);
                Rg.addView(Rb);
            }
            Row.addView(Rg);
            T.addView(Row);*/
        }
    }

    public void onDate(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        ((DatePickerFragment) newFragment).setTv(tv1);
        newFragment.show(getSupportFragmentManager(), "datePicker");
        Log.d("STATUS", ""+ ((DatePickerFragment) newFragment).getDateString());
    }
}
