package com.example.nizer01.goplay.dao;

import android.widget.ArrayAdapter;

import com.example.nizer01.goplay.domain.Activity;
import com.example.nizer01.goplay.domain.Event;
import com.example.nizer01.goplay.domain.Local;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EventDao {

    private static ArrayList<Event> eventList = new ArrayList<>();

    private static FirebaseDatabase database  = FirebaseDatabase.getInstance();
    private static DatabaseReference databaseReference = database.getReference("event/" );

    public static void createEvent(Event ev){
        //Salvando no banco FireBase
        databaseReference.push().setValue(ev);

        //Salvando em uma lista estática
        eventList.add(ev);
    }

    public static ArrayList<Event> getEvents(){ return eventList; }

    public static ArrayList<Event> getFireBaseEvents(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("event");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventList.clear();
                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Event ev = objSnapshot.getValue(Event.class);
                    System.out.println(ev.getName());
                    eventList.add(ev);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*databaseReference.child("event").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventList.clear();
                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Event ev = objSnapshot.getValue(Event.class);
                    eventList.add(ev);
                    System.out.println(ev.getName());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

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
