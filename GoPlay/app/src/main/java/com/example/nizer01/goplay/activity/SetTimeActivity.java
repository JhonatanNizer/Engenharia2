package com.example.nizer01.goplay.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.nizer01.goplay.R;


import java.util.Calendar;

public class SetTimeActivity extends AppCompatActivity {

    private int startHour;
    private int startMin;

    private int finishHour;
    private int finishMin;

    //Variável utilizada para gambiarra bem no estilo Jhonatan Nizer Tagina da Silva
    private int who;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);

        final String format = "";
        TimePicker timePicker = findViewById(R.id.timePicker);
        final EditText edittext1 = findViewById(R.id.editText_startTime);
        final EditText edittext2 = findViewById(R.id.editText_finishTime);

        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(who==0) {
                    startHour = hourOfDay;
                    startMin = minute;
                    edittext1.setText(new StringBuilder().append(startHour).append(" : ").append(startMin).append(" ").append(format));
                }
                if(who==1) {
                    finishHour = hourOfDay;
                    finishMin = minute;
                    edittext2.setText(new StringBuilder().append(finishHour).append(" : ").append(finishMin).append(" ").append(format));
                }
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.radio_startTime:
                if (checked)
                    who = 0;
                    break;
            case R.id.radio_finishTime:
                if (checked)
                    who = 1;
                    break;
        }
    }

    public void onClickContinue(View view){
        Intent intent = new Intent(this, FinishEventCreationActivity.class);



        startActivity(intent);
    }

    public void onClickVoltar(View view) {
        finish();
    }

}
