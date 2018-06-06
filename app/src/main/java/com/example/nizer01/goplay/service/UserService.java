package com.example.nizer01.goplay.service;

import com.google.firebase.auth.FirebaseAuth;

public class UserService extends MenuPrimaryService {

    private final FirebaseAuth fbAuth;

    public UserService() {
        fbAuth = FirebaseAuth.getInstance();
    }

    public boolean isUserLoggedIn() {
        return fbAuth.getCurrentUser() != null;
    }

    public void logOut() {
        fbAuth.signOut();
    }
}
