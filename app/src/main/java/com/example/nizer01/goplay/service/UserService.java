package com.example.nizer01.goplay.service;

import android.content.Context;
import android.content.ContextWrapper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.nizer01.goplay.domain.Account;
import com.example.nizer01.goplay.domain.Event;
import com.example.nizer01.goplay.domain.Profile;
import com.example.nizer01.goplay.listeners.OnGetEventsListener;
import com.example.nizer01.goplay.listeners.OnGetProfileListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserService {

    protected FirebaseAuth fbAuth;
    protected AppCompatActivity activity;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("profile/");

    public UserService(AppCompatActivity act) {
        activity = act;
        fbAuth = FirebaseAuth.getInstance();
    }

    public boolean isUserLoggedIn() {
        return fbAuth.getCurrentUser() != null;
    }

    public FirebaseUser getCurrentUser() {
        return fbAuth.getCurrentUser();
    }

    public String getCurrentUserId() {
        return fbAuth.getCurrentUser().getUid();
    }

    public String getCurrentUserEmail() {
        return fbAuth.getCurrentUser().getEmail();
    }

    public void logOut() {
        fbAuth.signOut();
    }

    public void writeNewAccount(Account account) {
        fbAuth.createUserWithEmailAndPassword(account.getEmail(), account.getPassword());
    }

    public String writeNewProfile(Profile pf) {

        String key = firebaseDatabase.getReference().child("profile").push().getKey();
        pf.setId(key);

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/profile/" + key, pf);

        firebaseDatabase.getReference().updateChildren(childUpdates);

        return key;
    }

    public void onGetProfileByEmail(final String email, final OnGetProfileListener onGetProfileListener) {
        databaseReference.orderByChild("email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Profile profile = null;

                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Profile pf = objSnapshot.getValue(Profile.class);

                    if(pf.getEmail().equals(email)) {
                        profile = pf;
                        break;
                    }
                }

                if(profile == null) {
                    onGetProfileListener.onNotFinded();
                } else {
                    onGetProfileListener.onFinded(profile);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                onGetProfileListener.onError(databaseError);
            }
        });
    }

    public void onGetProfileById(final String pid, final OnGetProfileListener onGetProfileListener) {
        databaseReference.orderByChild("email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Profile profile = null;

                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Profile pf = objSnapshot.getValue(Profile.class);

                    if(pf.getId().equals(pid)) {
                        profile = pf;
                        break;
                    }
                }

                if(profile == null) {
                    onGetProfileListener.onNotFinded();
                } else {
                    onGetProfileListener.onFinded(profile);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                onGetProfileListener.onError(databaseError);
            }
        });
    }
}
