package com.example.nizer01.goplay.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nizer01.goplay.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class CreateEventActivity2 extends AppCompatActivity implements View.OnClickListener{

    private EditText firstnameEdittext;
    private EditText lastnameEdittext;
    private EditText emailEdittext;
    private EditText passEdittext;
    private EditText passAgainEdittext;
    private EditText birthdayEdittext;
    private RadioGroup genderRadioGroup;
    private Button registerButton;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event2);
        bindViews();
        setViewActions();
        prepareDatePickerDialog();
    }

    private void bindViews() {
        firstnameEdittext=(EditText)findViewById(R.id.firstname_edittext);
        lastnameEdittext=(EditText)findViewById(R.id.lastname_edittext);
        emailEdittext=(EditText)findViewById(R.id.email_edittext);
        passEdittext=(EditText)findViewById(R.id.password_edittext);
        passAgainEdittext=(EditText)findViewById(R.id.password_again_edittext);
        birthdayEdittext=(EditText)findViewById(R.id.birthday_edittext);
        genderRadioGroup=(RadioGroup)findViewById(R.id.gender_radiogroup);
        registerButton=(Button)findViewById(R.id.register_button);
    }

    private void setViewActions() {
        birthdayEdittext.setOnClickListener(this);
        registerButton.setOnClickListener(this);
    }

    private void prepareDatePickerDialog() {
        Calendar calendar=Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                birthdayEdittext.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
                datePickerDialog.dismiss();
            }
        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
    }

    private void confirmEvent() {
        String firstname=firstnameEdittext.getText().toString();
        String lastname=lastnameEdittext.getText().toString();
        String email=emailEdittext.getText().toString();
        String pass=passEdittext.getText().toString();
        String passAgain=passAgainEdittext.getText().toString();
        String birthday=birthdayEdittext.getText().toString();
        RadioButton selectedRadioButton=(RadioButton)findViewById(genderRadioGroup.getCheckedRadioButtonId());
        String gender=selectedRadioButton==null ? "":selectedRadioButton.getText().toString();
        if(!firstname.equals("")&&!lastname.equals("")&&!email.equals("")&&!pass.equals("")&&!passAgain.equals("")&&!birthday.equals("")&&!gender.equals("")){
            if(pass.equals(passAgain)){
                //deu boa
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            }else{
                //pass diferente
            }
        }else{
            //algo vazio
        }
    }

    private void cancelEvent(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.birthday_edittext:
                datePickerDialog.show();
                break;
            case R.id.register_button:
                confirmEvent();
                break;
            case R.id.button_back:
                cancelEvent();
                break;
        }
    }


}