package com.example.nizer01.goplay.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nizer01.goplay.R;
import com.example.nizer01.goplay.domain.Event;
import com.example.nizer01.goplay.listeners.OnGetEventsListener;
import com.example.nizer01.goplay.service.EventService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HomeActivity extends AppActivity {

    EventService eventManager = new EventService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        onCreateStartServices();
        onCreateUserNotIsLoggedInRedirectToMain();


        setMenuActive(R.id.mn_dashboard);
        unsetMenuClickable(R.id.mn_dashboard);

        setMyEventsByUserId(userManager.getCurrentUserId());
    }

    public void setMyEventsByUserId(String uid) {
        eventManager.onGetEventsByUserId(uid, new OnGetEventsListener() {
            @Override
            public void onFinded(ArrayList<Event> evs) {
                setMyEventsItems(evs);
            }

            @Override
            public void onNotFinded() {
                System.out.println("whyyyyy");
            }

            @Override
            public void onError(Object er) {

            }
        });
    }

    public void clearUserIdEventItems() {
        LinearLayout ll = findViewById(R.id.lsEvents);
        ll.removeAllViewsInLayout();
    }

    public void setMyEventsItems(ArrayList<Event> evs) {

        LinearLayout ll = findViewById(R.id.lsHoMyEvents);

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

        ((TextView) view.findViewById(R.id.txEvItDate)).setText(dateFormat.format(ev.getStartTime()));
        ((TextView) view.findViewById(R.id.txEvItActivity)).setText(ev.getActivity().getName());
        ((TextView) view.findViewById(R.id.txEvItCity)).setText(ev.getLocal().getCity());

        return view;
    }
}
