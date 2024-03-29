package com.ceforce.app_rendamos.asq3data;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ceforce.app_rendamos.R;
import com.ceforce.app_rendamos.RecyclerView.RecyclerViewAdapter;

import java.util.ArrayList;

public class RecyclerViewAdapterUser<TAG> extends RecyclerView.Adapter<RecyclerViewAdapterUser.ViewHolder>{


    private static final Object TAG = "RecyclerViewAdapter";

    public static int ind = 0;

    private Context context;

    private ArrayList<String> leftText;
    private ArrayList<String> rightText;
    private Context mContext;
    private ViewHolder holder;
    private int position;

    public RecyclerViewAdapterUser(Context mContext, ArrayList<String> leftText, ArrayList<String> rightText) {
        this.leftText = leftText;
        this.rightText = rightText;
        this.mContext = mContext;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.container_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d((String) TAG, "onBindViewHolder: called");


//        holder.leftText.setText(this.leftText.get(position));
//        holder.rightText.setText(this.rightText.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ind = position;

                Intent intent = new Intent(v.getContext(), HistoricalResults.class);
                v.getContext().startActivity(intent);
                
                Log.d("Ind", String.valueOf(position));

            }
        });
    }


    @Override
    public int getItemCount() {
        return leftText.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView leftText;
        TextView rightText;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            leftText = itemView.findViewById(R.id.anchorText);
            rightText = itemView.findViewById(R.id.sideText);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

}
