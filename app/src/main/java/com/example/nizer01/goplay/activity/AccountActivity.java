package com.example.nizer01.goplay.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nizer01.goplay.R;
import com.example.nizer01.goplay.utility.AppActivity;
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
        setMenuPrimaryActive(R.id.mn_account);
        unsetMenuPrimaryClickable(R.id.mn_account);

        btLogout = (Button) findViewById(R.id.button_settings_logout);
    }

    private void doLogout(View v){
        fbauth.signOut();
        goMain(v);
    }
}
