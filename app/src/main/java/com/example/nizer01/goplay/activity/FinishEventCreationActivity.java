package com.example.nizer01.goplay.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.nizer01.goplay.R;
import com.example.nizer01.goplay.domain.Event;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class FinishEventCreationActivity extends AppCompatActivity {

    String eventName;
    String eventDescription;
    String sportSelected;
    int minPlayers;
    int maxPlayers;
    String eventLocal;
    String eventCity;
    Date eventDate;
    Timestamp startTime;
    Timestamp finishTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_event_creation);

        //dataToObject();

        TextView tv = findViewById(R.id.textView_name);
        tv.setText(getIntent().getStringExtra("eventName"));
        tv = findViewById(R.id.textView_description);
        tv.setText(getIntent().getStringExtra("eventDescription"));
        tv = findViewById(R.id.textView_sport);
        tv.setText(getIntent().getStringExtra("sportSelected"));
        tv = findViewById(R.id.textView_minPlayers);
        tv.setText(getIntent().getStringExtra("minPlayers"));
        tv = findViewById(R.id.textView_maxPlayers);
        tv.setText(getIntent().getStringExtra("maxPlayers"));
        tv = findViewById(R.id.textView_local);
        tv.setText(getIntent().getStringExtra("eventLocal"));
        tv = findViewById(R.id.textView_city);
        tv.setText(getIntent().getStringExtra("eventCity"));
        tv = findViewById(R.id.textView_date);
        tv.setText(
                getIntent().getStringExtra("day") + '/' +
                getIntent().getStringExtra("month") + '/' +
                getIntent().getStringExtra("year"));
        tv = findViewById(R.id.textView_startTime);
        tv.setText(
                getIntent().getStringExtra("startHour") + ':' +
                getIntent().getStringExtra("startMin"));
        tv = findViewById(R.id.textView_finishTime);
        tv.setText(
                getIntent().getStringExtra("finishHour") + ':' +
                getIntent().getStringExtra("finishMin"));
    }

    public void onClickCancelar(View view) {
        finish();
    }

    private void dataToObject(){

        eventName = getIntent().getStringExtra("eventName");
        eventDescription = getIntent().getStringExtra("eventDescription");
        sportSelected = getIntent().getStringExtra("sportSelected");
        minPlayers = Integer.parseInt(getIntent().getStringExtra("minPlayers"));
        maxPlayers = Integer.parseInt(getIntent().getStringExtra("maxPlayers"));
        eventLocal = getIntent().getStringExtra("eventLocal");
        eventCity = getIntent().getStringExtra("eventCity");

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            eventDate = dateFormat.parse(getIntent().getStringExtra("day") + "/" +
                    getIntent().getStringExtra("month") + "/" +
                    getIntent().getStringExtra("year"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        try {
            Date parseStartTime = dateFormat.parse("00/00/0000 " +
                    getIntent().getStringExtra("startHour") + ":" +
                    getIntent().getStringExtra("startMin"));
            startTime = new Timestamp(parseStartTime.getTime());

            Date parseFinishTime = dateFormat.parse("00/00/0000 " +
                    getIntent().getStringExtra("finishHour") + ":" +
                    getIntent().getStringExtra("finishMin"));
            finishTime = new Timestamp(parseFinishTime.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //PRONTO... AGORA É SÓ CRIAR UM OBJETO EVENTO COM OS DADOS FORMATADOS ACIMA E SALVAR NO BANCO

    }

}
