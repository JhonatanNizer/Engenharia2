package com.example.nizer01.goplay.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.nizer01.goplay.R;
import com.example.nizer01.goplay.domain.Event;
import com.example.nizer01.goplay.listeners.OnEventByIdListener;
import com.example.nizer01.goplay.service.EventService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EventActivity extends AppActivity {

    EventService eventManager = new EventService();

    @Override
    protected void onStart(){
        super.onStart();

        if(!user.isUserLoggedIn()) {
            menuPrimary.goMain();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        menuPrimary.setMenuActive(R.id.mn_events);

        eventManager.setEventByIdListener(new OnEventByIdListener() {
            @Override
            public void onFinded(Event ev) {
                setEventFields(ev);
            }

            @Override
            public void onNotFinded() {
                menuPrimary.goMaps();
            }

            @Override
            public void onError(Object er) {

            }
        });

        eventManager.getEventById(getIntent().getExtras().getString("id"));
    }

    protected void setEventFields(Event ev) {
        setEventName(ev.getName());
    }

    protected void setEventName(String name) {
        TextView eventName = (TextView) findViewById(R.id.textview_event_name);
        eventName.setText(name);
    }
}
