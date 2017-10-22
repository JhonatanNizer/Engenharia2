package com.example.jhonatan.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void irCadastro(View view){
        Intent intent1 = new Intent(getApplicationContext(), CadastroActivity.class);
        startActivity(intent1);
    }

}
