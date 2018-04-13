package com.example.nizer01.goplay.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.nizer01.goplay.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickRegister(View view){
        Intent intent = new Intent(this, RegisterActivity.class );
        startActivity(intent);
    }

    public void onClickLogin(View view){
        Intent intent = new Intent(this, HomeActivity.class );
        startActivity(intent);
    }

}
