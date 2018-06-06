package com.example.nizer01.goplay.service;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.nizer01.goplay.activity.AccountActivity;
import com.example.nizer01.goplay.activity.EventActivity;
import com.example.nizer01.goplay.activity.EventsActivity;
import com.example.nizer01.goplay.activity.HomeActivity;
import com.example.nizer01.goplay.activity.MainActivity;
import com.example.nizer01.goplay.activity.MapsActivity;
import com.example.nizer01.goplay.activity.RegisterActivity;

public class MenuPrimaryService extends MenuService {

    public void goHome(View view) {
        goHome();
    }
    public void goHome() {
        goActivity(this, HomeActivity.class);
    }

    public void goAccount(View view){
        goAccount();
    }
    public void goAccount(){
        goActivity(this, AccountActivity.class);
    }

    public void goEvents(View view) {
        goEvents();
    }
    public void goEvents() {
        goActivity(this, EventsActivity.class);
    }

    public void goEvent(View view, Bundle bundle) {
        goEvent(bundle);
    }
    public void goEvent(Bundle bundle) {
        goActivity(this, EventActivity.class, bundle);
    }

    public void goMaps(View view) {
        goMaps();
    }
    public void goMaps() {
        goActivity(this, MapsActivity.class);
    }

    public void goMain(View view) {
        goMain();
    }
    public void goMain() {
        goActivity(this, MainActivity.class);
    }

    public void goRegister(View view) {
        goRegister();
    }
    public void goRegister() {
        goActivity(this, RegisterActivity.class);
    }
}
