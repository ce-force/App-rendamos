package com.ceforce.app_rendamos.RecyclerView;

import android.app.AppComponentFactory;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ceforce.app_rendamos.MainActivity;
import com.ceforce.app_rendamos.R;
import com.ceforce.app_rendamos.user.UserDetails;

import org.w3c.dom.Text;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";

    private Context context;

    private ArrayList<String> leftText;
    private ArrayList<String> rightText;
    private Context mContext;

    public RecyclerViewAdapter(Context mContext, ArrayList<String> leftText, ArrayList<String> rightText) {
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
        Log.d(TAG, "onBindViewHolder: called");


        holder.leftText.setText(this.leftText.get(position));
        holder.rightText.setText(this.rightText.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                context.startActivity(new Intent(context, UserDetails.class));

                Intent intent = new Intent(v.getContext(), UserDetails.class);
                v.getContext().startActivity(intent);


            }
        });
    }


    @Override
    public int getItemCount() {
        return leftText.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

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
