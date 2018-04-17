package com.example.nizer01.goplay.utility;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nizer01.goplay.R;
import com.example.nizer01.goplay.domain.Event;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter {

    private ArrayList<Event> eventList;

    public EventAdapter(ArrayList<Event> initialList){
        this.eventList = initialList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View eventSingleView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_view, parent, false);
        EventHolder gaveta = new EventHolder(eventSingleView);
        return gaveta;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EventHolder gaveta = (EventHolder) holder;
        Event event = this.eventList.get(position);
        gaveta.setParameters(event);
    }

    @Override
    public int getItemCount() {
        return this.eventList.size();
    }

}
