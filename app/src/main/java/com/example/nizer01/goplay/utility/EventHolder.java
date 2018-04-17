package com.example.nizer01.goplay.utility;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nizer01.goplay.R;
import com.example.nizer01.goplay.domain.Event;

public class EventHolder extends RecyclerView.ViewHolder {

    private TextView tvName;
    private TextView tvCity;
    private TextView tvDate;
    private TextView tvTime;
    private ImageView ivIcon;

    public EventHolder(View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tvName);
        tvCity = itemView.findViewById(R.id.tvCidade);
        tvDate = itemView.findViewById(R.id.tvDate);
        tvTime = itemView.findViewById(R.id.tvTime);
        ivIcon = itemView.findViewById(R.id.ivPic);
    }

    public void setParameters(Event ev){
        tvName.setText(ev.getName());
        tvCity.setText(ev.getLocal().getCity());
        tvDate.setText(String.valueOf(ev.getStartTime()));
        tvTime.setText(String.valueOf(ev.getStartTime()));
    }

}
