package com.example.nizer01.goplay.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nizer01.goplay.R;
import com.example.nizer01.goplay.utility.DatePicker;

public class SetDateActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_date);

        CalendarView cv = findViewById(R.id.calendarView);
        final TextView tv = findViewById(R.id.textView);

        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                tv.setText("Dia: " + dayOfMonth + " Mes: " + month + " Ano:" + year);
                //Toast.makeText(view.getContext(), "Year=" + year + " Month=" + month + " Day=" + dayOfMonth, Toast.LENGTH_LONG).show();
            }
        });


    }

    public void onClickContinuar(View view) {
        Intent intent = new Intent(this, SetTimeActivity.class);
        startActivity(intent);
    }

    public void onClickVoltar(View view) {
        finish();
    }



}
