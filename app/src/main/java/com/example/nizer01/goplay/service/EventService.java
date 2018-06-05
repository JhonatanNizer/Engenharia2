package com.example.nizer01.goplay.service;

import com.example.nizer01.goplay.domain.Event;
import com.example.nizer01.goplay.listeners.OnEventByIdListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EventService {

    private OnEventByIdListener onEventByIdListener;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("event/");

    public void setEventByIdListener(OnEventByIdListener listener) {
        onEventByIdListener = listener;
    }

    public void getEventById(final String id) {
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
                    onEventByIdListener.onNotFinded();
                } else {
                    onEventByIdListener.onFinded(event);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                onEventByIdListener.onError(databaseError);
            }
        });
    }

}
