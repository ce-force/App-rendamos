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

import com.ceforce.app_rendamos.R;
import com.ceforce.app_rendamos.RecyclerView.RecyclerViewAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


//        final TextView textView = root.findViewById(R.id.text_home);
////        homeViewModel.getText().observe(this, new Observer<String>() {
////            @Override
////            public void onChanged(@Nullable String s) {
////                textView.setText(s);
////            }
////        });
        return root;
    }


    private void matToRecyclerView(String mat[][], int n, View root){

        ArrayList<String> leftText = new ArrayList<>();
        ArrayList<String> rightText = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            leftText.add(mat[i][0]);
            rightText.add(mat[i][1]);

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