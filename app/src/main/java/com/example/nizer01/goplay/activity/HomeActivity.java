package com.example.nizer01.goplay.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.nizer01.goplay.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onClickCreateEvent(View view){
        Intent intent = new Intent(this, CreateEventActivity.class );
        startActivity(intent);
    }

    public void onClickCreateEvent2(View view){
        Intent intent = new Intent(this, CreateEventActivity2.class );
        startActivity(intent);
    }

    public void onClickListEvents(View view){
        Intent intent = new Intent(this, ListEventsActivity.class );
        startActivity(intent);
    }

}
