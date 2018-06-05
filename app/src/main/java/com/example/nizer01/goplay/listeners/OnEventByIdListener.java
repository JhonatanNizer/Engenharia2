package com.example.nizer01.goplay.listeners;

import com.example.nizer01.goplay.domain.Event;

public interface OnEventByIdListener {
    void onFinded(Event ev);
    void onNotFinded();
    void onError(Object er);
}
