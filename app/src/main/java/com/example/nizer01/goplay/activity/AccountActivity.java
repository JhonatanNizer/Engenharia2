package com.example.nizer01.goplay.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nizer01.goplay.R;
import com.google.firebase.auth.FirebaseAuth;

public class AccountActivity extends AppActivity {

    FirebaseAuth fbauth = FirebaseAuth.getInstance();

    Button btLogout;

    @Override
    protected void onStart(){
        super.onStart();

        if(!isUserLoggedIn()) {
            goMain();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        setMenuActive(R.id.mn_account);
        unsetMenuClickable(R.id.mn_account);

        btLogout = (Button) findViewById(R.id.button_settings_logout);
    }

    private void doLogout(View v){
        logOut();
        goMain(v);
    }
}