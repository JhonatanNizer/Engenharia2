package com.example.nizer01.goplay.activity;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.example.nizer01.goplay.R;
import com.example.nizer01.goplay.dao.EventDao;
import com.example.nizer01.goplay.utility.AppActivity;
import com.example.nizer01.goplay.utility.EventAdapter;

public class EventsActivity extends AppActivity {

    String m_Text = "";
    EventAdapter adapter;
    RecyclerView rvList;

    @Override
    protected void onStart(){
        super.onStart();

        if(!isUserLoggedIn()) {
            goMain();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        setMenuPrimaryActive(R.id.mn_events);
        unsetMenuPrimaryClickable(R.id.mn_events);

        rvList = findViewById(R.id.rvList);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);

        adapter = new EventAdapter(EventDao.getFireBaseEvents());
        rvList.setAdapter(adapter);
        rvList.setLayoutManager(new LinearLayoutManager(this));
    }

    public void updateList(String filter) {
        adapter = new EventAdapter(EventDao.getFilteredEvents(filter));
        rvList.setAdapter(adapter);
        rvList.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onClickFilter(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Filter by Activity");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                updateList(m_Text);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

}
