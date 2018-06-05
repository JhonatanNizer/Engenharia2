package com.example.nizer01.goplay.utility;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.nizer01.goplay.R;
import com.example.nizer01.goplay.activity.EventActivity;
import com.example.nizer01.goplay.activity.HomeActivity;
import com.example.nizer01.goplay.activity.EventsActivity;
import com.example.nizer01.goplay.activity.MainActivity;
import com.example.nizer01.goplay.activity.MapsActivity;
import com.example.nizer01.goplay.activity.RegisterActivity;
import com.example.nizer01.goplay.activity.AccountActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Map;

abstract public class AppActivity extends AppCompatActivity {

    private final FirebaseAuth fbAuth = FirebaseAuth.getInstance();

    public boolean isUserLoggedIn() {
        return fbAuth.getCurrentUser() != null;
    }

    public void goActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        startActivity(intent);
    }
    public void goActivity(Context context, Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void setMenuPrimaryActive(int id) {
        ImageButton ib = (ImageButton) findViewById(id);
        ib.setBackgroundColor(ContextCompat.getColor(this, R.color.colorMenuBgPrimaryActive));
    }

    public void changeMenuPrimaryClickable(int id, boolean clickable) {
        ImageButton ib = (ImageButton) findViewById(id);
        ib.setClickable(clickable);
    }
    public void unsetMenuPrimaryClickable(int id) {
        changeMenuPrimaryClickable(id, false);
    }
    public void setMenuPrimaryClickable(int id) {
        changeMenuPrimaryClickable(id, true);
    }

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
