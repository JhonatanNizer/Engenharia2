package com.example.nizer01.goplay.activity;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nizer01.goplay.R;
import com.example.nizer01.goplay.dao.UserDao;
import com.example.nizer01.goplay.domain.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe onde o programa é inicializado.
 * É responsável por exibir a tela inicial de login.
 * Caso o usuário não esteja logado no sistema, é possível ir para a atividade de registro para
 * se cadastrar.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Tag usada para log.
     */
    private static final String TAG = "MainActivity";

    /**
     * Variáveis que serão inicializados pelo método setWidgets().
     */
    private EditText etEmail;
    private EditText etPassword;
    private Button btLogin;
    private Button btRegister;

    /**
     * Variável da autencicação via firebase.
     */
    private FirebaseAuth fbAuth;

    /**
     * Método onStart() é inicializado automaticamente antes de criar a atividade.
     * Ele verifica caso já haja algum usuário logado para encaminhar para a tela home do app.
     */
    @Override
    protected void onStart(){
        super.onStart();
        if(fbAuth.getCurrentUser() != null){
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * Método onCreate() é inicializado automaticamente após criar a atividade.
     *
     * @param savedInstanceState Parametro obrigatório para a criação da atividade.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setWidgets();
        setListeners();
        fbAuth = FirebaseAuth.getInstance();
    }

    /**
     * setWidgets() responsável por relacionar os objetos gráficos do layout com os objetos
     * desta classe declarados na inicialização.
     * É chamado apenas uma vez dentro do método onCreate().
     */
    private void setWidgets() {
        etEmail = (EditText) findViewById(R.id.edittext_main_email);
        etPassword = (EditText) findViewById(R.id.edittext_main_password);
        btLogin = (Button) findViewById(R.id.button_main_login);
        btRegister = (Button) findViewById(R.id.button_main_register);
    }

    /**
     * setListeners() responsável por criar e relacionar os listeners utilizados nessa atividade
     * com seus respectivos botoes.
     * É chamado apenas uma vez dentro do método onCreate().
     */
    private void setListeners() {
        btLogin.setOnClickListener(this);
        btRegister.setOnClickListener(this);
    }

    /**
     * onClick() é responsável por detectar em qual botão o listener foi chamado utilizando o id do
     * widget como parâmetro e direcionar para o método correto que irá tratar o clique.
     *
     * @param v Recebe a View que está sendo executada no momento e solicita a id do widget clicado
     *          através do método getId().
     */
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

    /**
     * onClickLogin é responsável por tratar o clique no botão "login"
     * Ao ser chamado, é verificado se tanto o campo de e-mail quanto o campo de password
     * estão preenchidos.
     * Caso estejam é verificado se o password preenchido está de acordo com o email preenchido
     * Chama o método "HomeAcitivity" se as verificações forem aprovadas, ou exibe mensagem de erro
     * caso algo esteja errado.
     * É chamado apenas pelo método onClick().
     */
    private void onClickLogin() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        if (email.matches("") || password.matches("")) {
            Toast.makeText(this, "No field can be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(isValidEmail(email)){
            fbAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(MainActivity.this,
                                        task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(this, "Invalid E-mail", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * onClick register é responsável por iniciar a atividade de registro caso seja chamada
     * pelo método onClick() se o usuário clicar no botão "register"
     */
    private void onClickRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    /**
     * Função para verificar se email digitado pelo usuário é valido.
     * @param string String recebida para verificação do padrão de e-mail.
     * @return Retorna true, caso a string esteja no padrão correto de e-mail.
     */
    private boolean isValidEmail(CharSequence string) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(string).matches();
    }

}
