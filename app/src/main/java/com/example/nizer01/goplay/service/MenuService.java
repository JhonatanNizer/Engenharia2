package com.example.nizer01.goplay.service;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.nizer01.goplay.R;

public class MenuService extends AppCompatActivity {

    protected AppCompatActivity activity;

    public MenuService(AppCompatActivity act) {
        activity = act;
    }

    public void goToActivity(Class<?> cls) {
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
    }
    public void goToActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(activity, cls);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    public void setMenuActive(int id) {
        ImageButton ib = (ImageButton) activity.findViewById(id);
        ib.setBackgroundColor(ContextCompat.getColor(activity, R.color.colorMenuBgPrimaryActive));
    }

    public void changeMenuClickable(int id, boolean clickable) {
        ImageButton ib = (ImageButton) activity.findViewById(id);
        ib.setClickable(clickable);
    }
    public void changeMenuFocusable(int id, boolean focusable) {
        ImageButton ib = (ImageButton) activity.findViewById(id);
        ib.setFocusable(focusable);
    }
    public void changeMenuFocusableInTouchMode(int id, boolean focusable) {
        ImageButton ib = (ImageButton) activity.findViewById(id);
        ib.setFocusableInTouchMode(focusable);
    }
    public void unsetMenuClickable(int id) {
        changeMenuClickable(id, false);
    }
    public void setMenuClickable(int id) {
        changeMenuClickable(id, true);
    }

    public void setMenuBackgroundColor(int id, int color) {
        ImageButton ib = (ImageButton) activity.findViewById(id);
        ib.setBackgroundTintList(ContextCompat.getColorStateList(activity, color));
    }

    public void setMenuColor(int id, int color) {
        ImageButton ib = (ImageButton) activity.findViewById(id);
        ib.setColorFilter(ContextCompat.getColor(activity, color));
    }
}
