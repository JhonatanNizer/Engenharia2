package com.example.nizer01.goplay.activity;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nizer01.goplay.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppActivity {

    private static final String TAG = "MainActivity";

    private EditText etEmail;
    private EditText etPassword;
    private Button btLogin;
    private Button btRegister;

    private final FirebaseAuth fbAuth = FirebaseAuth.getInstance();

    @Override
    protected void onStart(){
        super.onStart();

        if(!isUserLoggedIn()) {
            goMaps();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = (EditText) findViewById(R.id.edittext_main_email);
        etPassword = (EditText) findViewById(R.id.edittext_main_password);
        btLogin = (Button) findViewById(R.id.button_main_login);
        btRegister = (Button) findViewById(R.id.button_main_register);
    }

    private boolean isValidPassword(CharSequence string) {

        boolean isValid = string.toString().matches("") != true;

        if(!isValid) {
            Toast.makeText(this, "Not a valid password.", Toast.LENGTH_SHORT).show();
        }

        return isValid;
    }

    private boolean isValidEmail(CharSequence string) {
        boolean isValid = string.toString().matches("") != true && android.util.Patterns.EMAIL_ADDRESS.matcher(string).matches();

        if(!isValid) {
            Toast.makeText(this, "Not a valid email field.", Toast.LENGTH_SHORT).show();
        }

        return isValid;
    }

    private boolean isValidRegister(CharSequence email, CharSequence password) {
        return isValidEmail(email) && isValidPassword(password);
    }

    public void doLogin(View view) {

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if(isValidRegister(email, password)){
            fbAuth
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            goMaps();
                        } else {
                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                });
        }
    }
}
