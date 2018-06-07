package com.example.nizer01.goplay.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nizer01.goplay.R;
import com.example.nizer01.goplay.domain.Profile;
import com.example.nizer01.goplay.listeners.OnGetProfileListener;
import com.google.firebase.auth.FirebaseAuth;

public class AccountActivity extends AppActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateStartServices();
        onCreateUserNotIsLoggedInRedirectToMain();

        setContentView(R.layout.activity_account);
        setMenuActive(R.id.mn_account);
        unsetMenuClickable(R.id.mn_account);

        getProfileByEmail(userManager.getCurrentUserEmail());
    }

    public void getProfileByEmail(String email) {
        userManager.onGetProfileByEmail(email, new OnGetProfileListener() {
            @Override
            public void onFinded(Profile pf) {
                setProfileFields(pf);
            }

            @Override
            public void onNotFinded() {

            }

            @Override
            public void onError(Object er) {

            }
        });
    }

    public void setProfileFields(Profile pf) {
        setFieldText("ID: " + pf.getId(), R.id.txStUid);
        setFieldText("NOME: " + pf.getFirstName() + " " + pf.getLastName() , R.id.txStFullName);
        setFieldText("EMAIL: " + pf.getEmail(), R.id.txStEmail);
    }

    protected void setFieldText(String value, int viewId) {
        ((TextView) findViewById(viewId)).setText(value);
    }

    public void doLogout(View view){
        userManager.logOut();
        goToMain(view);
    }
}
