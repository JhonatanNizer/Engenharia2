package com.example.nizer01.goplay.dao;

import android.widget.ArrayAdapter;

import com.example.nizer01.goplay.domain.Activity;
import com.example.nizer01.goplay.domain.Event;
import com.example.nizer01.goplay.domain.Local;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class EventDao {

    private static ArrayList<Event> eventList = new ArrayList<>();

    private static FirebaseDatabase database  = FirebaseDatabase.getInstance();
    private static DatabaseReference databaseReference = database.getReference("event/" );

    public static void createEvent(Event ev){
        //Salvando no banco FireBase
        ev.setID(UUID.randomUUID().toString());
        databaseReference.child(ev.getID()).setValue(ev);
        //databaseReference.push().setValue(ev);

        //Salvando em uma lista estática
        //eventList.add(ev);
    }

    public static Event getEventFromList(int position){
        return eventList.get(position);
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
                    //System.out.println(ev.getName());
                    eventList.add(ev);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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

    public static ArrayList<Event> getFirebaseFilteredEvents(String activity_name){
        final ArrayList<Event> filteredEventList = new ArrayList<>();
        Query query;

        if(activity_name.equals("")){
            return eventList;
        }else {
            query = databaseReference.child("event").orderByChild("name").startAt(activity_name);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                            Event ev = objSnapshot.getValue(Event.class);
                            eventList.add(ev);
                            System.out.println("Filtrado!" + ev.getName());
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            return filteredEventList;
        }
    }

    public static void updateEvent(Event ev){
        databaseReference.child(ev.getID()).setValue(ev);
    }

    public static void deleteEvent(Event ev){
        databaseReference.child(ev.getID()).removeValue();
    }

}
