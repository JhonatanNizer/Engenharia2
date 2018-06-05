package com.example.nizer01.goplay.activity;

import android.os.Bundle;

import com.example.nizer01.goplay.R;

public class HomeActivity extends AppActivity {

    private static final String TAG = "HomeActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onStart(){
        super.onStart();

        if(!user.isUserLoggedIn()) {
            menuPrimary.goMain();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        menuPrimary.setMenuActive(R.id.mn_dashboard);
        menuPrimary.unsetMenuClickable(R.id.mn_dashboard);
    }
}
