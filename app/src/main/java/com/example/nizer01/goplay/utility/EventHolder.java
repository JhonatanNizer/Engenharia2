package com.example.nizer01.goplay.utility;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nizer01.goplay.R;
import com.example.nizer01.goplay.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EventHolder extends RecyclerView.ViewHolder {

    private TextView tvName;
    private TextView tvCity;
    private TextView tvDate;
    private TextView tvTime;
    private TextView tvActivity;
    private ImageView ivIcon;

    public EventHolder(View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tvName);
        tvCity = itemView.findViewById(R.id.tvCidade);
        tvDate = itemView.findViewById(R.id.tvDate);
        tvTime = itemView.findViewById(R.id.tvTime);
        tvActivity = itemView.findViewById(R.id.tvActivity);
        ivIcon = itemView.findViewById(R.id.ivPic);
    }

    public void setParameters(Event ev){
        tvName.setText(ev.getName());
        tvCity.setText(ev.getLocal().getCity());
        tvActivity.setText(ev.getActivity().getName());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        String date  = dateFormat.format(ev.getStartTime());
        tvDate.setText(date);

        SimpleDateFormat dateFormat2 = new SimpleDateFormat("hh:mm");
        String time  = dateFormat2.format(ev.getStartTime());
        tvTime.setText(time);
    }

}
