package com.example.nizer01.goplay.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nizer01.goplay.R;
import com.example.nizer01.goplay.domain.Event;
import com.example.nizer01.goplay.listeners.OnGetEventsListener;
import com.example.nizer01.goplay.service.EventService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class EventsActivity extends AppActivity {

    EventService eventManager = new EventService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateStartServices(this);
        onCreateUserNotIsLoggedInRedirectToMain();

        setContentView(R.layout.activity_events);

        setMenuActive(R.id.mn_events);
        unsetMenuClickable(R.id.mn_events);

        setEventsByTime(1);
    }

    public void setEventsByTime(int time) {
        eventManager.onGetEventsByStartTime(time, new OnGetEventsListener() {
            @Override
            public void onFinded(ArrayList<Event> evs) {
                clearEventItems(); setEventItems(evs);
            }

            @Override
            public void onNotFinded() {
                goToMaps();
            }

            @Override
            public void onError(Object er) {

            }
        });
    }

    public void setEventByActivity(final String activity) {
        eventManager.onGetEventsByActivity(activity, new OnGetEventsListener() {
            @Override
            public void onFinded(ArrayList<Event> evs) {
                clearEventItems(); setEventItems(evs);
            }

            @Override
            public void onNotFinded() {
                setEventsByTime(1);
            }

            @Override
            public void onError(Object er) {

            }
        });
    }

    public void clearEventItems() {
        LinearLayout ll = findViewById(R.id.lsEvents);
        ll.removeAllViewsInLayout();
    }

    public void setEventItems(ArrayList<Event> evs) {

        LinearLayout ll = findViewById(R.id.lsEvents);

        for (Event ev: evs) {
             ll.addView(getEventItemView(getLayoutInflater().inflate(R.layout.activity_events_item, null), ev));
        }
    }

    public View getEventItemView(View view, Event ev) {
        Bundle bn = new Bundle();
        bn.putString("id", ev.getId());
        (view.findViewById(R.id.lsEventsItem)).setTag(bn);
        ((TextView) view.findViewById(R.id.txEvItTitle)).setText(ev.getName());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy 'às' hh:mm");

        ((TextView) view.findViewById(R.id.txEvItDate)).setText(dateFormat.format(ev.getStartTime()).toString());
        ((TextView) view.findViewById(R.id.txEvItActivity)).setText(ev.getActivity().getName());
        ((TextView) view.findViewById(R.id.txEvItCity)).setText("Local: " + ev.getLocal().getCity());

        return view;
    }

    public void doFilter(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Filter by Activity");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clearEventItems();
                setEventByActivity(input.getText().toString());
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
