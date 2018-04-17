package com.example.nizer01.goplay.dao;

import android.widget.ArrayAdapter;

import com.example.nizer01.goplay.domain.Activity;
import com.example.nizer01.goplay.domain.Event;
import com.example.nizer01.goplay.domain.Local;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EventDao {

    public static ArrayList<Event> getLista(){

        ArrayList<Event> eventList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            Local lc = new Local();
            lc.setName("Local " + i);
            lc.setDescription("Descrição " + i);
            lc.setCity("Itajaí " + i);
            lc.setLatitude(i);
            lc.setLatitude(i);

            Activity ac = new Activity();
            ac.setName("Atividade " + i);
            ac.setDescription("Descrição " + i);
            ac.setMinPlayers(i);
            ac.setMaxPlayers(i);

            Event ev = new Event();
            ev.setName("NomeEvento " + i);
            ev.setDescription("Descrição " + i);
            ev.setMinPlayers(ac.getMinPlayers());
            ev.setMaxPlayers(ac.getMaxPlayers());
            ev.setStatus("Programado");
            ev.setLocal(lc);
            ev.setActivity(ac);

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            try {
                Date date = df.parse("17/04/2018 16:05");
                long time = date.getTime();
                ev.setStartTime(new Timestamp(time));
                ev.setEndTime(new Timestamp(time));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            eventList.add(ev);
        }
        return eventList;
    }

}
