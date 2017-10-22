package com.example.jhonatan.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class CadastroActivity extends AppCompatActivity {

    private int editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    protected void voltarMain(View view){
        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent1);
    }

    protected void irMenuPrincipal(View view){
        Intent intent2 = new Intent(getApplicationContext(), MenuPrincipalActivity.class);
        startActivity(intent2);

        /*CRIAR UM OBJETO PESSOA AQUI COM OS DADOS DOS CAMPOS DE TEXTO (menos data de nascimento) */

    }

}
