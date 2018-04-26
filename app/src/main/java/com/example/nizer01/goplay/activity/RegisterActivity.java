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
import com.example.nizer01.goplay.dao.UserDao;
import com.example.nizer01.goplay.domain.Role;
import com.example.nizer01.goplay.domain.User;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText firstnameEdittext;
    private EditText lastnameEdittext;
    private EditText emailEdittext;
    private EditText passwordEdittext;
    private EditText passwordrepeatEdittext;
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
        passwordEdittext=(EditText)findViewById(R.id.edittext_register_password);
        passwordrepeatEdittext=(EditText)findViewById(R.id.edittext_register_confirmpassword);
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
        String password= passwordEdittext.getText().toString();
        String passwordrepeat=passwordrepeatEdittext.getText().toString();
        String birthday=birthdayEdittext.getText().toString();
        RadioButton selectedRadioButton=(RadioButton)findViewById(genderRadioGroup.getCheckedRadioButtonId());
        String gender=selectedRadioButton==null ? "":selectedRadioButton.getText().toString();

        if(!firstname.equals("")&&!lastname.equals("")&&!email.equals("")&&!password.equals("")&&!passwordrepeat.equals("")&&!birthday.equals("")&&!gender.equals("")){
            if(password.equals(passwordrepeat)){
                createUserObject(email, password, firstname, lastname, birthday, gender);
                Toast.makeText(this, R.string.toast_registercompleted, Toast.LENGTH_LONG).show();
                finish();
            }else{
                Toast.makeText(this, R.string.toast_passwordsdifferent, Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, R.string.toast_emptyfield, Toast.LENGTH_SHORT).show();
        }
    }

    private void onClickButtonBack(){
        finish();
    }

    private void createUserObject(String email, String password, String firstname, String lastname, String birthday, String gender){
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        try {
            DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
            Date date = df.parse(birthday);
            long time = date.getTime();
            user.setBirthDate(new Timestamp(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(gender == "Male" || gender == "Masculino")
            user.setGender('M');
        else{
            user.setGender('F');
        }

        Role role = new Role();
        role.setName("Normal user");
        role.setDescription("Can create and participate of events");

        UserDao.saveUser(user);

    }

}
