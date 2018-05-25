package com.example.nizer01.goplay.dao;

import com.example.nizer01.goplay.domain.Profile;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileDao {

    private static final String TAG = "ProfileDao";

    private static final FirebaseDatabase fbDatabase = FirebaseDatabase.getInstance();

    public static void createProfile(Profile profile){
        DatabaseReference dbRef = fbDatabase.getReference("profile/" );
        dbRef.push().setValue(profile);
    }

}
