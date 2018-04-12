package com.example.nizer01.goplay.activity;

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

    private TimePicker timePicker1;
    private TextView time;
    private Calendar calendar;
    private String format = "";
    private int hour;
    private int min;
    private EditText edittext1;
    private EditText edittext2;
    private RadioButton radioButton;
    private int who;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);

        timePicker1 = findViewById(R.id.timePicker1);
        time = findViewById(R.id.textView1);
        calendar = Calendar.getInstance();

        edittext1 = findViewById(R.id.editText);
        edittext2 = findViewById(R.id.editText2);
        
        timePicker1.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                hour = hourOfDay;
                min = minute;
                updateTextView(hourOfDay, minute, who);
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.radio_inicio:
                if (checked)
                    who = 0;
                    break;
            case R.id.radio_termino:
                if (checked)
                    who = 1;
                    break;
        }
    }

    public void updateTextView(int hour, int min, int who) {
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }
        time.setText(new StringBuilder().append(hour).append(" : ").append(min).append(" ").append(format));
        if (who == 0) {
            edittext1.setText(new StringBuilder().append(hour).append(" : ").append(min).append(" ").append(format));
        }
        if(who == 1){
            edittext2.setText(new StringBuilder().append(hour).append(" : ").append(min).append(" ").append(format));
        }
    }

    public void onClickVoltar(View view) {
        finish();
    }

}
