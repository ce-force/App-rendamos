package com.ceforce.app_rendamos.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ceforce.app_rendamos.ASQ3TestActivity;
import com.ceforce.app_rendamos.R;
import com.ceforce.app_rendamos.RecyclerView.RecyclerViewAdapter;
import com.ceforce.app_rendamos.Utilities.DateUtilities;
import com.ceforce.app_rendamos.asq3data.HistoricalResults;
import com.ceforce.app_rendamos.asq3data.HistoryLog;
import com.ceforce.app_rendamos.login.LoginManager;
import com.ceforce.app_rendamos.login.SaveSharedPreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UserDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        int position = RecyclerViewAdapter.ind;

        JSONObject LoginData = null;
        JSONObject UserInfo = null;

        String answer = SaveSharedPreference.getUserData(this);

        JSONObject answerJSON = null;
        try {
            answerJSON = new JSONObject(answer);
            UserInfo = new JSONObject(answerJSON.getString("UserInfo"));
            LoginData = new JSONObject(answerJSON.getString("LoginData"));


            LoginManager logManager = new LoginManager();
            User teacher = new User(UserInfo.getInt("uid"), UserInfo.getString("givenName"), UserInfo.getString("email"), LoginData.getString("access_token"));

            String[][] matrix = logManager.give_my_kids(teacher.getAccess_token());

            if(matrix == null){

                Log.e("MatError", "Null mat");

            }

            TextView idText = findViewById(R.id.userName);
            TextView name = findViewById(R.id.asq3Name);
            TextView dateOfBirth = findViewById(R.id.asq3Birth);
            TextView premWeeks = findViewById(R.id.premWeeks);
            TextView examType = findViewById(R.id.examType);
            TextView examDate = findViewById(R.id.examDate);

            Log.d("MatTest", matrix[position][0]);

            idText.setText(matrix[position][0]);
            name.setText(matrix[position][1]);
            dateOfBirth.setText(matrix[position][2].split("T")[0]);
            premWeeks.setText(matrix[position][3]);

            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String today = df.format(c);

            examDate.setText(today);

            String exam = new DateUtilities().getASQ(matrix[position][2], Integer.parseInt(matrix[position][3]));

            examType.setText(exam);

            Button button = findViewById(R.id.backButton);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onApply(v);

                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onApply(View view){
        Intent mIntent = new Intent(this, ASQ3TestActivity.class);
        Bundle mBundle = new Bundle();
        int[][] arr=new int[5][6];
        arr[0][1]=5;
        mBundle.putSerializable("matrix", arr);
        mIntent.putExtras(mBundle);
        startActivity(mIntent);
    }

    public void toHistorial(View view){
        Intent intent = new Intent(this, HistoryLog.class);
        startActivity(intent);

    }


    public void toEditHistorial(View view){
//        Intent intent = new Intent(this, EditHistory.class);
//        startActivity(intent);
    }

}
