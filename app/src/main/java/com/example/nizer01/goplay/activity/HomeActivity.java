package com.example.nizer01.goplay.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.nizer01.goplay.R;
import com.example.nizer01.goplay.dao.EventDao;
import com.example.nizer01.goplay.domain.Event;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.MapView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "HomeActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    @Override
    public void onClick(View v) {

    }

    public void onClickCreateEvent(View view) {
        Intent intent = new Intent(this, CreateEventActivity2.class);
        intent.putExtra("FromHome", "FromHome");
        startActivity(intent);
    }

    public void onClickListEvents(View view) {
        Intent intent = new Intent(this, ListEventsActivity.class);
        startActivity(intent);
        EventDao.getFireBaseEvents();
    }

    public void onClickMap(View view) {
        if (isServiesOk()) {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }
    }

    public void onClickEvento(View view) {
        if (isServiesOk()) {
            Intent intent = new Intent(this, EventActivity.class);
            startActivity(intent);
        }
    }
    public boolean isServiesOk() {
        Log.d(TAG, "isServicesOk: checking google sercies version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);

        if (available == ConnectionResult.SUCCESS) {
            Log.d(TAG, "isServiesOk: Google Play Services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            Log.d(TAG, "isServicesOk: Google Play Services is NOT working ");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "Map request refused", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void onClickSettings(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

}
