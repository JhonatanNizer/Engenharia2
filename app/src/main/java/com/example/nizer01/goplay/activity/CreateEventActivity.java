package com.example.nizer01.goplay.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.nizer01.goplay.R;
import com.example.nizer01.goplay.domain.Activity;
import com.example.nizer01.goplay.domain.Event;

import java.util.ArrayList;
import java.util.List;

public class CreateEventActivity extends AppCompatActivity {

    //O certo é iniciar a instancia da classe de domínio com seus atributos
    String eventName;
    String eventDescription;
    String sportSelected;
    String minPlayers;
    String maxPlayers;
    String eventLocal;
    String eventCity;

    List<Activity> activityList = new ArrayList<>();
    Spinner activitySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        //Atividades adicionadas manualmente abaixo, deverão ser substituídas por um SELECT no banco
        Activity activity1 = new Activity();
        activity1.setName("Futebol de Salão");
        activity1.setDescription("Esporte mais popular do mundo");
        activity1.setMinPlayers(6);
        activity1.setMaxPlayers(10);

        Activity activity2 = new Activity();
        activity2.setName("Volei de Quadra");
        activity2.setDescription("Esporte muito da hora");
        activity2.setMinPlayers(8);
        activity2.setMaxPlayers(12);

        Activity activity3 = new Activity();
        activity3.setName("Basquete");
        activity3.setDescription("Esporte de americano");
        activity3.setMinPlayers(4);
        activity3.setMaxPlayers(10);

        activityList.add(activity1);
        activityList.add(activity2);
        activityList.add(activity3);

        //ArrayAdapter<Activity> eventAdapter = new ArrayAdapter<Activity>(this, android.R.layout.simple_spinner_dropdown_item, activityList);
        //eventAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //activitySpinner.setAdapter(eventAdapter);

        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Selecionar Atividade");
        spinnerArray.add(activity1.getName());
        spinnerArray.add(activity2.getName());
        spinnerArray.add(activity3.getName());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activitySpinner = (Spinner) findViewById(R.id.spinner_selectSport);
        activitySpinner.setAdapter(adapter);
    }

    public void onClickContinuar(View view) {

        EditText editText = findViewById(R.id.editText_eventName);
        eventName = editText.getText().toString();

        editText = findViewById(R.id.editText_eventDescription);
        eventDescription = editText.getText().toString();

        sportSelected = activitySpinner.getSelectedItem().toString();

        editText = findViewById(R.id.editText_minPlayers);
        minPlayers = editText.getText().toString();

        editText = findViewById(R.id.editText_maxPlayers);
        maxPlayers = editText.getText().toString();

        editText = findViewById(R.id.editText_eventLocal);
        eventLocal = editText.getText().toString();

        editText = findViewById(R.id.editText_eventCity);
        eventCity = editText.getText().toString();

        Intent intent = new Intent(this, SetDateActivity.class );

        intent.putExtra("eventName", eventName);
        intent.putExtra("eventDescription", eventDescription);
        intent.putExtra("sportSelected", sportSelected);
        intent.putExtra("minPlayers", minPlayers);
        intent.putExtra("maxPlayers", maxPlayers);
        intent.putExtra("eventLocal", eventLocal);
        intent.putExtra("eventCity", eventCity);

        startActivity(intent);
    }

    public void onClickCancelar(View view) {
        finish();
    }

}
