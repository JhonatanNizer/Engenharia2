package com.example.nizer01.goplay.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.nizer01.goplay.R;

public class SetDateActivity extends AppCompatActivity {

    private String yearS;
    private String monthS;
    private String dayS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_date);
        CalendarView cv = findViewById(R.id.calendarView);
        final TextView tv = findViewById(R.id.textView_nextEvents);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                tv.setText("Dia: " + dayOfMonth + " Mes: " + month + " Ano:" + year);
                yearS = String.valueOf(year);
                monthS = String.valueOf(month);
                dayS = String.valueOf(dayOfMonth);
            }
        });
    }

    public void onClickContinuar(View view) {
        Intent intent = new Intent(this, SetTimeActivity.class);

        //Data from CreateEventActivity
        intent.putExtra("eventName", getIntent().getStringExtra("eventName"));
        intent.putExtra("eventDescription", getIntent().getStringExtra("eventDescription"));
        intent.putExtra("sportSelected", getIntent().getStringExtra("sportSelected"));
        intent.putExtra("minPlayers", getIntent().getStringExtra("minPlayers"));
        intent.putExtra("maxPlayers", getIntent().getStringExtra("maxPlayers"));
        intent.putExtra("eventLocal", getIntent().getStringExtra("eventLocal"));
        intent.putExtra("eventCity", getIntent().getStringExtra("eventCity"));

        //Data from date
        intent.putExtra("year", yearS);
        intent.putExtra("month", monthS);
        intent.putExtra("day", dayS);

        startActivity(intent);
    }

    public void onClickVoltar(View view) {
        finish();
    }

}
