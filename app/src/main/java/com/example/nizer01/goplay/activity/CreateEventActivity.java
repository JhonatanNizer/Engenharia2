package com.example.nizer01.goplay.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.nizer01.goplay.R;

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

    Spinner spinnerSports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Esporte");
        spinnerArray.add("Futebol");
        spinnerArray.add("Voley");
        spinnerArray.add("Basquete");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSports = (Spinner) findViewById(R.id.spinner_selectSport);
        spinnerSports.setAdapter(adapter);
    }

    public void onClickContinuar(View view) {

        EditText editText = findViewById(R.id.editText_eventName);
        eventName = editText.getText().toString();

        editText = findViewById(R.id.editText_eventDescription);
        eventDescription = editText.getText().toString();

        sportSelected = spinnerSports.getSelectedItem().toString();

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
