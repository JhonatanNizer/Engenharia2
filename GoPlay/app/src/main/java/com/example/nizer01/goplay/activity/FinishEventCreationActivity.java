package com.example.nizer01.goplay.activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.nizer01.goplay.R;

public class FinishEventCreationActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_event_creation);

        TextView tv = findViewById(R.id.textView_name);
        tv.setText(getIntent().getStringExtra("eventName"));
        tv = findViewById(R.id.textView_description);
        tv.setText(getIntent().getStringExtra("eventDescription"));
        tv = findViewById(R.id.textView_sport);
        tv.setText(getIntent().getStringExtra("sportSelected"));
        tv = findViewById(R.id.textView_minPlayers);
        tv.setText(getIntent().getStringExtra("minPlayers"));
        tv = findViewById(R.id.textView_maxPlayers);
        tv.setText(getIntent().getStringExtra("maxPlayers"));
        tv = findViewById(R.id.textView_local);
        tv.setText(getIntent().getStringExtra("eventLocal"));
        tv = findViewById(R.id.textView_city);
        tv.setText(getIntent().getStringExtra("eventCity"));
        tv = findViewById(R.id.textView_date);
        tv.setText(
                getIntent().getStringExtra("day") + '/' +
                getIntent().getStringExtra("month") + '/' +
                getIntent().getStringExtra("year"));
        tv = findViewById(R.id.textView_startTime);
        tv.setText(
                getIntent().getStringExtra("startHour") + ':' +
                getIntent().getStringExtra("startMin"));
        tv = findViewById(R.id.textView_finishTime);
        tv.setText(
                getIntent().getStringExtra("finishHour") + ':' +
                getIntent().getStringExtra("finishMin"));
    }

    public void onClickCancelar(View view) {
        finish();
    }

}
