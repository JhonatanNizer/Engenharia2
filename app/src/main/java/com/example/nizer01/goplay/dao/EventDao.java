package com.example.nizer01.goplay.dao;

import android.widget.ArrayAdapter;

import com.example.nizer01.goplay.domain.Activity;

import java.util.ArrayList;

public class EventDao {

    public static ArrayList<Activity> getLista(){
        ArrayList<Activity> activityList = new ArrayList<>();

        Activity ac1 = new Activity();
        ac1.setName("Atividade 1");

        return activityList;
    }

}
