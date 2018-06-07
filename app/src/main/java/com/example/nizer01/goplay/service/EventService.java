package com.example.nizer01.goplay.service;

import android.content.Intent;

import com.example.nizer01.goplay.activity.AddEventActivity;
import com.example.nizer01.goplay.domain.Event;
import com.example.nizer01.goplay.domain.Local;
import com.example.nizer01.goplay.listeners.OnGetEventListener;
import com.example.nizer01.goplay.listeners.OnGetEventsListener;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EventService {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("event/");

    public String writeNewEvent(Event ev) {

        String key = firebaseDatabase.getReference().child("event").push().getKey();
        ev.setId(key);

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/event/" + key, ev);

        firebaseDatabase.getReference().updateChildren(childUpdates);

        return key;
    }

    public String writeNewEventSubscriber(Event ev, String userId) {

        String key = firebaseDatabase.getReference().child("event").push().getKey();
        ev.setId(key);

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/event/" + key, ev);

        firebaseDatabase.getReference().updateChildren(childUpdates);

        return key;
    }

    public void onGetEventById(final String id, final OnGetEventListener onGetEventListener) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Event event = null;

                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Event ev = objSnapshot.getValue(Event.class);

                    if(ev.getId().equals(id)) {
                        event = ev;
                        break;
                    }
                }

                if(event == null) {
                    onGetEventListener.onNotFinded();
                } else {
                    onGetEventListener.onFinded(event);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                onGetEventListener.onError(databaseError);
            }
        });
    }

    public void onGetEventsByStartTime(final int startTime, final OnGetEventsListener onGetEventsListener) {
        databaseReference.orderByChild("startTime").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<Event> events = new ArrayList<>();

                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()) {
                    Event ev = objSnapshot.getValue(Event.class);

                    if(ev.getStartTime() >= startTime) {
                        events.add(ev);
                    }
                }

                if(events.isEmpty()) {
                    onGetEventsListener.onNotFinded();
                } else {
                    onGetEventsListener.onFinded(events);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                onGetEventsListener.onError(databaseError);
            }
        });
    }

    public void onGetEventsByActivity(final String activity, final OnGetEventsListener onGetEventsListener) {
        databaseReference.orderByChild("startTime").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<Event> events = new ArrayList<>();

                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()) {
                    Event ev = objSnapshot.getValue(Event.class);

                    if(ev.getActivity().getName().equals(activity)) {
                        events.add(ev);
                    }
                }

                if(events.isEmpty()) {
                    onGetEventsListener.onNotFinded();
                } else {
                    onGetEventsListener.onFinded(events);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                onGetEventsListener.onError(databaseError);
            }
        });
    }

    public void onGetEventsByLocalBounds(final LatLngBounds bounds, final OnGetEventsListener onGetEventsListener) {
        databaseReference.orderByChild("startTime").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<Event> events = new ArrayList<>();

                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()) {
                    Event ev = objSnapshot.getValue(Event.class);
                    Local lc = ev.getLocal();

                    if(  lc.getLatitude() <= bounds.northeast.latitude &&
                            lc.getLatitude() >= bounds.southwest.latitude &&
                            lc.getLongitude() <= bounds.northeast.longitude &&
                            lc.getLongitude() >= bounds.southwest.longitude
                            ) {
                        events.add(ev);
                    }
                }

                if(events.isEmpty()) {
                    onGetEventsListener.onNotFinded();
                } else {
                    onGetEventsListener.onFinded(events);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                onGetEventsListener.onError(databaseError);
            }
        });
    }

    public void onGetEventsByUserId(final String uid, final OnGetEventsListener onGetEventsListener) {
        databaseReference.orderByChild("startTime").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<Event> events = new ArrayList<>();

                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()) {
                    Event ev = objSnapshot.getValue(Event.class);

                    if(ev.getUid().equals(uid)) {
                        events.add(ev);
                    }
                }

                if(events.isEmpty()) {
                    onGetEventsListener.onNotFinded();
                } else {
                    onGetEventsListener.onFinded(events);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                onGetEventsListener.onError(databaseError);
            }
        });
    }
}
