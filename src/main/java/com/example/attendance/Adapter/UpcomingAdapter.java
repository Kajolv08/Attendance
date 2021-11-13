package com.example.attendance.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.attendance.POJO.UpcomingPOJO;
import com.example.attendance.Profile;
import com.example.attendance.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.ViewHolder> {
    private final List<UpcomingPOJO> upcomingPOJOList;
    private final Context context;
    private int lastPosition = -1;

    // RecyclerView recyclerView;
    public UpcomingAdapter(List<UpcomingPOJO> upcomingPOJOS, Context context) {
        this.upcomingPOJOList = upcomingPOJOS;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UpcomingAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_viewitems, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final UpcomingPOJO upcomingPOJO = upcomingPOJOList.get(position);
        holder.tv_Rollno.setText(upcomingPOJO.getRoll_no());
        //holder.tv_personBalance.setText(upcomingPOJO.getBalance());
        holder.cv_main.setOnClickListener(v -> {
            context.startActivity(new Intent(context, Profile.class)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)//send data on other activity
                    .putExtra("RollNo", upcomingPOJO.getRoll_no())
                    .putExtra("Name", upcomingPOJO.getName())
                    .putExtra("std", upcomingPOJO.getStd())
                    .putExtra("Address", upcomingPOJO.getAddress())
                    .putExtra("Phone", upcomingPOJO.getPhone())
                    .putExtra("Email", upcomingPOJO.getEmail())

            );
        });
    }

    @Override
    public int getItemCount() {
        return upcomingPOJOList.size();
    }

    @Override
    public int getItemViewType(int position) {
        /*return super.getItemViewType(position);*/
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView tv_Rollno, tv_personBalance;
        protected CardView cv_main;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cv_main = itemView.findViewById(R.id.cv_main);
            tv_Rollno = itemView.findViewById(R.id.tv_Rollno);
            //tv_personBalance = itemView.findViewById(R.id.tv_personBalance);
        }

    }
}