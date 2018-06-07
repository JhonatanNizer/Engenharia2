package com.example.nizer01.goplay.listeners;

import com.example.nizer01.goplay.domain.Event;

import java.util.ArrayList;

public interface OnGetEventsListener {
    void onFinded(ArrayList<Event> evs);
    void onNotFinded();
    void onError(Object er);
}

