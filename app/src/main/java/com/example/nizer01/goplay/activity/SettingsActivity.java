package com.example.nizer01.goplay.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nizer01.goplay.R;
import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener  {

    FirebaseAuth fbauth = FirebaseAuth.getInstance();

    Button btLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setWidgets();
        setListeners();
    }

    private void setWidgets(){
        btLogout = (Button) findViewById(R.id.button_settings_logout);
    }

    private void setListeners(){
        btLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_settings_logout:
                onClickLogout();
                break;
        }
    }

    private void onClickLogout(){
        fbauth.signOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
