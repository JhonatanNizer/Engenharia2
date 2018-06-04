package com.example.nizer01.goplay.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nizer01.goplay.R;
import com.example.nizer01.goplay.dao.EventDao;
import com.example.nizer01.goplay.domain.Event;

import java.util.Date;

public class EventActivity extends AppCompatActivity {

    TextView eventName;
    TextView eventDescription;
    TextView eventRequirements;
    TextView eventDate;
    TextView eventCost;

    Event ev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        setWidgets();

        int position = getIntent().getIntExtra("EventListPosition", 0);
        inflateData(position);
    }

    private void setWidgets(){
        eventName = findViewById(R.id.textview_event_name);
        eventDescription = findViewById(R.id.textview_event_description);
        eventRequirements = findViewById(R.id.textView_event_requiremets);
        eventDate = findViewById(R.id.textView_event_date);
        eventCost = findViewById(R.id.textView_event_custo);
    }

    private void inflateData(int position){
        ev = EventDao.getEventFromList(position);

        eventName.setText(ev.getName());
        eventDescription.setText("Descrição: " +  ev.getDescription());
        eventRequirements.setText("Levar: " + ev.getRequirements());

        Date date = new Date(ev.getStartTime());
        eventDate.setText("Data: " + date.toString());
        eventCost.setText("Custo: " + ev.getInvestiments());

    }

    public void atualizaDados(View view){
        ev.setName("Nome atualizado!");
        ev.setDescription("Descrição atualizada!");
        EventDao.updateEvent(ev);
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void deletaEvento(View view){
        EventDao.deleteEvent(ev);
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

}
