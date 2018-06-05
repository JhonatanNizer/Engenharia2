package com.example.nizer01.goplay.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nizer01.goplay.R;
import com.example.nizer01.goplay.utility.AppActivity;

public class EventActivity extends AppActivity {

    String eventId;

    @Override
    protected void onStart(){
        super.onStart();

        if(!isUserLoggedIn()) {
            goMain();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        setMenuPrimaryActive(R.id.mn_events);

        eventId = getIntent().getExtras().getString("id");

        System.out.println(eventId);
    }

}
