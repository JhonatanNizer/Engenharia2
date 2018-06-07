package com.example.nizer01.goplay.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.nizer01.goplay.R;
import com.example.nizer01.goplay.domain.Activity;
import com.example.nizer01.goplay.domain.Event;
import com.example.nizer01.goplay.domain.Local;
import com.example.nizer01.goplay.domain.Profile;
import com.example.nizer01.goplay.listeners.OnGetProfileListener;
import com.example.nizer01.goplay.service.EventService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddEventActivity extends AppActivity {

    protected EventService eventManager = new EventService();
    protected DatePickerDialog datePickerDialog;
    protected TimePickerDialog timePickerDialog;
    protected Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateStartServices(this);
        onCreateUserNotIsLoggedInRedirectToMain();

        setContentView(R.layout.activity_add_event);
        setEventFields();
    }

    protected void setEventFields() {

        Intent in = getIntent();

        if(in.getExtras() != null) {

            if(in.getExtras().getString("local") != null) {
                setFieldText(in.getExtras().getString("local"), R.id.etEvLocal);
            }

            if(in.getExtras().getString("city") != null) {
                setFieldText(in.getExtras().getString("city"), R.id.etEvCity);
            }
        }

        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Select Activity");
        spinnerArray.add("Soccer");
        spinnerArray.add("Volleyball");
        spinnerArray.add("Basketball");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ((Spinner) findViewById(R.id.spEvActivity)).setAdapter(adapter);
    }

    protected void setFieldText(String value, int viewId) {
        ((TextView) findViewById(viewId)).setText(value);
    }

    protected String getFieldText(int viewId) {
        return ((TextView) findViewById(viewId)).getText().toString();
    }

    protected String getSelectedItem(int viewId) {
        return ((Spinner) findViewById(viewId)).getSelectedItem().toString();
    }

    public void doAddEvent(View view) {
        String etEvName = getFieldText(R.id.etEvName);
        String etEvDescription = getFieldText(R.id.etEvDescription);
        String spEvActivity = getSelectedItem(R.id.spEvActivity);
        String etEvMinPlayers = getFieldText(R.id.etEvMinPlayers);
        String etEvMaxPlayers = getFieldText(R.id.etEvMaxPlayers);
        String etEvRequirements = getFieldText(R.id.etEvRequirements);
        String etEvCost = getFieldText(R.id.etEvCost);
        String etEvLocal = getFieldText(R.id.etEvLocal);
        String etEvDate = getFieldText(R.id.etEvDate);
        String etEvStartTime = getFieldText(R.id.etEvStartTime);
        String etEvFinishTime = getFieldText(R.id.etEvFinishTime);
        String etEvCity = getFieldText(R.id.etEvCity);
        //String etEvDuration = getFieldText(R.id.etEvDuration);

        if( etEvName.isEmpty() || etEvDescription.isEmpty() || spEvActivity.isEmpty() ||
            etEvMinPlayers.isEmpty() || etEvMaxPlayers.isEmpty() || etEvRequirements.isEmpty() ||
            etEvCost.isEmpty() || etEvLocal.isEmpty() || etEvDate.isEmpty() || etEvStartTime.isEmpty() ||
            etEvFinishTime.isEmpty()
          ) {
            Toast.makeText(this, "No field can be empty", Toast.LENGTH_SHORT).show();
        } else {
            event = new Event();

            event.setUid(userManager.getCurrentUserId());
            event.setName(etEvName);
            event.setDescription(etEvDescription);

            Activity activity = new Activity();

            activity.setName(spEvActivity);
            activity.setMinPlayers(Integer.parseInt(etEvMinPlayers));
            activity.setMaxPlayers(Integer.parseInt(etEvMaxPlayers));

            event.setActivity(activity);
            event.setRequirements(etEvRequirements);
            event.setInvestiments(etEvCost);

            Local local = new Local();

            local.setCity(etEvCity);
            local.setDescription(etEvLocal);

            Intent li = getIntent();
            if(li.getExtras() != null) {
                if(!Double.isNaN(li.getExtras().getDouble("latitude"))) {
                    local.setLatitude(li.getExtras().getDouble("latitude"));
                }
                if(!Double.isNaN(li.getExtras().getDouble("longitude"))) {
                    local.setLongitude(li.getExtras().getDouble("longitude"));
                }
                if(li.getExtras().getString("name") != null) {
                    local.setName(li.getExtras().getString("name"));
                }
            }

            event.setLocal(local);

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            try {
                event.setStartTime((df.parse(etEvDate + " " + etEvStartTime)).getTime());
                event.setEndTime((df.parse(etEvDate + " " + etEvFinishTime)).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //ev.setDuration(Long.parseLong(etEvDuration));
            event.setMinPlayers(Integer.parseInt(etEvMinPlayers));
            event.setMaxPlayers(Integer.parseInt(etEvMaxPlayers));

            userManager.onGetProfileByEmail(userManager.getCurrentUserEmail(), new OnGetProfileListener() {
                @Override
                public void onFinded(Profile pf) {

                    event.addPuid(pf.getId());

                    String eventId = eventManager.writeNewEvent(event);

                    Bundle evBundle = new Bundle();
                    evBundle.putString("id", eventId);
                    goToEvent(evBundle);
                }

                @Override
                public void onNotFinded() {

                }

                @Override
                public void onError(Object er) {

                }
            });
        }
    }

    protected void showPickerDate(View view) {
        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                setFieldText(String.format("%02d", Integer.parseInt(String.valueOf(dayOfMonth))) + '/' +
                        String.format("%02d", Integer.parseInt(String.valueOf(monthOfYear + 1))) + '/' +
                        String.format("%02d", Integer.parseInt(String.valueOf(year))), R.id.etEvDate);
                datePickerDialog.dismiss();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    protected void showPickerStartTime(View view) {
        Calendar calendar = Calendar.getInstance();
        int mHour = calendar.get(Calendar.HOUR_OF_DAY);
        int mMinute = calendar.get(Calendar.MINUTE);
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                setFieldText(String.format("%02d", Integer.parseInt(String.valueOf(hourOfDay))) + ":" +
                        String.format("%02d", Integer.parseInt(String.valueOf(minute))), R.id.etEvStartTime);
                timePickerDialog.dismiss();
            }
        }, mHour, mMinute, true);
        timePickerDialog.show();
    }

    protected void showPickerFinishTime(View view) {
        Calendar calendar = Calendar.getInstance();
        int mHour = calendar.get(Calendar.HOUR_OF_DAY);
        int mMinute = calendar.get(Calendar.MINUTE);
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                setFieldText(String.format("%02d", Integer.parseInt(String.valueOf(hourOfDay))) + ":" +
                        String.format("%02d", Integer.parseInt(String.valueOf(minute))), R.id.etEvFinishTime);
                timePickerDialog.dismiss();
            }
        }, mHour, mMinute, true);
        timePickerDialog.show();
    }

}
