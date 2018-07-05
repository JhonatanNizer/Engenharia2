package com.example.nizer01.goplay.activity;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.nizer01.goplay.R;
import com.example.nizer01.goplay.dao.AccountDao;
import com.example.nizer01.goplay.dao.ProfileDao;
import com.example.nizer01.goplay.domain.Account;
import com.example.nizer01.goplay.domain.Profile;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegisterActivity extends AppActivity implements View.OnClickListener {

    private static final String TAG = "RegisterActivity";

    private EditText etFirstName;
    private EditText etLastName;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etRepeatPassword;
    private EditText etBirthday;
    private RadioGroup rgGender;
    private Button btRegister;
    private Button btBack;

    private FirebaseAuth fbAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateStartServices(this);

        setContentView(R.layout.activity_register);
        setWidgets();
        setListeners();
    }

    private void setWidgets() {
        etFirstName = (EditText) findViewById(R.id.edittext_register_name);
        etLastName = (EditText) findViewById(R.id.edittext_register_lastname);
        etEmail = (EditText) findViewById(R.id.edittext_register_email);
        etPassword = (EditText) findViewById(R.id.edittext_register_password);
        etRepeatPassword = (EditText) findViewById(R.id.edittext_register_confirmpassword);
        etBirthday = (EditText) findViewById(R.id.edittext_register_birthday);
        rgGender = (RadioGroup) findViewById(R.id.radiogroup_register_gender);
        btRegister = (Button) findViewById(R.id.button_register_register);
        btBack = (Button) findViewById(R.id.button_register_back);
    }

    private void setListeners() {
        etBirthday.setOnClickListener(this);
        btRegister.setOnClickListener(this);
        btBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog =
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    etBirthday.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
            }
        }, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void onClickButtonRegister() {
        String firstname = etFirstName.getText().toString();
        String lastname = etLastName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String passwordrepeat = etRepeatPassword.getText().toString();
        String birthday = etBirthday.getText().toString();
        RadioButton selectedRadioButton = (RadioButton) findViewById(
                rgGender.getCheckedRadioButtonId());
        String gender = selectedRadioButton == null ? "" : selectedRadioButton.getText().toString();
        if (!firstname.equals("") &&
                !lastname.equals("") &&
                !email.equals("") &&
                !password.equals("") &&
                !passwordrepeat.equals("") &&
                !birthday.equals("")&&
                !gender.equals("")) {
            if (password.equals(passwordrepeat)) {
                if(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                     createAccountObject(email, password);
                     createProfileObject(firstname, lastname, birthday, gender, email);
                }else{
                    Toast.makeText(this, "Invalid e-Mail", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, R.string.toast_passwordsdifferent,
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.toast_emptyfield, Toast.LENGTH_SHORT).show();
        }
    }

    private void onClickButtonBack() {
        finish();
    }

    private boolean isBirthdayValid(String birthday){
        return true;
    }

    private void createAccountObject(String email, String password) {
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        userManager.writeNewAccount(account);
    }

    private void createProfileObject(String firstname, String lastname, String birthday, String gender, String email) {
        Profile profile = new Profile();
        profile.setFirstName(firstname);
        profile.setLastName(lastname);
        profile.setEmail(email);
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date date = df.parse(birthday);
            long time = date.getTime();
            profile.setBirthDate(time);
        } catch (ParseException e) {
            Log.e(TAG, "createUserObject: ", e);
        }
        profile.setGender(gender);
        userManager.writeNewProfile(profile);
        finish();
    }

}
