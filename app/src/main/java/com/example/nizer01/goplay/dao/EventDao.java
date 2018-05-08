package com.example.nizer01.goplay.dao;

import android.widget.ArrayAdapter;

import com.example.nizer01.goplay.domain.Activity;
import com.example.nizer01.goplay.domain.Event;
import com.example.nizer01.goplay.domain.Local;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EventDao {

    private static ArrayList<Event> eventList = new ArrayList<>();

    public static void createEvent(Event ev){
        //Salvando no banco FireBase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message/" );
        myRef.push().setValue(ev);

        //Salvando em uma lista estática
        eventList.add(ev);
    }

    public static ArrayList<Event> getEvents(){
        return eventList;
    }

    public static ArrayList<Event> getFilteredEvents(String activity_name){
        ArrayList<Event> filteredEventList = new ArrayList<>();
        for (int i = 0; i < eventList.size(); i++) {
            if(eventList.get(i).getActivity().getName().equals(activity_name)){
                filteredEventList.add(eventList.get(i));
            }
        }
        return filteredEventList;
    }

}
