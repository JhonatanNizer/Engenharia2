package com.example.nizer01.goplay.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    private void onNumberPickerClick(){
        final TextView tv = (TextView) findViewById(R.id.textView_np);
        NumberPicker np = (NumberPicker) findViewById(R.id.numberPicker);

        //Set TextView text color
        tv.setTextColor(Color.parseColor("#ffd32b3b"));

        //Populate NumberPicker values from minimum and maximum value range
        //Set the minimum value of NumberPicker
        np.setMinValue(0);
        //Specify the maximum value/number of NumberPicker
        np.setMaxValue(10);

        //Gets whether the selector wheel wraps when reaching the min/max value.
        np.setWrapSelectorWheel(true);

        //Set a value change listener for NumberPicker
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker
                tv.setText("Selected Number : " + newVal);
                System.out.println("FOIIIIIIIIIIIIIIIIIIII");
            }
        });
    }

}
