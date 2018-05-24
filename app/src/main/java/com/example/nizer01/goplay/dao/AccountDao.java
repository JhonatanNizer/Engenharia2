package com.example.nizer01.goplay.dao;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.nizer01.goplay.R;
import com.example.nizer01.goplay.activity.RegisterActivity;
import com.example.nizer01.goplay.domain.Account;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AccountDao {

    private static FirebaseAuth fbAuth = FirebaseAuth.getInstance();

    public static void createAccount(Account account) {
        String email = account.getEmail();
        String password = account.getPassword();

        fbAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                    }
                });
    }

}


