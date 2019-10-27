package com.ceforce.app_rendamos.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ceforce.app_rendamos.LoginManager;
import com.ceforce.app_rendamos.R;
import com.ceforce.app_rendamos.RecyclerView.RecyclerViewAdapter;
import com.ceforce.app_rendamos.Utilities.DateUtilities;
import com.ceforce.app_rendamos.login.SaveSharedPreference;
import com.ceforce.app_rendamos.user.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);



        JSONObject LoginData = null;
        JSONObject UserInfo = null;
        try {

            Log.d("AAAAA",  SaveSharedPreference.getUserData(getContext()));

            String answer = SaveSharedPreference.getUserData(getContext());

            JSONObject answerJSON = new JSONObject(answer);

            UserInfo = new JSONObject(answerJSON.getString("UserInfo"));
            LoginData = new JSONObject(answerJSON.getString("LoginData"));


            LoginManager logManager = new LoginManager();
            User teacher = new User(UserInfo.getInt("uid"), UserInfo.getString("givenName"), UserInfo.getString("email"), LoginData.getString("access_token"));

            String[][] matrix = logManager.give_my_kids(teacher.getAccess_token());

            if (matrix == null) {
                Log.e("NO", "NO TIENE KIDS ESTE MEN");


                TextView leftLabel = root.findViewById(R.id.anchorText);
                leftLabel.setText("");
                TextView rightLabel = root.findViewById(R.id.sideText);
                rightLabel.setText("No hay alumnos para este profesor");



            } else {
                matToRecyclerView(matrix, matrix.length, root);

                for (int r = 0; r < matrix.length; r++) {
                    Log.e("id", matrix[r][0]);
                    Log.e("Nombre", matrix[r][1]);
                    Log.e("dob", matrix[r][2]);
                    Log.e("earlyBirth", matrix[r][3]);

                }
            }



    //        final TextView textView = root.findViewById(R.id.text_home);
    ////        homeViewModel.getText().observe(this, new Observer<String>() {
    ////            @Override
    ////            public void onChanged(@Nullable String s) {
    ////                textView.setText(s);
    ////            }
    ////        });
        } catch (Exception e) {
            Log.d("AAAAA",  e.getMessage());

            e.printStackTrace();
        }
        return root;
    }


    private void matToRecyclerView(String mat[][], int n, View root){
        //Log.d("Login >> Mat Load", String.valueOf(n));

        ArrayList<String> leftText = new ArrayList<>();
        ArrayList<String> rightText = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            leftText.add(mat[i][1]);

            DateUtilities dateUtilities = new DateUtilities();

            String ASQ = dateUtilities.getASQ(mat[i][2], Integer.parseInt(mat[i][3]));

            rightText.add(ASQ);

        }

        initRecyclerView(leftText, rightText, root);

    }


    private void initRecyclerView(ArrayList<String> leftTexts, ArrayList<String> rightTexts, View root){

        Log.d("Login >> RecyclerView", "initRecyclerView: init recyclerView");
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recycler);
        Log.d("Recycler","Loaded");
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), leftTexts, rightTexts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


    }


}