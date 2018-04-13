package com.example.nizer01.goplay.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.nizer01.goplay.R;
import com.example.nizer01.goplay.dao.EventDao;
import com.example.nizer01.goplay.utility.EventAdapter;

public class ListEventsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_events);

        /*
        RecyclerView rvLista = findViewById(R.id.rvList);
        EventDao eventdao = new EventDao();
        EventAdapter adaptador = new EventAdapter();
        rvLista.setAdapter(adaptador);
        rvLista.setLayoutManager(new LinearLayoutManager(this));
        */

    }
}
