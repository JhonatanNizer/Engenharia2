package com.example.nizer01.goplay.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.nizer01.goplay.R;
import com.example.nizer01.goplay.activity.MainActivity;
import com.example.nizer01.goplay.dao.UserDao;
import com.example.nizer01.goplay.domain.Activity;
import com.example.nizer01.goplay.domain.Role;
import com.example.nizer01.goplay.domain.User;
import com.example.nizer01.goplay.utility.PickerDialogFragment;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import mobi.upod.timedurationpicker.TimeDurationPicker;
import mobi.upod.timedurationpicker.TimeDurationPickerDialogFragment;

public class CreateEventActivity2 extends AppCompatActivity implements View.OnClickListener {

    private EditText nameEditText;
    private EditText descriptionEditText;
    private EditText cityEditText;
    private EditText localEdittext;
    private EditText dateEdittext;
    private EditText startTimeEdittext;
    private EditText durationEdittext;
    private EditText minPlayersEditText;
    private EditText maxPlayersEditText;
    private EditText finishTimeEdittext;
    private EditText requirementsEditText;
    private EditText costEditText;
    private Button createEventButton;
    private Button backButton;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    List<Activity> activityList = new ArrayList<>();
    Spinner activitySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event2);
        setWidgets();
        setListeners();

        //Remover posteriormente
        Activity activity1 = new Activity();
        activity1.setName("Soccer");
        activity1.setDescription("Soccer");
        activity1.setMinPlayers(6);
        activity1.setMaxPlayers(10);

        Activity activity2 = new Activity();
        activity2.setName("Voleyball");
        activity2.setDescription("Esporte muito da hora");
        activity2.setMinPlayers(8);
        activity2.setMaxPlayers(12);

        Activity activity3 = new Activity();
        activity3.setName("Basketball");
        activity3.setDescription("Esporte de americano");
        activity3.setMinPlayers(4);
        activity3.setMaxPlayers(10);

        activityList.add(activity1);
        activityList.add(activity2);
        activityList.add(activity3);

        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Select Activity");
        spinnerArray.add(activity1.getName());
        spinnerArray.add(activity2.getName());
        spinnerArray.add(activity3.getName());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activitySpinner = (Spinner) findViewById(R.id.spinner_createEvent_sport);
        activitySpinner.setAdapter(adapter);
        //Remover posteriormente

    }

    private void setWidgets() {
        nameEditText = findViewById(R.id.editText_createEvent_eventName);
        descriptionEditText = findViewById(R.id.editText_createEvent_eventDescription);
        minPlayersEditText = findViewById(R.id.editText_createEvent_minPlayers);
        maxPlayersEditText = findViewById(R.id.editText_createEvent_maxPlayers);
        cityEditText = findViewById(R.id.editText_createEvent_city);
        localEdittext = findViewById(R.id.editText_createEvent_local);
        dateEdittext = findViewById(R.id.editText_createEvent_date);
        startTimeEdittext = findViewById(R.id.editText_createEvent_startTime);
        durationEdittext = findViewById(R.id.editText_createEvent_duration);
        finishTimeEdittext = findViewById(R.id.editText_createEvent_finishTime);
        requirementsEditText = findViewById(R.id.editText_createEvent_requirements);
        costEditText = findViewById(R.id.editText_createEvent_cost);
        createEventButton = findViewById(R.id.button_createEvent_createEvent);
        backButton = findViewById(R.id.button_createEvent_back);
    }

    private void setListeners() {
        dateEdittext.setOnClickListener(this);
        startTimeEdittext.setOnClickListener(this);
        durationEdittext.setOnClickListener(this);
        finishTimeEdittext.setOnClickListener(this);
        createEventButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.editText_createEvent_date:
                onClickEditTextEventDate();
                break;
            case R.id.editText_createEvent_startTime:
                onClickEditTextStartTime();
                break;
            case R.id.editText_createEvent_duration:
                onClickEditTextDuration();
                break;
            case R.id.editText_createEvent_finishTime:
                onClickEditTextFinishTime();
                break;
            case R.id.button_createEvent_createEvent:
                onClickButtonCreateEvent();
                break;
            case R.id.button_createEvent_back:
                onClickButtonBack();
                break;
        }
    }

    private void onClickEditTextEventDate() {
        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //dateEdittext.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
                dateEdittext.setText(String.format("%02d", Integer.parseInt(String.valueOf(dayOfMonth))) + '/' +
                        String.format("%02d", Integer.parseInt(String.valueOf(monthOfYear+1))) + '/' +
                        String.format("%02d", Integer.parseInt(String.valueOf(year))));
                datePickerDialog.dismiss();
            }
        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void onClickEditTextStartTime() {
        Calendar calendar = Calendar.getInstance();
        int mHour = calendar.get(Calendar.HOUR_OF_DAY);
        int mMinute = calendar.get(Calendar.MINUTE);
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //startTimeEdittext.setText(hourOfDay+":"+minute);
                startTimeEdittext.setText(String.format("%02d", Integer.parseInt(String.valueOf(hourOfDay))) + ":" +
                        String.format("%02d", Integer.parseInt(String.valueOf(minute))));
                timePickerDialog.dismiss();
            }
        }, mHour, mMinute, true);
        timePickerDialog.show();
    }

    private void onClickEditTextFinishTime(){
        Calendar calendar = Calendar.getInstance();
        int mHour = calendar.get(Calendar.HOUR_OF_DAY);
        int mMinute = calendar.get(Calendar.MINUTE);
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                finishTimeEdittext.setText(String.format("%02d", Integer.parseInt(String.valueOf(hourOfDay))) + ":" +
                        String.format("%02d", Integer.parseInt(String.valueOf(minute))));
                timePickerDialog.dismiss();
            }
        }, mHour, mMinute, true);
        timePickerDialog.show();
    }

    private void onClickEditTextDuration(){
        new PickerDialogFragment().show(getFragmentManager(), "Duration Picker");
    }

    private void onClickButtonCreateEvent() {

        if((nameEditText.getText().toString().equals("") ||
                descriptionEditText.getText().toString().equals("") ||
                activitySpinner.getSelectedItem().toString().equals("Select Activity") ||
                minPlayersEditText.getText().toString().equals("") ||
                maxPlayersEditText.getText().toString().equals("") ||
                requirementsEditText.getText().toString().equals("") ||
                costEditText.getText().toString().equals("") ||
                localEdittext.getText().toString().equals("") ||
                dateEdittext.getText().toString().equals("") ||
                startTimeEdittext.getText().toString().equals("") ||
                finishTimeEdittext.getText().toString().equals("")
        )) {
            Toast.makeText(this, "No field can be empty", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(this, FinishEventCreationActivity.class);
            intent.putExtra("eventName", nameEditText.getText().toString());
            intent.putExtra("eventDescription", descriptionEditText.getText().toString());
            intent.putExtra("eventActivity", activitySpinner.getSelectedItem().toString());
            intent.putExtra("eventMinPlayers", minPlayersEditText.getText().toString());
            intent.putExtra("eventMaxPlayers", maxPlayersEditText.getText().toString());
            intent.putExtra("eventRequirements", requirementsEditText.getText().toString());
            intent.putExtra("eventCost", costEditText.getText().toString());
            intent.putExtra("eventCity", cityEditText.getText().toString());
            intent.putExtra("eventLocal", localEdittext.getText().toString());
            intent.putExtra("eventDate", dateEdittext.getText().toString());
            intent.putExtra("eventStartTime", startTimeEdittext.getText().toString());
            intent.putExtra("eventFinishTime", finishTimeEdittext.getText().toString());
            startActivity(intent);
        }
    }



    private void onClickButtonBack(){
        finish();
    }

}
