package com.example.nizer01.goplay.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nizer01.goplay.R;
import com.example.nizer01.goplay.dao.UserDao;
import com.example.nizer01.goplay.domain.User;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etEmail;
    private EditText etPassword;
    private Button btLogin;
    private Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setWidgets();
        setListeners();
    }

    private void setWidgets() {
        etEmail = (EditText) findViewById(R.id.edittext_main_email);
        etPassword = (EditText) findViewById(R.id.edittext_main_password);
        btLogin = (Button) findViewById(R.id.button_main_login);
        btRegister = (Button) findViewById(R.id.button_main_register);
    }

    private void setListeners() {
        btLogin.setOnClickListener(this);
        btRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_main_login:
                onClickLogin();
                break;
            case R.id.button_main_register:
                onClickRegister();
                break;
        }
    }

    private void onClickLogin() {
        /*
        ArrayList<User> userList = UserDao.getUserList();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        if (userList.isEmpty()) {
            Toast.makeText(this, "User list is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.matches("") || password.matches("")) {
            Toast.makeText(this, "No field can be empty", Toast.LENGTH_SHORT).show();
            return;
        } else {
            for (int i = 0; i < userList.size(); i++) {
                if (email.matches(userList.get(i).getEmail())) {
                    if (password.matches(userList.get(i).getPassword())) {
                        Intent intent = new Intent(this, HomeActivity.class);
                        startActivity(intent);
                        return;
                    } else {
                        Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        }
        */
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        //Toast.makeText(this, "Email not found", Toast.LENGTH_SHORT).show();
    }

    private void onClickRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}
