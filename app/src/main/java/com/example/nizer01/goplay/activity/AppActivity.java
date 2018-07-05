package com.example.nizer01.goplay.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.nizer01.goplay.service.MenuService;
import com.example.nizer01.goplay.service.UserService;

abstract public class AppActivity extends AppCompatActivity {

    protected UserService userManager;
    protected MenuService menuManager;

    protected void onCreateStartServices(AppCompatActivity activity) {
        userManager = new UserService(activity);
        menuManager = new MenuService(activity);
    }
    protected void onCreateStartServices() {
        userManager = new UserService(this);
        menuManager = new MenuService(this);
    }

    protected void onCreateUserNotIsLoggedInRedirectToMain() {
        if(!userManager.isUserLoggedIn()) {
            goToMain();
        }
    }

    protected void onCreateUserIsLoggedInRedirectToStart() {
        if(userManager.isUserLoggedIn()) {
            goToMaps();
        }
    }

    public void setMenuActive(int id) {
        menuManager.setMenuActive(id);
    }
    public void setMenuClickable(int id) {
        menuManager.setMenuClickable(id);
    }
    public void unsetMenuClickable(int id) {
        menuManager.unsetMenuClickable(id);
    }

    public void setMenuBackgroundColor(int id, int color) {
        menuManager.setMenuBackgroundColor(id, color);
    }

    public void setMenuColor(int id, int color) {
        menuManager.setMenuColor(id, color);
    }

    protected void goToHome(View view) {
        goToHome();
    }
    protected void goToHome() {
        menuManager.goToActivity( HomeActivity.class);
    }

    protected void goToAccount(View view){
        goToAccount();
    }
    protected void goToAccount(){
        menuManager.goToActivity(AccountActivity.class);
    }

    protected void goToEvents(View view) {
        goToEvents();
    }
    protected void goToEvents() {
        menuManager.goToActivity(EventsActivity.class);
    }

    protected void goToEvent(View view) {
        Bundle bundle = (Bundle) view.getTag();
        goToEvent(bundle);
    }
    protected void goToEvent(View view, Bundle bundle) {
        goToEvent(bundle);
    }
    protected void goToEvent(Bundle bundle) {
        menuManager.goToActivity(EventActivity.class, bundle);
    }

    protected void goToAddEvent(View view, Bundle bundle) {
        goToAddEvent(bundle);
    }
    protected void goToAddEvent(View view) {
        Bundle bundle = (Bundle) view.getTag();

        if (bundle != null) {
            goToAddEvent(bundle);
        }

        goToAddEvent();
    }
    protected void goToAddEvent(Bundle bundle) {
        menuManager.goToActivity(AddEventActivity.class, bundle);
    }
    protected void goToAddEvent() {
        menuManager.goToActivity(AddEventActivity.class);
    }

    protected void goToMaps(View view) {
        goToMaps();
    }
    protected void goToMaps() {
        menuManager.goToActivity(MapsActivity.class);
    }

    protected void goToMain(View view) {
        goToMain();
    }
    protected void goToMain() {
        menuManager.goToActivity(MainActivity.class);
    }

    /*
    protected void goToRegister(View view) {
        goToRegister();
    }
    protected void goToRegister() {
        menuManager.goToActivity(RegisterActivity.class);
    }
    */

    protected void goBack() {
        finishActivity(0);
    }
}
