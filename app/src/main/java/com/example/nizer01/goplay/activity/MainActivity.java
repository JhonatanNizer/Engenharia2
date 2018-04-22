package com.example.nizer01.goplay.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nizer01.goplay.R;
import com.example.nizer01.goplay.dao.UserDao;
import com.example.nizer01.goplay.domain.User;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText emailEdittext;
    private EditText passwordEdittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setWidgets();
    }

    private void setWidgets(){
        emailEdittext = (EditText) findViewById(R.id.edittext_main_email);
        passwordEdittext = (EditText) findViewById(R.id.edittext_main_password);
    }

    public void onClickLogin(View view){
        /*
        ArrayList<User> userList = UserDao.getUserList();
        String email = emailEdittext.getText().toString();
        String password = passwordEdittext.getText().toString();
        if(userList.isEmpty()){
            Toast.makeText(this, "User list is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(email.matches("") || password.matches("")){
            Toast.makeText(this,"No field can be empty", Toast.LENGTH_SHORT).show();
            return;
        } else {
            for (int i = 0; i < userList.size(); i++) {
                //System.out.println("Usuário ID: " + i + " Email: {" + userList.get(i).getEmail() + "} Password: " + userList.get(i).getPassword());
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
        Toast.makeText(this, "Email not found", Toast.LENGTH_SHORT).show();
        */
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void onClickRegister(View view){
        Intent intent = new Intent(this, RegisterActivity.class );
        startActivity(intent);
    }

}
