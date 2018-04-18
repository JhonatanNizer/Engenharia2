package com.example.nizer01.goplay.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.nizer01.goplay.R;
import com.example.nizer01.goplay.activity.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText firstnameEdittext;
    private EditText lastnameEdittext;
    private EditText emailEdittext;
    private EditText passEdittext;
    private EditText passAgainEdittext;
    private EditText birthdayEdittext;
    private RadioGroup genderRadioGroup;
    private Button registerButton;
    private Button backButton;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setWidgets();
        setListeners();
    }

    private void setWidgets() {
        firstnameEdittext=(EditText)findViewById(R.id.edittext_register_name);
        lastnameEdittext=(EditText)findViewById(R.id.edittext_register_lastname);
        emailEdittext=(EditText)findViewById(R.id.edittext_register_email);
        passEdittext=(EditText)findViewById(R.id.edittext_register_password);
        passAgainEdittext=(EditText)findViewById(R.id.edittext_register_confirmpassword);
        birthdayEdittext=(EditText)findViewById(R.id.edittext_register_birthday);
        genderRadioGroup=(RadioGroup)findViewById(R.id.radiogroup_register_gender);
        registerButton=(Button)findViewById(R.id.button_register_register);
        backButton=(Button)findViewById(R.id.button_register_back);
    }

    private void setListeners() {
        birthdayEdittext.setOnClickListener(this);
        registerButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }

    private void onClickEdittextBirthday() {
        Calendar calendar=Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                birthdayEdittext.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
                datePickerDialog.dismiss();
            }
        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void onClickButtonRegister() {
        String firstname=firstnameEdittext.getText().toString();
        String lastname=lastnameEdittext.getText().toString();
        String email=emailEdittext.getText().toString();
        String pass= passEdittext.getText().toString();
        String passAgain=passAgainEdittext.getText().toString();
        String birthday=birthdayEdittext.getText().toString();
        RadioButton selectedRadioButton=(RadioButton)findViewById(genderRadioGroup.getCheckedRadioButtonId());
        String gender=selectedRadioButton==null ? "":selectedRadioButton.getText().toString();
        if(!firstname.equals("")&&!lastname.equals("")&&!email.equals("")&&!pass.equals("")&&!passAgain.equals("")&&!birthday.equals("")&&!gender.equals("")){
            if(pass.equals(passAgain)){
                System.out.println("Deu boa");
            }else{
                System.out.println("Password deu ruim");
            }
        }else{
            System.out.println("Tem algo vazio");
        }
    }

    private void onClickButtonBack(){
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edittext_register_birthday:
                onClickEdittextBirthday();
                break;
            case R.id.button_register_register:
                onClickButtonRegister();
                break;
            case R.id.button_register_back:
                onClickButtonBack();
                break;
        }
    }

}
