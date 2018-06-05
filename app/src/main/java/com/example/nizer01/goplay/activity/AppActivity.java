package com.example.nizer01.goplay.activity;

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
import com.example.nizer01.goplay.service.MenuPrimaryService;
import com.example.nizer01.goplay.service.MenuService;
import com.example.nizer01.goplay.service.UserService;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Map;

abstract public class AppActivity extends AppCompatActivity {

    protected final MenuPrimaryService menuPrimary;
    protected final UserService user;

    protected AppActivity() {
        user = new UserService();
        menuPrimary = new MenuPrimaryService(getApplicationContext());
    }

}
