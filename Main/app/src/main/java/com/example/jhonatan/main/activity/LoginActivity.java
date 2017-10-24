package com.example.jhonatan.main.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.jhonatan.main.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    protected void irCadastro(View view){
        Intent intent1 = new Intent(getApplicationContext(), CadastroActivity.class);
        startActivity(intent1);
    }

}
