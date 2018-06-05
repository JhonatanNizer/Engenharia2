package com.example.nizer01.goplay.service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.nizer01.goplay.R;
import com.example.nizer01.goplay.activity.AccountActivity;
import com.example.nizer01.goplay.activity.EventActivity;
import com.example.nizer01.goplay.activity.EventsActivity;
import com.example.nizer01.goplay.activity.HomeActivity;
import com.example.nizer01.goplay.activity.MainActivity;
import com.example.nizer01.goplay.activity.MapsActivity;
import com.example.nizer01.goplay.activity.RegisterActivity;

public class MenuService extends AppCompatActivity {

    protected Context context;

    public MenuService(Context ctx) {
        context = ctx;
    }

    public MenuService() {
        context = this;
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

    public void setMenuActive(int id) {
        ImageButton ib = (ImageButton) findViewById(id);
        ib.setBackgroundColor(ContextCompat.getColor(context, R.color.colorMenuBgPrimaryActive));
    }

    public void changeMenuClickable(int id, boolean clickable) {
        ImageButton ib = (ImageButton) findViewById(id);
        ib.setClickable(clickable);
    }
    public void unsetMenuClickable(int id) {
        changeMenuClickable(id, false);
    }
    public void setMenuClickable(int id) {
        changeMenuClickable(id, true);
    }
}
