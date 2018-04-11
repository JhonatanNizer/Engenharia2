package com.example.nizer01.goplay.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.nizer01.goplay.R;

import java.util.ArrayList;
import java.util.List;

public class CreateEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);


        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Esporte");
        spinnerArray.add("Futebol");
        spinnerArray.add("Voley");
        spinnerArray.add("Fortnite");
        spinnerArray.add("Basquete");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerArray );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.spinner_selectSport);
        sItems.setAdapter(adapter);

    }

    public void onClickContinuar(View view) {
        Intent intent = new Intent(this, SetDateTimeActivity.class);
        startActivity(intent);
    }


}
