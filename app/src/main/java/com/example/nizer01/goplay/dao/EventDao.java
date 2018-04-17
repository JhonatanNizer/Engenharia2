package com.example.nizer01.goplay.dao;

import android.widget.ArrayAdapter;

import com.example.nizer01.goplay.domain.Activity;
import com.example.nizer01.goplay.domain.Event;
import com.example.nizer01.goplay.domain.Local;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EventDao {

    private static ArrayList<Event> eventList = new ArrayList<>();

    public static void createEvent(Event ev){
        eventList.add(ev);
    }

    public static ArrayList<Event> getEvents(){
        return eventList;
    }

}
