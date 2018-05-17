package com.example.nizer01.goplay.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nizer01.goplay.R;
import com.example.nizer01.goplay.dao.EventDao;
import com.example.nizer01.goplay.domain.Activity;
import com.example.nizer01.goplay.domain.Event;
import com.example.nizer01.goplay.domain.Local;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class FinishEventCreationActivity extends AppCompatActivity {

    String eventName;
    String eventDescription;
    String eventRequirements;
    String eventCost;
    String sportSelected;
    String minPlayers;
    String maxPlayers;
    String eventLocal;
    String eventCity;
    String eventDate;
    String startTime;
    String duration;
    String finishTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_event_creation);

        TextView tv = findViewById(R.id.textView_name);
        tv.setText(getIntent().getStringExtra("eventName"));
        tv = findViewById(R.id.textView_description);
        tv.setText(getIntent().getStringExtra("eventDescription"));
        tv = findViewById(R.id.textView_sport);
        tv.setText(getIntent().getStringExtra("eventActivity"));
        tv = findViewById(R.id.textView_requirements);
        tv.setText(getIntent().getStringExtra("eventRequirements"));
        tv = findViewById(R.id.textView_cost);
        tv.setText(getIntent().getStringExtra("eventCost"));
        tv = findViewById(R.id.textView_minPlayers);
        tv.setText(getIntent().getStringExtra("eventMinPlayers"));
        tv = findViewById(R.id.textView_maxPlayers);
        tv.setText(getIntent().getStringExtra("eventMaxPlayers"));
        tv = findViewById(R.id.textView_local);
        tv.setText(getIntent().getStringExtra("eventLocal"));
        tv = findViewById(R.id.textView_city);
        tv.setText(getIntent().getStringExtra("eventCity"));
        tv = findViewById(R.id.textView_date);

        tv.setText(getIntent().getStringExtra("eventDate"));
        tv = findViewById(R.id.textView_startTime);
        tv.setText(getIntent().getStringExtra("eventStartTime"));
        tv = findViewById(R.id.textView_finishTime);
        tv.setText(getIntent().getStringExtra("eventFinishTime"));

        /*
        tv.setText(
                String.format("%02d", Integer.parseInt(getIntent().getStringExtra("day").toString())) + '/' +
                String.format("%02d", Integer.parseInt(getIntent().getStringExtra("month").toString())) + '/' +
                String.format("%02d", Integer.parseInt(getIntent().getStringExtra("year").toString())));
        tv = findViewById(R.id.textView_startTime);
        tv.setText(
                String.format("%02d", Integer.parseInt(getIntent().getStringExtra("startHour").toString())) + ':' +
                String.format("%02d", Integer.parseInt(getIntent().getStringExtra("startMin").toString())));
        tv = findViewById(R.id.textView_finishTime);
        tv.setText(
                String.format("%02d", Integer.parseInt(getIntent().getStringExtra("finishHour").toString())) + ':' +
                String.format("%02d", Integer.parseInt(getIntent().getStringExtra("finishMin").toString())));
        */
    }

    public void onClickConfirmar(View view) {
        eventName = getIntent().getStringExtra("eventName");
        eventDescription = getIntent().getStringExtra("eventDescription");
        eventRequirements = getIntent().getStringExtra("eventRequirements");
        eventCost = getIntent().getStringExtra("eventCost");
        sportSelected = getIntent().getStringExtra("eventActivity");
        minPlayers = getIntent().getStringExtra("eventMinPlayers");
        maxPlayers = getIntent().getStringExtra("eventMaxPlayers");
        eventLocal = getIntent().getStringExtra("eventLocal");
        eventCity = getIntent().getStringExtra("eventCity");
        eventDate = getIntent().getStringExtra("eventDate");
        startTime = getIntent().getStringExtra("eventStartTime");
        duration = getIntent().getStringExtra("eventDuration");
        finishTime = getIntent().getStringExtra("eventFinishTime");


        /*DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //eventDate = getIntent().getStringExtra("eventDate");
        dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        try {
            Date parseStartTime = dateFormat.parse("00/00/0000 " +
                    getIntent().getStringExtra("startHour") + ":" +
                    getIntent().getStringExtra("startMin"));
            //startTime = new Timestamp(parseStartTime.getTime());
            startTime = parseStartTime.toString();

            Date parseFinishTime = dateFormat.parse("00/00/0000 " +
                    getIntent().getStringExtra("finishHour") + ":" +
                    getIntent().getStringExtra("finishMin"));
            finishTime = parseFinishTime.toString();
            //finishTime = new Timestamp(parseFinishTime.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        //-----------------Criação do Objeto Evento---------------
        Local lc = new Local();
        lc.setName(eventLocal);
        lc.setDescription("Descrição do Local Selecionado");
        lc.setCity(eventCity);
        lc.setLatitude(1);
        lc.setLatitude(1);

        Activity ac = new Activity();
        ac.setName(sportSelected);
        ac.setDescription("Descrição do Esporte Selecionado");
        ac.setMinPlayers(Integer.parseInt(minPlayers));
        ac.setMaxPlayers(Integer.parseInt(maxPlayers));

        Event ev = new Event();
        ev.setName(eventName);
        ev.setDescription(eventDescription);
        ev.setRequirements(eventRequirements);
        ev.setInvestiments(eventCost);
        ev.setMinPlayers(ac.getMinPlayers());
        ev.setMaxPlayers(ac.getMaxPlayers());
        ev.setStatus("Programado");
        ev.setLocal(lc);
        ev.setActivity(ac);
        //ev.setDuration(duration);

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        try {
            Date date = df.parse(eventDate + " " + startTime);
            long time = date.getTime();
            ev.setStartTime(new Timestamp(time));
            //ev.setEndTime(new Timestamp(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        EventDao.createEvent(ev);
        Toast.makeText(this, "Event Created !", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void onClickCancelar(View view) {
        finish();
    }

}
