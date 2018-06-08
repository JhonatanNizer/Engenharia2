package com.example.nizer01.goplay.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nizer01.goplay.R;
import com.example.nizer01.goplay.domain.Event;
import com.example.nizer01.goplay.domain.Profile;
import com.example.nizer01.goplay.listeners.OnGetEventListener;
import com.example.nizer01.goplay.listeners.OnGetProfileListener;
import com.example.nizer01.goplay.service.EventService;
import com.google.android.gms.vision.text.Line;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class EventActivity extends AppActivity {

    EventService eventManager = new EventService();
    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateStartServices();
        onCreateUserNotIsLoggedInRedirectToMain();

        setContentView(R.layout.activity_event);
        setMenuActive(R.id.mn_events);

        getEventById(getIntent().getExtras().getString("id"));
    }

    protected void getEventById(String id) {
        eventManager.onGetEventById(getIntent().getExtras().getString("id"), new OnGetEventListener() {
            @Override
            public void onFinded(Event ev) {
                setEventFields(ev);
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

    protected void setFieldText(String value, int viewId) {
        ((TextView) findViewById(viewId)).setText(value);
    }

    protected void setEventFields(Event ev) {
        event = ev;

        setFieldText(ev.getName(), R.id.txEvName);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy 'às' hh:mm");
        setFieldText(dateFormat.format(ev.getStartTime()).toString(), R.id.txEvDate);

        setFieldText(ev.getDescription(), R.id.txEvDescription);
        setFieldText(ev.getInvestiments(), R.id.txEvInvestiments);
        setFieldText(ev.getRequirements(), R.id.txEvRequirements);
        setFieldText(ev.getMinPlayers() + "-" + ev.getMaxPlayers(), R.id.txEvPlayers);

        userManager.onGetProfileByEmail(userManager.getCurrentUserEmail(), new OnGetProfileListener() {
            @Override
            public void onFinded(Profile pf) {

                if(!event.getPuids().contains(pf.getId())) {
                    Button button = findViewById(R.id.btEvParticipate);
                    button.setVisibility(button.VISIBLE);
                }
            }

            @Override
            public void onNotFinded() {
                Button button = findViewById(R.id.btEvParticipate);
                button.setVisibility(button.VISIBLE);
            }

            @Override
            public void onError(Object er) {

            }
        });

        setMyEventsItems(ev.getPuids());
    }

    public void setMyEventsItems(ArrayList<String> puids) {

        LinearLayout ll = findViewById(R.id.lsEvSubscribers);
        ll.removeAllViews();

        for (String puid: puids) {
            getProfileItemView(getLayoutInflater().inflate(R.layout.activity_event_profile, null), puid, ll);
        }
    }

    public void getProfileItemView(final View view, String puid, final LinearLayout ll) {

        userManager.onGetProfileById(puid, new OnGetProfileListener() {
            @Override
            public void onFinded(Profile pf) {

                ((TextView) view.findViewById(R.id.txEvPfNomeCompleto)).setText(pf.getFirstName() + " " + pf.getLastName());
                ((TextView) view.findViewById(R.id.txEvPfEmail)).setText(pf.getEmail());

                if(ll.findViewById(view.getId()) != null) {
                    ll.removeView(view);
                }

                ll.addView(view);
            }

            @Override
            public void onNotFinded() {

            }

            @Override
            public void onError(Object er) {

            }
        });


    }

    protected void doParticipate(View view) {

    }
}
