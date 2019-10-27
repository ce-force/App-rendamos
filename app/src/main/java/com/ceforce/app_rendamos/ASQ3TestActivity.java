package com.ceforce.app_rendamos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.ceforce.app_rendamos.RecyclerView.RecyclerViewAdapter;
import com.ceforce.app_rendamos.Utilities.DateUtilities;
import com.ceforce.app_rendamos.asq3data.TestResults;
import com.ceforce.app_rendamos.login.LoginActivity;
import com.ceforce.app_rendamos.login.SaveSharedPreference;
import com.ceforce.app_rendamos.ui.DatePickerFragment;
import com.ceforce.app_rendamos.user.User;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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


    String[] Areas={
            "Comunicación",
            "Motora Fina",
            "Motora Gruesa",
            "Resolución de problemas",
            "Socio-individual"
    };
    int AreaActual=0;
    int[][] punt = new int[5][6];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asq3_test);

        int position = RecyclerViewAdapter.ind;

        JSONObject LoginData = null;
        JSONObject UserInfo = null;

        String answer = SaveSharedPreference.getUserData(this);

        JSONObject answerJSON = null;
        try {
            answerJSON = new JSONObject(answer);
            UserInfo = new JSONObject(answerJSON.getString("UserInfo"));
            LoginData = new JSONObject(answerJSON.getString("LoginData"));


            com.ceforce.app_rendamos.LoginManager logManager = new com.ceforce.app_rendamos.LoginManager();
            User teacher = new User(UserInfo.getInt("uid"), UserInfo.getString("givenName"), UserInfo.getString("email"), LoginData.getString("access_token"));

            String[][] matrix = logManager.give_my_kids(teacher.getAccess_token());

            if(matrix == null){

                Log.e("MatError", "Null mat");

            }

            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String today = df.format(c);


            String exam = new DateUtilities().getASQ(matrix[position][2], Integer.parseInt(matrix[position][3]));

        TextView asqField = findViewById(R.id.textView2);

        asqField.setText(exam);


        tv1=findViewById(R.id.textView);
        tv1.setText(today);
        area=findViewById(R.id.Area);
        T=findViewById(R.id.table);
        R1=findViewById(R.id.Rgroup1);
        R2=findViewById(R.id.Rgroup2);
        R3=findViewById(R.id.Rgroup3);
        R4=findViewById(R.id.Rgroup4);
        R5=findViewById(R.id.Rgroup5);
        R6=findViewById(R.id.Rgroup6);


        for (int i=0;i<5;i++){
            for (int j=0;j<6;j++){
                punt[i][j]=-1;
            }
        }
        Refresh();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void show(){
        for (int i=0;i<5;i++){
            for (int j=0;j<6;j++){
                Log.d("STATUSXD1",i+""+j+""+punt[i][j]);
            }
        }
    }

    void clean() {
        for (int i=0;i<5;i++){
            for (int j=0;j<6;j++){
                punt[i][j]=-1;
            }
        }
    }

    void Select(){
        RadioGroup[] RAll={R1,R2,R3,R4,R5,R6};
        for (int j=0;j<6;j++){
            int index = RAll[j].indexOfChild(findViewById(RAll[j].getCheckedRadioButtonId()));
            if (index!=-1){
                punt[AreaActual][j]=index*5;
            }
            //Log.d("STATUSXD",""+index);

        }

    }

    void Refresh(){
        RadioGroup[] RAll={R1,R2,R3,R4,R5,R6};
        area.setText(Areas[AreaActual]);
        for (int j=0;j<6;j++){
            if (punt[AreaActual][j]==-1){
                RAll[j].clearCheck();
            }
            else{
                ((RadioButton)RAll[j].getChildAt(punt[AreaActual][j]/5)).setChecked(true);
            }
            Log.d("STATUSX1",AreaActual+""+j+""+punt[AreaActual][j]);
        }

    }

    public void onNext(View view){
        if (AreaActual==4) return;
        Select();
        show();
        AreaActual++;
        Refresh();
    }

    public void onBack(View view){
        if (AreaActual==0) return;
        Select();
        show();
        AreaActual--;
        Refresh();
    }

    public void onConfirm(View view){
        clean();
        Refresh();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
// Add the buttons
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                
            }
        });
        builder.setMessage("Guardado con éxito")
                .setTitle("");

        AlertDialog dialog = builder.create();

        dialog.show();
    }

    public void onCancel(View view){
        clean();
        Refresh();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
// Add the buttons
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        builder.setMessage("¿Seguro que desea salir sin guardar?")
                .setTitle("Confirmar");

        AlertDialog dialog = builder.create();

        dialog.show();

    }

    public void onDate(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        ((DatePickerFragment) newFragment).setTv(tv1);
        newFragment.show(getSupportFragmentManager(), "datePicker");
        Log.d("STATUS", ""+ ((DatePickerFragment) newFragment).getDateString());
    }
}
